package com.ihm.goouttometz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ihm.goouttometz.bo.Category;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by oualidqannouf on 1/19/2018.
 */

public class CategoryDao {

    private static CategoryDao instance;

    private static final String TABLE_CATEGORY = "category";
    private DatabaseHelper databaseHelper;

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LABEL = "label";

    private static final String[] COLUMNS = {KEY_ID,KEY_LABEL};

    private CategoryDao(Context context){
        this.databaseHelper = DatabaseHelper.getInstance(context);
    }
    public static CategoryDao getInstance(Context context){
        if(instance == null)
            instance = new CategoryDao(context);
        return instance;
    }
    public long add(Category category){
        Log.d("addBook", category.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_LABEL, category.getLabel());

        // 3. insert
        long insertId = db.insert(TABLE_CATEGORY, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

        return insertId;
    }


    // Get All categories
    public List<Category> getAll() {
        List<Category> categories = new LinkedList<Category>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CATEGORY;

        // 2. get reference to writable DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build category and add it to list
        Category category = null;
        if (cursor.moveToFirst()) {
            do {
                category = new Category();
                category.setId(Integer.parseInt(cursor.getString(0)));
                category.setLabel(cursor.getString(1));

                // Add category to categories
                categories.add(category);
            } while (cursor.moveToNext());
        }

        Log.d("CategoryDao.getAll()", categories.toString());

        // return books
        return categories;
    }

    public List<Category> generateData(){
        List<Category>///
        ...

        return list;
    }

    public persitMockData(){
        for(Category cat : generateData()){
            add(cat);
        }
    }


}
