package com.ihm.goouttometz.view.listener;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.*;
import android.widget.EditText;
import android.widget.Spinner;

import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.service.SiteService;
import com.ihm.goouttometz.view.FormActivity;
import com.ihm.goouttometz.view.MapsActivity;

/**
 * Created by oualidqannouf on 1/23/2018.
 */

public class AddButtonListener implements OnClickListener {

    private SiteService siteService;

    private Activity activity;
    private EditText nameEditText;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Spinner categorySpinner;
    private EditText summaryEditText;
    private EditText addressEditText;

    public AddButtonListener(Activity activity, EditText nameEditText, EditText latitudeEditText, EditText longitudeEditText, EditText addressEditText, Spinner categorySpinner, EditText summaryEditText) {
        this.activity = activity;
        this.nameEditText = nameEditText;
        this.latitudeEditText = latitudeEditText;
        this.longitudeEditText = longitudeEditText;
        this.categorySpinner = categorySpinner;
        this.summaryEditText = summaryEditText;
        this.addressEditText = addressEditText;

        siteService = SiteService.getInstance(activity);
    }

    @Override
    public void onClick(View view) {

        Site site = new Site(nameEditText.getText().toString(), Float.valueOf(latitudeEditText.getText().toString()), Float.valueOf(longitudeEditText.getText().toString()), addressEditText.getText().toString(), categorySpinner.getSelectedItemId(), summaryEditText.getText().toString());
        siteService.add(site);
        Intent intent = new Intent(activity, MapsActivity.class);

        activity.startActivity(intent);
    }
}
