package com.fengmaster.ngbt.test;

import com.fengmaster.ngbt.node.factory.NodeLoader;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by qianzise on 2017/9/3.
 */
public class Main {


    public static void main(String args[]) throws IOException, JSONException {
        NodeLoader.getInstance().addPackageName("com.fengmaster.ngbt.test");
        NodeLoader.getInstance().init();
    }
}
