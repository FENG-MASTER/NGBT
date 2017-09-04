package com.fengmaster.ngbt.test.action;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsActionNode;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by qianzise on 2017/9/3.
 */
public class ActionCD extends AbsActionNode{
    private int cd=0;

    private long startTime=0;


    @Override
    public void execute(Context context) {
        long time= (long) context.getGlobalContext().get("TIME");
        startTime=time;
        context.getTreeContext();
    }

    @Override
    public boolean condition(Context context) {
        long time= (long) context.getGlobalContext().get("TIME");
        return time>=startTime+cd;
    }

    @Override
    public void init(JSONObject conf) throws JSONException {
        cd=conf.getInt("cd");
    }

    @Override
    public State getState(Context context) {
        if (startTime==0){
            return State.STOP;
        }
        long time= (long) context.getGlobalContext().get("TIME");
        return startTime+cd>= time?State.RUNNING:State.STOP;
    }

    @Override
    public Object copy() {
        ActionCD actionCD = new ActionCD();
        actionCD.cd=cd;
        actionCD.startTime=startTime;
        return actionCD;
    }
}
