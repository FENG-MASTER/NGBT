package com.fengmaster.ngbt.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng-master on 2017/8/31.
 * 行为树上下文,整个行为树共享
 */
public class TreeContext {

    private Map<Object,Object> map=new HashMap<>();

    public static TreeContext newInstance(){
        return new TreeContext();
    }

    private TreeContext(){

    }

    public Object get(Object o){
        return map.get(o);
    }


    public void put(Object key,Object val){
        map.put(key,val);
    }

    public void remove(Object key){
        if (map.containsKey(key)){
            map.remove(key);
        }
    }
}
