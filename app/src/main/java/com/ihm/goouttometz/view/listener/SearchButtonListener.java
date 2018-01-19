package com.ihm.goouttometz.view.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.ihm.goouttometz.view.PopupSearchActivity;

/**
 * Created by delemazu3u on 19/01/2018.
 */

public class SearchButtonListener implements View.OnClickListener {

    Context maps_context;

    public SearchButtonListener(Context context){
        maps_context = context;
    }

    @Override
    public void onClick(View view) {
        ((Activity) maps_context).startActivityForResult(new Intent(maps_context, PopupSearchActivity.class), 1);
    }
}