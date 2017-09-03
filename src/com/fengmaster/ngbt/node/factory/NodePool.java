package com.fengmaster.ngbt.node.factory;

import com.fengmaster.ngbt.node.itf.INode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qianzise on 2017/9/1.
 * 节点池
 */
public class NodePool {

    private Map<String,INode> iNodeMap=new HashMap<>();

    private static NodePool instance;

    public static NodePool getInstance(){
        if (instance==null){
            instance=new NodePool();
        }
        return instance;
    }

    private NodePool(){

    }

    /**
     * 添加节点到节点池
     * @param nodeName 节点名称
     * @param iNode 节点
     */
    public void addNode(String nodeName,INode iNode){
        iNodeMap.put(nodeName,iNode);
    }

    public INode getNode(String name){
        return iNodeMap.get(name);
    }


}
