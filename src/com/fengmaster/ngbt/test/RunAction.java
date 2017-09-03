package com.fengmaster.ngbt.test;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsActionNode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by qianzise on 2017/9/3.
 */
public class RunAction extends AbsActionNode {
    @Override
    public void execute(Context context) {
        System.out.print("3");
    }

    @Override
    public boolean condition(Context context) {
        return true;
    }

    @Override
    public State getState(Context context) {
        return State.STOP;
    }

    @Override
    public void init(JSONObject conf) throws JSONException {

    }
}
