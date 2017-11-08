package com.example.eddielee.copywyxwelm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eddie on 2017/10/25.
 */

public class Databases extends SQLiteOpenHelper {

    private static final String CREATE_DATABASE = "create table ClassNameInfo(" +
            "id integer primary key autoincrement," +
            "className text," +
            "classID text)";

    public Databases(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
