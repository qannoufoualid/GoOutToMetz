package com.ihm.goouttometz.dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ihm.goouttometz.goouttometz.bo.Site;

import java.util.List;

/**
 * Created by oualidqannouf on 1/19/2018.
 */
public interface SiteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Site site);

    @Query("SELECT * FROM site WHERE categoryId=:categoryId")
    List<Site> findSitesByCategory(int categoryId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Site site);

    @Query("delete from site where id = :id")
    void delete(long id);
}
