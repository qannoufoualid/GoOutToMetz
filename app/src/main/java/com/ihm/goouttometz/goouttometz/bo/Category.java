package com.ihm.goouttometz.goouttometz.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by oualidqannouf on 1/18/2018.
 */
@Entity
public class Category {


    /**
     * L'identifiant
     */
    @PrimaryKey
    private int id;
    /**
     * Le label de la catégorie
     */
    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
