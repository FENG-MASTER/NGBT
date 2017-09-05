package com.fengmaster.ngbt.node.compent;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.compent.node.WeightedNode;
import com.fengmaster.ngbt.node.factory.NodeLoader;
import com.fengmaster.ngbt.node.itf.INode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by Feng-master on 2017/8/31.
 *
 * 带权值的选择节点（Weighted Selector）
 *
 *  我们会预先为每一个分支标注一个“权值”（Weight Value），
 * 然后当我们选择的时候，采用随机选择的方式来选，随机时会参考权值，
 * 并且保证已经被测试过的节点的不会再被测试，直到有一个节点的前提被满足，
 * 或者测试完所有的节点。
 *
 */
public class WeightedSelector extends NodeCompent {

    private List<WeightedNode> weightedNodeCompents=new ArrayList<>();

    private WeightedNode execNode;

    @Override
    public void execute(Context context) {
        if (execNode==null){
            return;
        }

        execNode.execute(context);
    }

    @Override
    public boolean condition(Context context) {

        randomList(weightedNodeCompents);

        for (WeightedNode weightedNodeCompent : weightedNodeCompents) {
            if (weightedNodeCompent.condition(context)){
                //有满足条件的
                execNode=weightedNodeCompent;
                return true;
            }
        }
        execNode=null;
        return false;
    }

    @Override
    public void addNode(INode node) {
        WeightedNode weightedNode=null;
        if (node instanceof WeightedNode){
            weightedNode= (WeightedNode) node;
        }else {
            weightedNode=new WeightedNode(node);
        }

        weightedNodeCompents.add(weightedNode);

    }

    @Override
    public void addNodes(Collection<INode> nodes) {
        nodes.forEach(this::addNode);
    }

    private void randomList(List<WeightedNode> list){
        list.forEach(weightedNode -> weightedNode.initRandom());
        list.sort(Comparator.comparingInt(o -> o.getRam()));
        Collections.reverse(list);
    }


    @Override
    public State getState(Context context) {
        return execNode==null?State.STOP:execNode.getState(context);
    }

    @Override
    public void initLeaf(JSONObject jsonObject) throws JSONException {
        INode node = NodeLoader.getInstance().createNodeByConf(jsonObject);
        int weight = jsonObject.optInt("weight",1);//获取权重
        addNode(new WeightedNode(weight,node));
    }

    @Override
    public Object copy() {
        WeightedSelector clone = new WeightedSelector();
        clone.execNode=null;
        clone.weightedNodeCompents=new LinkedList<>();
        for (WeightedNode weightedNodeCompent : weightedNodeCompents) {
            clone.weightedNodeCompents.add((WeightedNode)weightedNodeCompent.copy());

        }
        return clone;
    }
}
