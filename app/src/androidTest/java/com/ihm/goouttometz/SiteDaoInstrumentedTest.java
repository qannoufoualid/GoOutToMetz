package com.ihm.goouttometz;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.bo.Site;
import com.ihm.goouttometz.dao.CategoryDao;
import com.ihm.goouttometz.dao.SiteDao;
import com.ihm.goouttometz.dao.DatabaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SiteDaoInstrumentedTest {
    @Test
    public void addTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseHelper helper = DatabaseHelper.getInstance(appContext);
        SiteDao dao = SiteDao.getInstance(appContext);
        List<Site> sites =  dao.getAll();
        int initialSize = sites.size();

        Site site = new Site("site X", 20, 20, "Address X",1, "summary");
        dao.add(site);

        sites =  dao.getAll();
        int finalSize = sites.size();

        assertEquals(initialSize+1, finalSize);
    }

    @Test
    public void findByIdTest() throws Exception {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseHelper helper = DatabaseHelper.getInstance(appContext);
        SiteDao dao = SiteDao.getInstance(appContext);

        Site site = new Site("site X", 20, 20, "Address X",1, "summary");
        long insertedId = dao.add(site);

        Site foundSite = dao.findById(insertedId);

        assertEquals(site.getName(), foundSite.getName());
    }


    @Test
    public void deleteTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseHelper helper = DatabaseHelper.getInstance(appContext);
        SiteDao dao = SiteDao.getInstance(appContext);
        List<Site> sites =  dao.getAll();
        int initialSize = sites.size();

        Site site = new Site("site X", 20, 20, "Address X",1, "summary");
        long insertedId = dao.add(site);
        dao.delete(insertedId);

        sites =  dao.getAll();
        int finalSize = sites.size();

        assertEquals(initialSize, finalSize);
    }

    @Test
    public void findSitesByCategoryTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseHelper helper = DatabaseHelper.getInstance(appContext);
        SiteDao dao = SiteDao.getInstance(appContext);
        CategoryDao categoryDao = CategoryDao.getInstance(appContext);
        long insertedIdCategory = categoryDao.add(new Category("Cafe"));


        Site site1 = new Site("site X1", 20, 20, "Address X",insertedIdCategory, "summary");
        long insertedId1 = dao.add(site1);
        Site site2 = new Site("site X2", 20, 20, "Address X",insertedIdCategory, "summary");
        long insertedId2 = dao.add(site2);

        List<Site> sites = dao.findSitesByCateory(insertedIdCategory);

        assertTrue(sites.size()==2 && sites.get(0).getId() == insertedId1 && sites.get(1).getId() == insertedId2);
    }







}
