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
 * 例子1:宠物喂食随机动作(pet.conf)
 *
 * 例子展示了怎么使用 带权值的选择节点{@link com.fengmaster.ngbt.node.compent.WeightedSelector}
 * 配置出带有一定随机性的ai
 *
 * 本例子中,宠物ai会根据配置文件中的几率做出相应动作
 *
 *  吃掉肉(EatAction):喂肉给自己的孩子(FeedAction):无视肉(IgnoreAction)
 *       2          :            1             :       1
 *
 * 例子2:宠物优先喂饱孩子ai(Pet2.conf)
 *
 * 这个例子展示了怎么使用 不带优先级的选择节点{@link com.fengmaster.ngbt.node.compent.NonPrioritySelector}
 *
 * 这个ai的行为表现是: 优先喂饱所有的孩子,然后再自己吃或者不吃
 *
 * 这里涉及到了 <b>不带优先级的选择节点会先选择上一次执行的节点去检查</b>
 *
 *
 * 例子3:宠物只要特定的食物(Pet3.conf)
 *
 * 这个例子展示了怎么使用 顺序节点{@link com.fengmaster.ngbt.node.compent.Sequence} 实现节点条件且判断的效果
 *
 * 这个ai的行为表现是 : 如果输入的食物不是good开头的,宠物会无视
 *
 * 如果是good开头的,宠物会吃掉或者喂自己的孩子
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
//            INode petAi = NodePool.getInstance().getNode("Pet2");
//            INode petAi = NodePool.getInstance().getNode("Pet3");


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
