package com.ihm.goouttometz.view.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ihm.goouttometz.view.PopupSearchActivity;

/**
 * Created by delemazu3u on 19/01/2018.
 */

public class SearchLaunchButtonListener implements View.OnClickListener {

    private PopupSearchActivity act;

    public SearchLaunchButtonListener(PopupSearchActivity context){
        act = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("lol", new int[] {
                act.getCategory_spinner().getSelectedItemPosition(),
                Integer.parseInt(act.getDistance_spinner().getSelectedItem().toString())});
        act.setResult(1, intent);
        act.finish();
    }
}
