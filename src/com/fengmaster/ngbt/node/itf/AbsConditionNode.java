package com.fengmaster.ngbt.node.itf;

import com.fengmaster.ngbt.context.Context;

import java.util.Collection;

/**
 * Created by Feng-master on 2017/9/5.
 * 抽象条件节点
 */
public abstract class AbsConditionNode extends AbsActionNode {
    @Override
    public void addNode(INode node) {
        super.addNode(node);
    }

    @Override
    public void addNodes(Collection<INode> nodes) {
        super.addNodes(nodes);
    }

    @Override
    public void execute(Context context) {
        //空实现
    }

    @Override
    public State getState(Context context) {
        return State.STOP;
    }
}
