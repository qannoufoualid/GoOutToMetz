package com.ihm.goouttometz.goouttometz.dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ihm.goouttometz.goouttometz.bo.Category;

import java.util.List;

/**
 * Created by oualidqannouf on 1/19/2018.
 */

public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void add(Category category);

    @Query("select * from category")
    public List<Category> getAll();

    @Query("select * from category where id = :categoryid")
    public List<Category> findById(long categoryid);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Category category);

    @Query("delete from category")
    void removeAll();

}
