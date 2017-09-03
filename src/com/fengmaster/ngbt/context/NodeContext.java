package com.fengmaster.ngbt.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng-master on 2017/8/31.
 * 行为树作用域
 */
public class NodeContext {

    private Map<Object,Object> map=new HashMap<>();

    public static NodeContext newInstance(){
        return new NodeContext();
    }

    private NodeContext(){

    }

    public Object get(Object o){
        return map.get(o);
    }

    public void put(Object key,Object val){
        map.put(key,val);
    }
}
