package com.fengmaster.ngbt.context;

import com.fengmaster.ngbt.node.itf.INode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng-master on 2017/8/31.
 *  指定节点作用域,目前只能针对特定节点名字,根据节点名称确定变量作用域
 */
public class NodeContext {

    private Map<Object,Map<Object,Object>> map=new HashMap<>();

    public static NodeContext newInstance(){
        return new NodeContext();
    }

    private NodeContext(){

    }

    /**
     * 获得指定节点的key对应的变量
     * @param iNode 节点
     * @param key key
     * @return 变量
     */
    public Object get(INode iNode,Object key){
        String name = iNode.getClass().getSimpleName();
        if (!map.containsKey(name)){
            return null;
        }
        return map.get(name).get(key);
    }

    /**
     * 添加指定节点变量
     * @param iNode 节点
     * @param key key
     * @param val 变量
     */
    public void put(INode iNode, Object key, Object val){
        put(iNode.getClass().getSimpleName(),key,val);
    }

    /**
     * 添加指定节点变量
     * @param nodeName 节点名
     * @param key key
     * @param val 变量
     */
    public void put(String nodeName, Object key, Object val){
        if (!map.containsKey(nodeName)){
            map.put(nodeName,new HashMap<>());
        }
        map.get(nodeName).put(key,val);
    }

}
