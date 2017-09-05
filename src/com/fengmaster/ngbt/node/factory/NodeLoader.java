package com.fengmaster.ngbt.node.factory;

import com.fengmaster.ngbt.Util;
import com.fengmaster.ngbt.node.itf.INode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qianzise on 2017/9/1.
 */
public class NodeLoader {

    private List<String> packageNames=new ArrayList<>();

    /**
     * 默认配置目录
     */
    public static final String PATH_DEF = new File("").getAbsolutePath()+"\\conf\\";
    private static NodeLoader instance;

    /**
     * 配置目录
     */
    private String path;

    private NodeLoader() {

    }

    /**
     * 单例
     * @return 实例
     */
    public static NodeLoader getInstance() {
        if (instance == null) {
            instance = new NodeLoader();
        }
        return instance;
    }

    /**
     * 根据配置创建节点
     *
     * @param jsonObject 配置json
     * @return 创建的新节点
     */
    public INode createNodeByConf(JSONObject jsonObject) throws JSONException {
        String className = jsonObject.getString("class");//获得节点类名
        INode node = null;
        try {
            node = createNodeByClassName(className,jsonObject);
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.printf("找不到" + className);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    private INode createNodeByClassName(String name,JSONObject conf) throws IllegalAccessException, InstantiationException, IOException, JSONException {
        if (NodePool.getInstance().getNode(name)!=null){
            return NodePool.getInstance().getNode(name);
        }

        File file=new File(path+name+".conf");
        if (file.exists()&&file.isFile()&&file.getName().substring(file.getName().lastIndexOf(".")+1).equals("conf")){
           return initFile(file,name);
        }

        for (String packageName : packageNames) {
            Class<? extends INode> clazz=null;
            try {
                clazz= (Class<? extends INode>) Class.forName(packageName+"."+name);//必须完整类名,包括包名
            } catch (ClassNotFoundException e) {
                continue;
            }
            INode node = clazz.newInstance();
            node.init(conf);
            return node;
        }

        return null;
    }

    /**
     * 默认路径初始化
     * @throws IOException io
     * @throws JSONException json
     */
    public void init() throws IOException, JSONException {
        init(PATH_DEF);
    }

    /**
     * 初始化自定义配置文件路径
     * @param path 配置文件路径
     * @throws IOException io
     * @throws JSONException json
     */
    public void init(String path) throws IOException, JSONException {

        this.path=path;

        //添加默认实现容器类包名
        packageNames.add("com.fengmaster.ngbt.node.compent");
        packageNames.add("");

        File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException("找不到配置文件目录");
        }

        if (!file.isDirectory()) {
            throw new FileNotFoundException("非法路径");
        }

        //过滤掉非.conf文件
        List<File> files = Arrays.asList(file.listFiles(pathname -> pathname.getName().substring(pathname.getName().lastIndexOf(".")+1).equals("conf")));

        for (File tf : files) {
            String nodeName = tf.getName().substring(0, tf.getName().lastIndexOf("."));//取出文件名

            if(NodePool.getInstance().getNode(nodeName)!=null){
                continue;
            }

            initFile(tf,nodeName);

        }


    }

    /**
     * 添加配置文件中使用的类的包名,或者你可以配置中直接使用完整类名
     * PS:当出现多个包里相同类名,会先检查先添加的包名
     * @param pack 完整包名
     */
    public void addPackageName(String pack){
        packageNames.add(pack);
    }

    private INode initFile(File file,String nodeName) throws IOException, JSONException {
        JSONObject jo = Util.readFile2Json(file);//读取配置文件
        INode node = createNodeByConf(jo);
        NodePool.getInstance().addNode(nodeName, node);//存入节点池
        return node;
    }

}
