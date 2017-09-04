package com.fengmaster.ngbt.node.itf;

import com.fengmaster.ngbt.node.itf.INode;

import java.util.Collection;

/**
 * Created by qianzise on 2017/9/3.
 * 抽象动作节点
 */
public abstract class AbsActionNode implements INode {

    @Override
    public void addNode(INode node) {
        return;
    }

    @Override
    public void addNodes(Collection<INode> nodes) {
        return;
    }

    @Override
    public Object copy() {
        try {
            return this.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
