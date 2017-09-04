package com.fengmaster.ngbt.test;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.factory.NodeLoader;
import com.fengmaster.ngbt.node.factory.NodePool;
import com.fengmaster.ngbt.node.itf.INode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by qianzise on 2017/9/3.
 * 例子1:宠物喂食
 */
public class PetTest {


    public static void main(String args[]) throws IOException, JSONException {
        NodeLoader.getInstance().addPackageName("com.fengmaster.ngbt.test.action");
        NodeLoader.getInstance().init();

        Scanner scanner=new Scanner(System.in);

        while (true){
            INode attackMonster = NodePool.getInstance().getNode("Pet");

            Context context=new Context();
            context.getTreeContext().put("meat",scanner.next());

            if (attackMonster.getState(context)!= IState.State.RUNNING&&attackMonster.condition(context)){
                attackMonster.execute(context);
            }


        }
    }
}
