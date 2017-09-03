package com.fengmaster.ngbt.node.itf;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Created by Feng-master on 2017/8/22.
 * 节点接口
 */
public interface INode extends ICondition, IExecute,IState {

    /**
     * 初始化
     * @param conf 配置
     */
    void init(JSONObject conf) throws JSONException;

    /**
     * 增加子节点
     * @param node 节点
     */
    void addNode(INode node);

    /**
     * 增加多个子节点
     * @param nodes 节点
     */
    void addNodes(Collection<INode> nodes);


}
