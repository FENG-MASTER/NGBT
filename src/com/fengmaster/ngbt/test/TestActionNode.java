package com.fengmaster.ngbt.test;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsActionNode;
import com.fengmaster.ngbt.node.itf.INode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Created by qianzise on 2017/9/3.
 */
public class TestActionNode extends AbsActionNode {
    @Override
    public void execute(Context context) {
        System.out.println("yes!");
    }

    @Override
    public boolean condition(Context context) {
        return true;
    }

    @Override
    public void init(JSONObject conf) throws JSONException {

    }


    @Override
    public State getState(Context context) {
        return State.STOP;
    }
}
