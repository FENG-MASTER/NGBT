package com.fengmaster.ngbt.node.compent;

import com.fengmaster.ngbt.context.Context;
import com.fengmaster.ngbt.node.itf.INode;
import com.fengmaster.ngbt.node.itf.IState;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.fengmaster.ngbt.node.compent.Sequence.StateBackType.AND;
import static com.fengmaster.ngbt.node.compent.Sequence.StateBackType.OR;

/**
 * Created by Feng-master on 2017/9/5.
 * <p>
 * 顺序节点,所有子节点都满足条件后,才会一起执行
 * <p>
 * 有两种状态返回模式:
 * 1. 所有子节点状态都是STOP,才返回状态STOP,否则都返回RUNNING
 * 2. 有一个子节点状态是STOP,则返回状态STOP
 */
public class Sequence extends NodeCompent {

    private StateBackType stateType = AND;//默认and模式

    private List<INode> iNodeList=new ArrayList<>();

    @Override
    public boolean condition(Context context) {
        return iNodeList.stream().allMatch(iNode -> iNode.condition(context));
    }

    @Override
    public State getState(Context context) {
        switch (stateType){
            case OR:
                if (iNodeList.stream().anyMatch(iNode -> iNode.getState(context)==State.STOP)){
                    return State.STOP;
                }else {
                    return State.RUNNING;
                }
            case AND:
            default:
                //默认都是AND

                if (iNodeList.stream().allMatch(iNode -> iNode.getState(context)==State.STOP)){
                    return State.STOP;
                }else {
                    return State.RUNNING;
                }
        }

    }

    @Override
    public Object copy() {
        return null;
    }

    @Override
    public void addNode(INode node) {
        iNodeList.add(node);
    }

    @Override
    public void addNodes(Collection<INode> nodes) {
        iNodeList.addAll(nodes);
    }

    @Override
    public void init(JSONObject conf) throws JSONException {
        super.init(conf);
        String type = conf.optString("type", "AND").toUpperCase();
        switch (type) {
            case "AND":
                stateType = AND;
                break;
            case "OR":
                stateType = OR;
                break;
            default:
                stateType = AND;
        }

    }

    @Override
    public void execute(Context context) {
        iNodeList.forEach(iNode -> iNode.execute(context));
    }

    /**
     * 状态返回模式枚举
     */
    enum StateBackType {
        AND, OR
    }
}
