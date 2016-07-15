package com.muzakki.ahmad.material.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by jeki on 7/12/16.
 */
public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db"; // change this
    private static final String[] TABLES = new String[]{
            /*"create table if not exists notification(id text primary key," +
                    "title text, description text,time text)"*/
    };

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for(String tb: TABLES){
            sqLiteDatabase.execSQL(tb);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*sqLiteDatabase.execSQL("drop table notification");
        sqLiteDatabase.execSQL(TABLES[0]);*/
    }

    @NonNull
    protected ArrayList<Bundle> getBundle(Cursor c){
        ArrayList<Bundle> result = new ArrayList<>();
        if(c.getCount()>0) {
            result = new ArrayList<>();
            while (c.moveToNext()) {
                Bundle row = new Bundle();
                for(int col=0;col<c.getColumnCount();col++){
                    row.putString(c.getColumnName(col),c.getString(col));
                }
                result.add(row);
            }
        }
        return result;
    }

}
