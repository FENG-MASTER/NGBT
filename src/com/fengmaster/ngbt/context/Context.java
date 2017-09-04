package com.fengmaster.ngbt.context;

/**
 * Created by Feng-master on 2017/8/31.
 * 行为树上下文对象
 */
public class Context {

    /**
     * 全局上下文
     */
    private GlobalContext globalContext;
    /**
     * 行为树上下文
     */
    private TreeContext treeContext;
    /**
     * 指定节点上下文
     */
    private NodeContext nodeContext;

    public Context(){
        globalContext=GlobalContext.getInstance();
        nodeContext = NodeContext.newInstance();
        treeContext = TreeContext.newInstance();
    }

    public GlobalContext getGlobalContext() {
        return globalContext;
    }

    public TreeContext getTreeContext() {
        return treeContext;
    }

    public NodeContext getNodeContext() {
        return nodeContext;
    }
}
