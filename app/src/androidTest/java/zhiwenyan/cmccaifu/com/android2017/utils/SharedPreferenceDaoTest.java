package zhiwenyan.cmccaifu.com.android2017.utils;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import zhiwenyan.cmccaifu.com.android2017.BasicApp;

/**
 * Description:
 * Data：8/23/2018-11:38 AM
 *
 * @author yanzhiwen
 */
@RunWith(AndroidJUnit4.class)
public class SharedPreferenceDaoTest {

    public static final String TEST_KEY = "instrumentedTest";
    public static final String TEST_STRING = "玉刚说";

    SharedPreferenceDao spDao;

    @Before
    public void setUp() {
        spDao = new SharedPreferenceDao(BasicApp.getContext());
    }

    @Test
    public void sharedPreferenceDaoWriteRead() {
        spDao.put(TEST_KEY, TEST_STRING);
        Assert.assertEquals(TEST_STRING, spDao.get(TEST_KEY));
    }
}

