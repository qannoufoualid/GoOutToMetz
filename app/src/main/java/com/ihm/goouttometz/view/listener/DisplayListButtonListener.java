package com.ihm.goouttometz.view.listener;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.ihm.goouttometz.view.MapsActivity;
import com.ihm.goouttometz.view.SitesListActivity;

/**
 * Created by oualidqannouf on 1/23/2018.
 */

public class DisplayListButtonListener implements OnClickListener {

    private Activity activity;

    public DisplayListButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(activity, SitesListActivity.class);

        activity.startActivity(intent);
    }
}
