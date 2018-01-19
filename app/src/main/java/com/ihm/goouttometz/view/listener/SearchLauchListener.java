package com.ihm.goouttometz.view.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by delemazu3u on 19/01/2018.
 */

public class SearchLauchListener implements View.OnClickListener {

    Context maps_context;

    public SearchLauchListener(Context context){
        maps_context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("lol", new String[] {"Bring Sajuuk", " to bear !!"});
        ((Activity)maps_context).setResult(1, intent);
        ((Activity) maps_context).finish();
    }
}
