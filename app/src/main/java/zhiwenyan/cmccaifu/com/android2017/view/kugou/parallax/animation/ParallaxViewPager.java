package zhiwenyan.cmccaifu.com.android2017.view.kugou.parallax.animation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.view.kugou.ParallaxFragment;

/**
 * Created by yanzhiwen on 2017/8/28.
 */

public class ParallaxViewPager extends ViewPager {
    private List<Fragment> mFragments = new ArrayList<>();

    public ParallaxViewPager(Context context) {
        this(context, null);
    }

    public ParallaxViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLayout(FragmentManager fm, int[] layoutIds) {
        mFragments.clear();
        for (int layoutId : layoutIds) {
            ParallaxFragment fragment = new ParallaxFragment();
            Bundle b = new Bundle();
            b.putInt(ParallaxFragment.LAYOUT_ID_KEY, layoutId);
            fragment.setArguments(b);
            mFragments.add(fragment);
        }
        ParallaxPagerAdapter adapter = new ParallaxPagerAdapter(fm);
        setAdapter(adapter);
    }

    public class ParallaxPagerAdapter extends FragmentPagerAdapter {

        public ParallaxPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
