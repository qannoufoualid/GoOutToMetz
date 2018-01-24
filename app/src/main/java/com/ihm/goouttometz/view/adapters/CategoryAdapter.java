package com.ihm.goouttometz.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ihm.goouttometz.R;
import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.view.listener.DeleteSiteButtonListener;

import java.util.List;

/**
 * Created by ASUS on 1/19/2018.
 */
public class CategoryAdapter extends ArrayAdapter<Category> {


    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
    }
/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Site site = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_site, parent, false);
        }
        // Lookup view for data population
        TextView tvSiteName = (TextView) convertView.findViewById(R.id.tvSiteName);
        Button btnDelete = (Button) convertView.findViewById(R.id.btnDelete);

        // Populate the data into the template view using the data object
        tvSiteName.setText(site.getName());
        btnDelete.setOnClickListener(new DeleteSiteButtonListener(this, site));

        // Return the completed view to render on screen
        return convertView;
    }
*/

    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            throw new Exception("La méthode CategoryAdapter::getView a été appelé. Bordel.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LayoutInflater.from(getContext()).inflate(R.layout.item_site, parent, false);
    }
}