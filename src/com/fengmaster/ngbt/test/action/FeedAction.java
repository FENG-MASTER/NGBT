package com.fengmaster.ngbt.test.action;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsActionNode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by qianzise on 2017/9/5.
 */
public class FeedAction extends AbsActionNode{
    @Override
    public void execute(Context context) {
        String meat = (String) context.getTreeContext().get("meat");
        if (meat==null){
            return;
        }
        System.out.println("宠物把"+meat+"喂给了自己的孩子");
    }

    @Override
    public boolean condition(Context context) {
        String meat = (String) context.getTreeContext().get("meat");
        if (meat==null){
            return false;
        }
        return true;
    }

    @Override
    public State getState(Context context) {
        return null;
    }

    @Override
    public void init(JSONObject conf) throws JSONException {

    }
}
