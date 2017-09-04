package com.fengmaster.ngbt.test;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.factory.NodeLoader;
import com.fengmaster.ngbt.node.factory.NodePool;
import com.fengmaster.ngbt.node.itf.INode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by qianzise on 2017/9/3.
 */
public class Main {


    public static void main(String args[]) throws IOException, JSONException {
        NodeLoader.getInstance().addPackageName("com.fengmaster.ngbt.test");
        NodeLoader.getInstance().init();


        while (true){


            INode attackMonster = NodePool.getInstance().getNode("AttackMonster");
            INode attackMonster2 = (INode) NodePool.getInstance().getNode("AttackMonster").copy();
            Context context=new Context();
            context.getNodeContext().put("ActionCD","key1","123");
            context.getGlobalContext().put("TIME",System.currentTimeMillis());

            if (attackMonster2.getState(context)!= IState.State.RUNNING&&attackMonster2.condition(context)){
                attackMonster2.execute(context);
            }


        }
    }
}
