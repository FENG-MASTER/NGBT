package com.fengmaster.ngbt.node.itf;

/**
 * Created by qianzise on 2017/9/4.
 * 可复制接口,由于{@link Cloneable}接口是protect的,不好用,这里需要的是public的,干脆自己新建一个接口
 */
public interface ICopyable {
    /**
     * 复制
     * @return 复制一个实例对象,就是克隆
     */
    Object copy();
}
