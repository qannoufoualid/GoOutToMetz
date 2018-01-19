package com.ihm.goouttometz.view.listener;

import android.content.Context;
import android.view.View;

import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.service.SiteService;
import com.ihm.goouttometz.view.adapters.SiteAdapter;

/**
 * Created by ASUS on 1/19/2018.
 */

public class DeleteSiteButtonListener implements View.OnClickListener{

    private Site siteToDelete;
    private SiteService siteService;
    private SiteAdapter adapter;

    public DeleteSiteButtonListener(SiteAdapter adapter, Site siteToDelete ){
        this.siteToDelete = siteToDelete;
        this.adapter = adapter;
        siteService = SiteService.getInstance(adapter.getContext());
    }

    @Override
    public void onClick(View view) {
        siteService.delete(siteToDelete.getId());
        adapter.remove(siteToDelete);
        adapter.notifyDataSetChanged();
    }
}
