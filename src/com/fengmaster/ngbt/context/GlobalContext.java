package com.fengmaster.ngbt.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng-master on 2017/8/31.
 * 全局作用域,所有行为树共享
 */
public class GlobalContext {

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

    /**
     * 获得全局变量
     * @param o 变量key
     * @return 变量
     */
    public Object get(Object o){
        return map.get(o);
    }

    /**
     * 添加全局变量
     * @param key 变量key
     * @param val 变量
     */
    public void put(Object key,Object val){
        map.put(key,val);
    }

    public void remove(Object key){
        if (map.containsKey(key)){
            map.remove(key);
        }
    }
}
