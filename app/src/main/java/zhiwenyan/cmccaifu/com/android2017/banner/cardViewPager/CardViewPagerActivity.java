package zhiwenyan.cmccaifu.com.android2017.banner.cardViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

public class CardViewPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_pager);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.fab_margin));

        CardViewPagerAdapter adapter = new CardViewPagerAdapter(getSupportFragmentManager(), 3f);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true, new ShadowTransformer(mViewPager, adapter));
    }


    private class CardViewPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {
        private List<CardFragment> mFragments;
        private float mBaseElevation;

        public CardViewPagerAdapter(FragmentManager fm, float baseElevation) {
            super(fm);
            mFragments = new ArrayList<>();
            mBaseElevation = baseElevation;

            for (int i = 0; i < 5; i++) {
                mFragments.add(new CardFragment());
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public float getBaseElevation() {
            return mBaseElevation;
        }

        @Override
        public CardView getCardViewAt(int position) {
            return mFragments.get(position).getCardView();
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

    private class ShadowTransformer implements ViewPager.PageTransformer {
        private final float MIN_SCALE = 0.8f;


        public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
            mViewPager = viewPager;
        }


        @Override
        public void transformPage(View page, float position) {
            Log.i("TAG", "transformPage: " + position);
            if (position >= -1 && position <= 1) {
                page.setScaleY(1.0f - Math.abs(position) * (1 - MIN_SCALE));
            } else {
                page.setScaleY(MIN_SCALE);
            }


        }
    }
}
