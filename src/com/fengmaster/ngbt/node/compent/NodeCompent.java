package com.fengmaster.ngbt.node.compent;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.factory.NodeLoader;
import com.fengmaster.ngbt.node.itf.INode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Created by Feng-master on 2017/9/3.
 * 容器节点基类
 */
public abstract class NodeCompent implements INode {

    @Override
    public void init(JSONObject conf) throws JSONException {
        JSONArray leafs = conf.getJSONArray("leafs");
        for (int i=0;i<leafs.length();i++){
            initLeaf(leafs.getJSONObject(i));
        }

    }

    public void initLeaf(JSONObject jsonObject) throws JSONException {
        INode node = NodeLoader.getInstance().createNodeByConf(jsonObject);
        addNode(node);
    }


}
