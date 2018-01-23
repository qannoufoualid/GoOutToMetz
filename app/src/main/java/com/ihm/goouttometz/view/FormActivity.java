package com.ihm.goouttometz.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ihm.goouttometz.R;
import com.ihm.goouttometz.view.listener.AddButtonListener;
import com.ihm.goouttometz.view.listener.GoBackButtonListener;

public class FormActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Spinner categorySpinner;
    private EditText summaryEditText;
    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameEditText = (EditText) findViewById(R.id.etName);
        latitudeEditText = (EditText) findViewById(R.id.etLatitude);
        longitudeEditText = (EditText) findViewById(R.id.etLongitude);
        summaryEditText = (EditText) findViewById(R.id.etSummary);
        addressEditText = (EditText) findViewById(R.id.etSummary);

        categorySpinner  = (Spinner) findViewById(R.id.spCategory);

        Button addButton = (Button) findViewById(R.id.btnAdd);
        Button goBackButton = (Button) findViewById(R.id.btnBack);

        addButton.setOnClickListener(new AddButtonListener(this, nameEditText, latitudeEditText, longitudeEditText, addressEditText, categorySpinner, summaryEditText));
        goBackButton.setOnClickListener(new GoBackButtonListener(this));
    }

}
