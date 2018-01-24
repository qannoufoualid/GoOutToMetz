package com.ihm.goouttometz.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.dao.CategoryDao;
import com.ihm.goouttometz.dao.SiteDao;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by oualidqannouf on 1/19/2018.
 */
public class SiteService {

    private static SiteService instance;
    private SiteDao dao;

    private  SiteService(Context context){
        dao = SiteDao.getInstance(context);
    }

    public static SiteService getInstance(Context context){
        if( instance == null)
            instance = new SiteService(context);
        return  instance;
    }


    public long add(Site site){
        return dao.add(site);
    }

    public List<Site> getAll() {
        return  dao.getAll();
    }

    public Site findById(long id){
        return dao.findById(id);
    }

    public void delete(Site site) {
        dao.delete(site);
    }
    public void delete(long id) {
        dao.delete(id);
    }

    public void deleteAll() {
        dao.deleteAll();
    }

    public List<Site> findSitesByCateory(long categoryId) {
        return dao.findSitesByCateory(categoryId);
    }

    public void generateData(){
        dao.persitMockData();
    }

    @Override
    public String toString() {
        return "J'existe";
    }
}
