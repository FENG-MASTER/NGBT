package com.fengmaster.ngbt.node.compent;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.INode;

import java.util.Collection;
import java.util.Random;

/**
 * Created by Feng-master on 2017/8/31.
 */
public class WeightedNode implements INode{

    public static final int DEF_WEIGHT=1;

    public int weighted=DEF_WEIGHT;
    private INode iNode;

    public static final double RANDOM_LEVEL=0.2;


    public WeightedNode(int weighted, INode node) {
        this.weighted = weighted;
        this.iNode = node;
    }

    public WeightedNode(INode node) {
        this.weighted = DEF_WEIGHT;
        this.iNode = node;
    }

    public int random(int sum){
        Random random=new Random();
        return random.nextInt((int) (sum*RANDOM_LEVEL+weighted));
    }

    @Override
    public void execute(Context context) {
        iNode.execute(context);
    }

    @Override
    public boolean condition(Context context) {
        return iNode.condition(context);
    }

    @Override
    public void addNode(INode node) {
        iNode.addNode(node);
    }

    @Override
    public void addNodes(Collection<INode> nodes) {
        iNode.addNodes(nodes);
    }
}
