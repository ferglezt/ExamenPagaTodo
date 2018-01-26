package com.ferglezt.examenpagatodo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ferglezt.examenpagatodo.Config;

/**
 * Created by fernando on 26/01/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static DatabaseHandler instance;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = Config.DB_NAME;

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {
        if(instance == null)
            instance = new DatabaseHandler(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
