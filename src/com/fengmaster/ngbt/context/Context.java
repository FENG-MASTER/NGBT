package com.fengmaster.ngbt.context;

import java.util.Map;

/**
 * Created by Feng-master on 2017/8/31.
 * 行为树上下文对象
 */
public class Context {

    private GlobalContext globalContext;
    private NodeContext nodeContext;
    private TreeContext treeContext;

    public Context(){
        globalContext=GlobalContext.getInstance();
        treeContext=TreeContext.newInstance();
        nodeContext=NodeContext.newInstance();
    }

    public GlobalContext getGlobalContext() {
        return globalContext;
    }

    public NodeContext getNodeContext() {
        return nodeContext;
    }

    public TreeContext getTreeContext() {
        return treeContext;
    }
}
