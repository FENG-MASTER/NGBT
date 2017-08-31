package com.fengmaster.ngbt.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng-master on 2017/8/31.
 *  指定节点作用域
 */
class TreeContext {

    private Map<Object,Map<Object,Object>> map=new HashMap<>();

    public static TreeContext newInstance(){
        return new TreeContext();
    }

    private TreeContext(){

    }

    public Object get(Object nodeKey,Object key){
        return map.get(nodeKey).get(key);
    }

    public void put(Object nodeKey,Object key,Object val){
        if (!map.containsKey(nodeKey)){
            map.put(nodeKey,new HashMap<>());
        }
        map.get(nodeKey).put(key,val);
    }

}
