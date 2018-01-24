package com.ihm.goouttometz.view.listener;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.ihm.goouttometz.view.FormActivity;
import com.ihm.goouttometz.view.MapsActivity;

/**
 * Created by oualidqannouf on 1/23/2018.
 */

public class DisplayFormButtonListener implements OnClickListener {

    private MapsActivity activity;

    public DisplayFormButtonListener(MapsActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Log.i("INFO !! ", "Je suis dans le listener");
        if(activity.getMy_location() != null) {
            Log.i("INFO !! ", "A ce stade, la localisation n'est pas nulle");
            Intent intent = new Intent(activity, FormActivity.class);
            intent.putExtra("LongLat", activity.getLatlng());
            activity.startActivityForResult(intent, 2);
        }else{
            Toast.makeText(activity, "La localisation n'est pas disponible.", Toast.LENGTH_SHORT).show();
        }
    }
}
