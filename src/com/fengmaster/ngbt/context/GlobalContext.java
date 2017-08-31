package com.fengmaster.ngbt.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng-master on 2017/8/31.
 * 全局作用域
 */
class GlobalContext {

    private static GlobalContext instance;

    public static GlobalContext getInstance(){
        if (instance==null){
            instance=new GlobalContext();
        }
        return instance;
    }


    private GlobalContext(){

    }

    /**
     * 全局作用域map
     */
    private Map<Object,Object> map=new HashMap<>();

    public Object get(Object o){
        return map.get(o);
    }

    public void put(Object key,Object val){
        map.put(key,val);
    }
}
