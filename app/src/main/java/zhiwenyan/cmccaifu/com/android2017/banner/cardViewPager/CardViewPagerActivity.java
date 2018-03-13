package zhiwenyan.cmccaifu.com.android2017.banner.cardViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.banner.transformer.ScaleInOutTransformer;

public class CardViewPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_pager);
        mViewPager = findViewById(R.id.viewPager);
        CardViewPagerAdapter adapter = new CardViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
//        mViewPager.setCurrentItem(adapter.getCount() / 2);
        //设置每个页面的margin
        mViewPager.setPageTransformer(true, new ScaleInOutTransformer());

    }


    private class CardViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<CardFragment> mFragments;

        public CardViewPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                mFragments.add(new CardFragment());
            }
        }

        /**
         * 每个页面的宽度的权重默认是1，在这里设置为1/3f.
         *
         * @param position
         * @return
         */
        @Override
        public float getPageWidth(int position) {
            return 1f;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object fragment = super.instantiateItem(container, position);
            mFragments.set(position, (CardFragment) fragment);
            return fragment;
        }
    }

    public class ScalePageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.70f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            Log.i("TAG", "position===: " + position);
        }
    }
}
