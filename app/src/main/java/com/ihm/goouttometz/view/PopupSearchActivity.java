package com.ihm.goouttometz.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Button;

import com.ihm.goouttometz.R;
import com.ihm.goouttometz.view.listener.SearchLauchListener;



public class PopupSearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        Button button_ok = findViewById(R.id.button_search_ok);

        button_ok.setOnClickListener(new SearchLauchListener(this));
    }


}