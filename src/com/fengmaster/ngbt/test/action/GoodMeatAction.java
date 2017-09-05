package com.fengmaster.ngbt.test.action;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.AbsConditionNode;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Feng-master on 2017/9/5.
 */
public class GoodMeatAction extends AbsConditionNode {
    @Override
    public boolean condition(Context context) {
        String meat = (String) context.getTreeContext().get("meat");
        return meat.toLowerCase().startsWith("good");
    }

    @Override
    public void init(JSONObject conf) throws JSONException {

    }
}
