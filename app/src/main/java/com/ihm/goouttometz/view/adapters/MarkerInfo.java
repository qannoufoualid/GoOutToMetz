package com.ihm.goouttometz.view.adapters;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.ihm.goouttometz.R;

/**
 * Created by delemazu3u on 25/01/2018.
 */

public class MarkerInfo implements GoogleMap.InfoWindowAdapter {

    private View popup = null;
    private LayoutInflater inflater;

    public MarkerInfo(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (popup == null) {
            popup = inflater.inflate(R.layout.marker_info, null);
        }
        TextView title = popup.findViewById(R.id.title);
        title.setText(marker.getTitle());

        TextView snippet = popup.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());

        return popup;
        }
}
