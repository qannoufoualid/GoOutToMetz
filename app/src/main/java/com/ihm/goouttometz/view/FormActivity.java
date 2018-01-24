package com.ihm.goouttometz.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ihm.goouttometz.R;
import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.service.CategoryService;
import com.ihm.goouttometz.view.listener.AddButtonListener;
import com.ihm.goouttometz.view.listener.GoBackButtonListener;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Spinner  categorySpinner;
    private EditText summaryEditText;
    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameEditText = findViewById(R.id.etName);
        latitudeEditText = findViewById(R.id.etLatitude);
        longitudeEditText = findViewById(R.id.etLongitude);
        summaryEditText = findViewById(R.id.etSummary);
        addressEditText = findViewById(R.id.etSummary);

        categorySpinner  = findViewById(R.id.spCategory);

        Button addButton = findViewById(R.id.btnAdd);
        Button goBackButton = findViewById(R.id.btnBack);



        CategoryService cs = CategoryService.getInstance(this);
        List<Category> categories = cs.getAll();
        List<String> categories_names = new ArrayList<>();
        for (Category c : categories) {
            categories_names.add(c.getLabel());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(adapter);


        addButton.setOnClickListener(new AddButtonListener(this, nameEditText, latitudeEditText, longitudeEditText, addressEditText, categorySpinner, summaryEditText));
        goBackButton.setOnClickListener(new GoBackButtonListener(this));
    }

}
