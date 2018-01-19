package com.ihm.goouttometz.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oualidqannouf on 1/19/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static DatabaseHelper instance = null;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "gooutmetzdb";


    public static DatabaseHelper getInstance(Context context){
        if (instance==null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CATEGORY_TABLE = "CREATE TABLE category ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "label TEXT)";

        String CREATE_SITE_TABLE = "CREATE TABLE site ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "latitude REAL," +
                "longitude REAL," +
                "address TEXT," +
                "summary TEXT," +
                "categoryId INTEGER," +
                "FOREIGN KEY (categoryId) REFERENCES category(id) )";



        // create tables
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_SITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS gooutmetzdb");

        // create fresh books table
        this.onCreate(db);
    }

}
