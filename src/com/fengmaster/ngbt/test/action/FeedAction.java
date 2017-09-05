package com.fengmaster.ngbt.test.action;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsActionNode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Feng-master on 2017/9/5.
 * 宠物 喂食 动作
 */
public class FeedAction extends AbsActionNode{

    private static final int DEF_NUM=4;

    private int childrenNum;
    private int feedWho=1;

    @Override
    public void execute(Context context) {
        String meat = (String) context.getTreeContext().get("meat");
        if (meat==null){
            return;
        }
        System.out.println("宠物把"+meat+"喂给了自己第"+feedWho+"个孩子");
        feedWho++;
        if (feedWho>childrenNum){
            feedWho=0;
        }
    }

    @Override
    public boolean condition(Context context) {
        if (feedWho==0){
            feedWho++;
            return false;
        }
        String meat = (String) context.getTreeContext().get("meat");
        if (meat==null){
            return false;
        }
        return true;
    }

    @Override
    public State getState(Context context) {
        return State.STOP;
    }

    @Override
    public void init(JSONObject conf) throws JSONException {
        childrenNum=conf.optInt("childrenNum",DEF_NUM);
    }
}
