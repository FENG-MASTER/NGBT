package com.fengmaster.ngbt.node.compent;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.INode;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Feng-master on 2017/8/31.
 *
 *
 * 带优先级的选择节点（Priority Selector）
 *
 * 当发现找到一个可执行的子节点后就停止搜索后续子节点
 */
public class PrioritySelector implements INode {

    private Queue<INode> queue=new LinkedBlockingQueue<>();

    private INode execNode;

    @Override
    public void execute(Context context) {
        if (execNode==null){
            return;
        }

        execNode.execute(context);

    }

    @Override
    public boolean condition(Context context) {

        for (INode iNode : queue) {
            if (iNode.condition(context)){
                //如果满足了其中一个
                execNode=iNode;
                return true;
            }
        }
        execNode=null;
        return false;
    }

    @Override
    public void addNode(INode node) {
        queue.add(node);
    }

    @Override
    public void addNodes(Collection<INode> nodes) {
        queue.addAll(nodes);
    }
}
