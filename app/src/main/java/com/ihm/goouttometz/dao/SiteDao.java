package com.ihm.goouttometz.dao;


import com.ihm.goouttometz.bo.Site;

import java.util.List;

/**
 * Created by oualidqannouf on 1/19/2018.
 */
public interface SiteDao {

    void add(Site site);

    List<Site> findSitesByCategory(int categoryId);

    void update(Site site);

    void delete(long id);
}
