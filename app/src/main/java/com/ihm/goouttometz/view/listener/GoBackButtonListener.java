package com.ihm.goouttometz.view.listener;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;

import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.service.SiteService;
import com.ihm.goouttometz.view.MapsActivity;

/**
 * Created by oualidqannouf on 1/23/2018.
 */

public class GoBackButtonListener implements OnClickListener {

    private Activity activity;

    public GoBackButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(activity, MapsActivity.class);

        activity.startActivity(intent);
    }
}
