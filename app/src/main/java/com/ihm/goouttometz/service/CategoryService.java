package com.ihm.goouttometz.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.dao.CategoryDao;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by oualidqannouf on 1/19/2018.
 */

public class CategoryService {

    private static CategoryService instance;
    private CategoryDao dao;

    private  CategoryService(Context context){
        dao = CategoryDao.getInstance(context);
    }

    public static CategoryService getInstance(Context context){
        if( instance == null)
            instance = new CategoryService(context);
        return  instance;
    }

    public long add(Category category){
        return dao.add(category);
    }

    public List<Category> getAll() {
        return dao.getAll();
    }

    public void generateData(){
        dao.persitMockData();
    }

}
