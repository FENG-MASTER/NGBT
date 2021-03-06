package com.fengmaster.ngbt.node.compent;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.INode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Feng-master on 2017/8/31.
 * <p>
 * 不带优先级的选择节点（Non-priority Selector）
 * <p>
 * 从上一个执行过的子节点开始选择，如果前提满足，则继续执行此节点，
 * 如果条件不满足，则从此节点开始，依次判断每一个子节点的前提，当找到一个满足条件的子节点后，则执行该节点
 *
 *
 */
public class NonPrioritySelector extends NodeCompent {

    /**
     * 节点队列
     */
    private Queue<INode> queue = new LinkedBlockingQueue<>();

    /**
     * 上一个执行节点
     */
    private INode lastExecNode;

    @Override
    public void execute(Context context) {
        if (lastExecNode == null) {
            return;
        }

        lastExecNode.execute(context);

    }

    @Override
    public boolean condition(Context context) {

        int len= queue.size();

        for (int i=0;i<len;i++){

            INode node= queue.peek();

            if (node.condition(context)){
                lastExecNode=node;
                return true;
            }else {
                queue.offer(queue.poll());
            }

        }

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

    @Override
    public State getState(Context context) {
        return lastExecNode==null?State.STOP:lastExecNode.getState(context);
    }

    @Override
    public Object copy() {
        NonPrioritySelector nonPrioritySelector = new NonPrioritySelector();
        nonPrioritySelector.lastExecNode=null;
        nonPrioritySelector.queue=new LinkedBlockingQueue<>();

        for (INode node : queue) {
            nonPrioritySelector.queue.add((INode) node.copy());
        }
        return nonPrioritySelector;
    }
}
