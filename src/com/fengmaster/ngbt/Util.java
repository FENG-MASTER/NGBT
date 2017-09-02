package com.fengmaster.ngbt;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by qianzise on 2017/9/2.
 */
public class Util {

    public static String readFile(File file) throws IOException {
        BufferedReader  reader=new BufferedReader(new FileReader(file));
        String s=null;

        StringBuffer buffer=new StringBuffer();
        while ((s=reader.readLine())!=null){
            buffer.append(s);
        }

        return buffer.toString();

    }

    public static JSONObject readFile2Json(File file) throws JSONException, IOException {
        return new JSONObject(readFile(file));
    }

}
