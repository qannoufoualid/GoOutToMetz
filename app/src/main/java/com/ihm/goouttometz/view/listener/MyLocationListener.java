package com.ihm.goouttometz.view.listener;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.ihm.goouttometz.view.MapsActivity;

/**
 * Created by delemazu3u on 24/01/2018.
 */

public class MyLocationListener implements android.location.LocationListener{

    private MapsActivity activity;

    public MyLocationListener(MapsActivity a){
        activity = a;
    }

    @Override
    public void onLocationChanged(Location location) {
        activity.updateDisplayedPoints();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
