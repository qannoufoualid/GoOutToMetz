package com.ihm.goouttometz;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ihm.goouttometz.bo.Category;
import com.ihm.goouttometz.dao.CategoryDao;
import com.ihm.goouttometz.dao.DatabaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CategoryDaoInstrumentedTest {
    @Test
    public void addTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseHelper helper = DatabaseHelper.getInstance(appContext);
        CategoryDao dao = CategoryDao.getInstance(appContext);
        List<Category> categories =  dao.getAll();
        int initialSize = categories.size();

        Category category = new Category("cat1");
        dao.add(category);

        categories =  dao.getAll();
        int finalSize = categories.size();

        assertEquals(initialSize+1, finalSize);
    }



}
