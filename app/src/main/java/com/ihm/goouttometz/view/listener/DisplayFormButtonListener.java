package com.ihm.goouttometz.view.listener;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.ihm.goouttometz.view.FormActivity;
import com.ihm.goouttometz.view.MapsActivity;

/**
 * Created by oualidqannouf on 1/23/2018.
 */

public class DisplayFormButtonListener implements OnClickListener {

    private Activity activity;

    public DisplayFormButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(activity, FormActivity.class);
        intent.putExtra("LongLat", ((MapsActivity)activity).getLatlng());
        activity.startActivityForResult(intent, 2);
    }
}
