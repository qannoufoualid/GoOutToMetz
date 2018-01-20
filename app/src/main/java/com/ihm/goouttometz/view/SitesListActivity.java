package com.ihm.goouttometz.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ihm.goouttometz.R;
import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.service.SiteService;
import com.ihm.goouttometz.view.adapters.SiteAdapter;

import java.util.List;

public class SitesListActivity extends AppCompatActivity {

    private SiteService siteService = SiteService.getInstance(this);
    private List<Site> sites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_list);

        sites = siteService.getAll();

        SiteAdapter itemsAdapter =
                new SiteAdapter(this, sites);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);
    }
}
