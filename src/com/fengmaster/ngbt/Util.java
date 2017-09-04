package com.fengmaster.ngbt;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by qianzise on 2017/9/2.
 * 工具类
 */
public class Util {

    /**
     * 读取文件文本
     * @param file 文件
     * @return 文件文本
     * @throws IOException exception
     */
    public static String readFile(File file) throws IOException {
        BufferedReader  reader=new BufferedReader(new FileReader(file));
        String s=null;

        StringBuffer buffer=new StringBuffer();
        while ((s=reader.readLine())!=null){
            buffer.append(s);
        }

        return buffer.toString();

    }

    /**
     * 读取json文件
     * @param file 文件
     * @return 文件json
     * @throws JSONException exception
     * @throws IOException exception
     */
    public static JSONObject readFile2Json(File file) throws JSONException, IOException {
        return new JSONObject(readFile(file));
    }

}
