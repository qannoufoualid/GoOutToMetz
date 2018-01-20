package com.ihm.goouttometz.bo;


/**
 * Created by oualidqannouf on 1/18/2018.
 */
public class Category {


    /**
     * L'identifiant
     */
    private long id;
    /**
     * Le label de la catégorie
     */
    private String label;


    public Category() {

    }

    public Category(long id, String label){
        this.id = id;
        this.label = label;
    }

    public Category(String label) {
        this.label = label;
    }

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


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
