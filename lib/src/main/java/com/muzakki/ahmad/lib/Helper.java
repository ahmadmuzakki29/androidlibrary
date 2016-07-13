package com.muzakki.ahmad.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by jeki on 7/12/16.
 */
public class Helper {

    public static Bundle JSONtoBundle(JSONObject obj)throws JSONException{
        Iterator<String> iter = obj.keys();
        Bundle b = new Bundle();
        while(iter.hasNext()){
            String key = iter.next();
            String val = obj.getString(key);
            b.putString(key,val);
        }
        return b;
    }

    public static JSONArray getJSONData(String resp) throws JSONException,NullPointerException{
        JSONObject obj = new JSONObject(resp);
        return getJSONData(obj);
    }

    public static JSONArray getJSONData(JSONObject obj) throws NullPointerException{
        try {
            return obj.getJSONArray("Data");
        }catch (JSONException e){
            try{
                return obj.getJSONArray("data");
            }catch (JSONException ex){
                return null;
            }
        }
    }

    public static String randomString() {
        return RandomStringUtils.randomAlphanumeric(30);
    }

    public static String decodeDate(String date) {
        String[] d = date.split("-");
        return d[2]+"/"+d[1]+"/"+d[0];
    }

    public static int getPxFromDp(int dp, Resources r){
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }

    public static void setPref(Context ctx, String key, String value){
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                Constant.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static void setPref(Context ctx, String key, Boolean value){
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                Constant.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static String getPrefString(Context ctx, String key){
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                Constant.PREF, Context.MODE_PRIVATE);
        return sharedPref.getString(key,null);
    }

    public static Boolean getPrefBoolean(Context ctx, String key){
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                Constant.PREF, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key,false);
    }

    public static void sendLog(Context context, String message) {
        // do send log
    }
}
