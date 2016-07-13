package com.muzakki.ahmad.material.form;

import android.content.Context;
import android.os.Bundle;

import com.muzakki.ahmad.lib.Constant;
import com.muzakki.ahmad.lib.InternetConnection;

import org.json.JSONObject;

/**
 * Created by jeki on 6/3/16.
 */
public class FormInternetConnection extends InternetConnection {
    private final Bundle data;
    private final String table;
    private Listener listener;

    public FormInternetConnection(Context ctx, Bundle b, String table, Listener listener) {
        super(ctx);
        this.data = b;
        this.table = table;
        this.listener = listener;
    }

    @Override
    protected void onSuccess(JSONObject result) {
        Bundle newData = new Bundle();
        if(data!=null){
            for(String key:data.keySet()){
                newData.putString(key,data.getBundle(key).getString("value"));
            }
        }
        listener.onServerSuccess(result, newData);
    }

    public void insert() {
        String url = getInsertUrl();
        postMultiPart(url, data);
    }

    public String getTable(){
        return table;
    }

    protected String getInsertUrl(){
        return Constant.URL_INSERT +  "/" + table;
    }

    protected String getUpdateUrl(String id){
        return Constant.URL_UPDATE +  "/" + table + "/" +id;
    }

    public void update(String id) {
        String url = getUpdateUrl(id);
        postMultiPart(url, data);
    }

    protected String getDeleteUrl(String id){
        return Constant.URL_DELETE+"/"+table+"/"+id;
    }

    public void delete(String id){
        String url = getDeleteUrl(id);
        get(url);
    }

    @Override
    protected void onTimeout() {
        listener.onTimeout();
    }

    public interface Listener {
        void onTimeout();
        void onServerSuccess(JSONObject result, Bundle data);
    }
}
