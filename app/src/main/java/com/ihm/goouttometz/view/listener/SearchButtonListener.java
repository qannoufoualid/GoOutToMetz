package com.ihm.goouttometz.view.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ihm.goouttometz.view.MapsActivity;
import com.ihm.goouttometz.view.PopupSearchActivity;

/**
 * Created by delemazu3u on 19/01/2018.
 */

public class SearchButtonListener implements View.OnClickListener {

    MapsActivity maps_activity;

    public SearchButtonListener(MapsActivity context){
        maps_activity = context;
    }

    @Override
    public void onClick(View view) {

            Intent intent = new Intent(maps_activity, PopupSearchActivity.class);
            maps_activity.startActivityForResult(intent, 1);

    }
}
