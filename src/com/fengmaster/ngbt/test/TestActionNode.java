package com.fengmaster.ngbt.test;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsActionNode;
import com.fengmaster.ngbt.node.itf.INode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Created by qianzise on 2017/9/3.
 */
public class TestActionNode extends AbsActionNode {
    @Override
    public void execute(Context context) {

    }

    @Override
    public boolean condition(Context context) {
        return false;
    }

    @Override
    public void init(JSONObject conf) throws JSONException {
        int a=conf.getInt("arg");
        System.out.printf(a+"");
    }


}
