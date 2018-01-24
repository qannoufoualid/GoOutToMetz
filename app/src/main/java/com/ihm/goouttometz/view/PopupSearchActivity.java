package com.ihm.goouttometz.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ihm.goouttometz.R;
import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.service.CategoryService;
import com.ihm.goouttometz.view.listener.SearchLaunchButtonListener;

import java.util.ArrayList;
import java.util.List;


public class PopupSearchActivity extends Activity {

    Spinner distance_spinner;
    Spinner category_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        distance_spinner = findViewById(R.id.spinner_distance);
        category_spinner = findViewById(R.id.spinner_category);



        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        CategoryService cs = CategoryService.getInstance(this);
        List<Category> categories = cs.getAll();
        List<String> categories_names = new ArrayList<>();
        for (Category c : categories) {
            categories_names.add(c.getLabel());
        }
        ArrayAdapter<String> cat_adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories_names);
        cat_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(cat_adapter);

        ArrayList<String> distances = new ArrayList<>();
        distances.add("100");
        distances.add("250");
        distances.add("500");
        distances.add("750");
        distances.add("1000");
        distances.add("2000");
        distances.add("5000");
        distances.add("10000");
        distances.add("20000");
        ArrayAdapter<String> dis_adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, distances);
        dis_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distance_spinner.setAdapter(dis_adapter);


        Button button_ok = findViewById(R.id.button_search_ok);

        button_ok.setOnClickListener(new SearchLaunchButtonListener(this));
    }

    public Spinner getCategory_spinner(){
        return category_spinner;
    }
    public Spinner getDistance_spinner(){
        return distance_spinner;
    }


}