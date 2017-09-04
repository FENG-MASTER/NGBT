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
 * Created by Feng-master on 2017/9/3.
 * 例子1:宠物喂食
 *
 * 这个例子展示了怎么使用 带权值的选择节点{@link com.fengmaster.ngbt.node.compent.WeightedSelector}
 * 配置出带有一定随机性的ai
 *
 * 本例子中,宠物ai会根据配置文件中的几率做出相应动作
 *
 *  吃掉肉(EatAction):喂肉给自己的孩子(FeedAction):无视肉(IgnoreAction)
 *       2          :            1             :       1
 *
 *
 *
 *
 */
public class PetTest {


    public static void main(String args[]) throws IOException, JSONException {
        //必须添加配置文件中使用的类所在包名
        NodeLoader.getInstance().addPackageName("com.fengmaster.ngbt.test.action");
        //初始化
        NodeLoader.getInstance().init();

        //模拟游戏输入
        Scanner scanner=new Scanner(System.in);

        while (true){
            //使用配置文件Pet.conf的ai
            INode petAi = NodePool.getInstance().getNode("Pet");

            //new一个上下文对象,用于传递参数给行为树节点
            Context context=new Context();
            //传入行为树上下文变量,key为meat,值为玩家输入的肉名称
            context.getTreeContext().put("meat",scanner.next());

            if (petAi.getState(context)!= IState.State.RUNNING&&petAi.condition(context)){
                //执行行为树
                petAi.execute(context);
            }


        }
    }
}
