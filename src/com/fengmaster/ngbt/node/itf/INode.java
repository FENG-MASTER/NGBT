package com.fengmaster.ngbt.node.itf;

import java.util.Collection;

/**
 * Created by Feng-master on 2017/8/22.
 * 节点接口
 */
public interface INode extends ICondition, IExecute {


    void addNode(INode node);

    void addNodes(Collection<INode> nodes);


}
