package com.beginner.example3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sahai613 on 01-12-2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    /**SQL CREATE TABLE
     * */

    private static final int DATABASE_VERSION=1;
    private static final String TEXT_TYPE=" TEXT NOT NULL";
     private static final String CREATE_POST_TABLE="CREATE TABLE " + DatabaseContract.PostTable.Table_Name + " ( " +
            DatabaseContract.PostTable.Title + TEXT_TYPE +", "+
            DatabaseContract.PostTable.link + TEXT_TYPE+", "+
            DatabaseContract.PostTable.Imagelink + TEXT_TYPE +" ); " ;
    public static final String DROP_POST_TABLE=" DROP TABLE IF EXISTS "+DatabaseContract.PostTable.Table_Name;

    public DatabaseOpenHelper(Context context) {
        super(context, DatabaseContract.DB_Name, null, DATABASE_VERSION);
        Log.d("DB operations","DB Created 1st step");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POST_TABLE);
        Log.d("DB operations","DB Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_POST_TABLE);
        onCreate(db);
        Log.d("DB operations","DB Updated");
    }


}
