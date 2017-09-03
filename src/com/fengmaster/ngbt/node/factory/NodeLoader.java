package com.fengmaster.ngbt.node.factory;

import com.fengmaster.ngbt.Util;
import com.fengmaster.ngbt.node.itf.INode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qianzise on 2017/9/1.
 */
public class NodeLoader {

    /**
     * 默认配置目录
     */
    public static final String PATH_DEF = new File("").getAbsolutePath()+"\\conf\\";
    private static NodeLoader instance;

    private NodeLoader() {

    }

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
    public static INode createNodeByConf(JSONObject jsonObject) throws JSONException {
        String className = jsonObject.getString("class");//获得节点类名
        INode node = null;
        try {
            node = createNodeByClassName(className);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.printf("找不到" + className);
            e.printStackTrace();
        }
        node.init(jsonObject);
        return node;
    }

    private static INode createNodeByClassName(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        @SuppressWarnings("unchecked")
        Class<? extends INode> clazz = (Class<? extends INode>) Class.forName(name);
        return clazz.newInstance();
    }

    /**
     * 默认路径初始化
     * @throws IOException io
     * @throws JSONException json
     */
    public void init() throws IOException, JSONException {
        init(PATH_DEF);
    }

    public void init(String path) throws IOException, JSONException {

        File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException("找不到配置文件目录");
        }

        if (!file.isDirectory()) {
            throw new FileNotFoundException("非法路径");
        }

        //过滤掉非.conf文件
        List<File> files = Arrays.asList(file.listFiles(pathname -> pathname.getName().substring(pathname.getName().lastIndexOf(".")).equals("conf")));

        for (File tf : files) {
            String nodeName = tf.getName().substring(0, tf.getName().lastIndexOf("."));//取出文件名

            JSONObject jo = Util.readFile2Json(tf);//读取配置文件

            NodePool.getInstance().addNode(nodeName, createNodeByConf(jo));//存入节点池

        }


    }

}
