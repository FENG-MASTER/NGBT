package com.fengmaster.ngbt.node.itf;

import com.fengmaster.ngbt.context.Context;

/**
 * Created by qianzise on 2017/9/3.
 * 状态接口
 */
public interface IState {

    enum State{
        RUNNING,STOP
    }

    State getState(Context context);

}
