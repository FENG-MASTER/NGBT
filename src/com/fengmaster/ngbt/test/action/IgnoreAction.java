package com.fengmaster.ngbt.test.action;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsActionNode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Feng-master on 2017/9/5.
 * 宠物 无视 动作
 */
public class IgnoreAction extends AbsActionNode{
    @Override
    public void execute(Context context) {
        String meat = (String) context.getTreeContext().get("meat");
        if (meat==null){
            return ;
        }
        System.out.println("宠物无视了"+meat);
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
