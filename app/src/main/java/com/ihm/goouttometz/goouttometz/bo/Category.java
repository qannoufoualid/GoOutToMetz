package com.ihm.goouttometz.goouttometz.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by oualidqannouf on 1/18/2018.
 */
@Entity(tableName = "category")
public class Category {


    /**
     * L'identifiant
     */
    @PrimaryKey(autoGenerate = true)
    private long id;
    /**
     * Le label de la catégorie
     */
    private String label;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
