package com.ihm.goouttometz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ihm.goouttometz.bo.Site;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by oualidqannouf on 1/19/2018.
 */

public class SiteDao {

    private static SiteDao instance;

    private static final String TABLE_SITE = "site";
    private DatabaseHelper databaseHelper;

    private String name;
    private float latitude;
    private float longitude;
    private String address;
    private int categoryId;
    private String summary;
    // Sites Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_SUMMARY = "summary";
    private static final String KEY_CATEGORY_ID = "categoryId";


    private static final String[] COLUMNS = {KEY_ID,KEY_NAME, KEY_LATITUDE, KEY_LONGITUDE, KEY_ADDRESS, KEY_SUMMARY, KEY_CATEGORY_ID};


    private SiteDao(Context context){
        this.databaseHelper = DatabaseHelper.getInstance(context);
    }

    public static SiteDao getInstance(Context context){
        if(instance == null)
            instance = new SiteDao(context);
        return  instance;
    }
    public long add(Site site){
        Log.d("addSite", site.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, site.getName());
        values.put(KEY_LATITUDE, site.getLatitude());
        values.put(KEY_LONGITUDE, site.getLongitude());
        values.put(KEY_ADDRESS, site.getAddress());
        values.put(KEY_CATEGORY_ID, site.getCategoryId());
        values.put(KEY_SUMMARY, site.getSummary());

        // 3. insert
        long insertedId = db.insert(TABLE_SITE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

        return  insertedId;
    }


    // Get All sites
    public List<Site> getAll() {
        List<Site> sites = new LinkedList<Site>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SITE;

        // 2. get reference to writable DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build site and add it to list
        Site site = null;
        if (cursor.moveToFirst()) {
            do {
                site = new Site();
                site.setId(Integer.parseInt(cursor.getString(0)));
                site.setName(cursor.getString(1));
                site.setLatitude(cursor.getFloat(2));
                site.setLongitude(cursor.getFloat(3));
                site.setAddress(cursor.getString(4));
                site.setSummary(cursor.getString(5));
                site.setCategoryId(cursor.getLong(6));

                // Add site to sites
                sites.add(site);
            } while (cursor.moveToNext());
        }

        Log.d("SiteDao.getAll()", sites.toString());

        // return sites
        return sites;
    }


    public Site findById(long id){

        // 1. get reference to readable DB
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_SITE, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build site object
        Site site = new Site();
        site.setId(Integer.parseInt(cursor.getString(0)));
        site.setName(cursor.getString(1));
        site.setLatitude(cursor.getFloat(2));
        site.setLongitude(cursor.getFloat(3));
        site.setAddress(cursor.getString(4));
        site.setSummary(cursor.getString(5));
        site.setCategoryId(cursor.getLong(6));


        //log
        Log.d("SiteDao.findById("+id+")", site.toString());

        // 5. return site
        return site;
    }

    public void delete(Site site) {

        // 1. get reference to writable DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SITE, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(site.getId()) }); //selections args

        // 3. close
        db.close();

        //log
        Log.d("SiteDao.delete", site.toString());

    }
    public void delete(long id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SITE, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(id) }); //selections args

        // 3. close
        db.close();

        //log
        Log.d("SiteDao.delete", String.valueOf(id));

    }

    public void deleteAll() {

        // 1. get reference to writable DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SITE, //table name
                null,  // selections
                null); //selections args

        // 3. close
        db.close();

        //log
        Log.d("SiteDao.deleteAll", "");

    }


    public List<Site> findSitesByCateory(long categoryId) {

        List<Site> sites = new LinkedList<Site>();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor =
                db.query(TABLE_SITE, // a. table
                        COLUMNS, // b. column names
                        " categoryId = ?", // c. selections
                        new String[] { String.valueOf(categoryId) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit


        Site site = null;
        if (cursor.moveToFirst()) {
            do {
                site = new Site();
                site.setId(Integer.parseInt(cursor.getString(0)));
                site.setName(cursor.getString(1));
                site.setLatitude(cursor.getFloat(2));
                site.setLongitude(cursor.getFloat(3));
                site.setAddress(cursor.getString(4));
                site.setSummary(cursor.getString(5));
                site.setCategoryId(cursor.getLong(6));

                // Add site to sites
                sites.add(site);
            } while (cursor.moveToNext());
        }

        Log.d("findSitesByCateory()", sites.toString());

        // return sites
        return sites;

    }

}
