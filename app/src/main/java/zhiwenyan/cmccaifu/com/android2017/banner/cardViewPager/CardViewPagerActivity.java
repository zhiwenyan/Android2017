package zhiwenyan.cmccaifu.com.android2017.banner.cardViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
        mViewPager.setOffscreenPageLimit(3);

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

    private class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

        private ViewPager mViewPager;
        private CardAdapter mAdapter;
        private float mLastOffset;
        private boolean mScalingEnabled;
        private final float MIN_SCALE = 0.8f;


        public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
            mViewPager = viewPager;
            viewPager.addOnPageChangeListener(this);
            mAdapter = adapter;
        }

        public void enableScaling(boolean enable) {
            if (mScalingEnabled && !enable) {
                // shrink main card
                CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
                if (currentCard != null) {
                    currentCard.animate().scaleY(1);
                    currentCard.animate().scaleX(1);
                }
            } else if (!mScalingEnabled && enable) {
                // grow main card
                CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
                if (currentCard != null) {
                    currentCard.animate().scaleY(1.1f);
                    currentCard.animate().scaleX(1.1f);
                }
            }

            mScalingEnabled = enable;
        }

        @Override
        public void transformPage(View page, float position) {
            if (position >= -1 && position <= 1) {
                page.setScaleY(1.0f - Math.abs(position) * (1 - MIN_SCALE));
            } else {
                page.setScaleY(MIN_SCALE);
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            int realCurrentPosition;
//            int nextPosition;
//            float baseElevation = mAdapter.getBaseElevation();
//            float realOffset;
//            boolean goingLeft = mLastOffset > positionOffset;
//            if (goingLeft) {
//                realCurrentPosition = position + 1;
//                nextPosition = position;
//                realOffset = 1 - positionOffset;
//            } else {
//                nextPosition = position + 1;
//                realCurrentPosition = position;
//                realOffset = positionOffset;
//            }
//            if (nextPosition > mAdapter.getCount() - 1
//                    || realCurrentPosition > mAdapter.getCount() - 1) {
//                return;
//            }
//            CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);
//            if (currentCard != null) {
//                if (mScalingEnabled) {
//                    currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
//                    currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
//                }
//                currentCard.setCardElevation((baseElevation + baseElevation
//                        * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
//            }
//            CardView nextCard = mAdapter.getCardViewAt(nextPosition);
//            if (nextCard != null) {
//                if (mScalingEnabled) {
//                    nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
//                    nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
//                }
//                nextCard.setCardElevation((baseElevation + baseElevation
//                        * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
//            }
//            mLastOffset = positionOffset;

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
