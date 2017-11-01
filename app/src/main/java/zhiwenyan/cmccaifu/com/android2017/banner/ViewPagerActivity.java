package zhiwenyan.cmccaifu.com.android2017.banner;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.banner.transformer.RotateDownTransformer;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.add_point)
    LinearLayout mAddPoint;
    private int[] mImgs = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c};
    private List<ImageView> mImageViews = new ArrayList<>();
    private List<ImageView> mPoints = new ArrayList<>();
    private static final int START_LOOP = 0x1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1) {
                int mCurrentPosition = mViewPager.getCurrentItem();
                mViewPager.setCurrentItem(++mCurrentPosition);
                mHandler.sendEmptyMessageDelayed(START_LOOP, 3000);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void init() {
        initImg();
        mViewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.fab_margin));
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = mImageViews.get(position % mImageViews.size());
                ViewParent vp = view.getParent();
                if (vp != null) {
                    ViewGroup parent = (ViewGroup) vp;
                    parent.removeView(view);
                }
                container.addView(view);
                return view;
            }

        });
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        mViewPager.setPageTransformer(true, new RotateDownTransformer());
        mHandler.sendEmptyMessageDelayed(0x1, 3000);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {  // 如果手指在ViewPager上不轮播
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mHandler.removeMessages(START_LOOP);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mHandler.sendEmptyMessageDelayed(START_LOOP, 3000);
                }
                return false;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mImageViews.size(); i++) {
                    if (position % mImageViews.size() == i) {
                        mPoints.get(i).setImageResource(R.drawable.v2);
                    } else {
                        mPoints.get(i).setImageResource(R.drawable.v1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initImg() {
        for (int i = 0; i < mImgs.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mImgs[i]);
            mImageViews.add(imageView);
            ImageView ponitImg = new ImageView(this);
            if (i == 0) {
                ponitImg.setImageResource(R.drawable.v2);
            } else {
                ponitImg.setImageResource(R.drawable.v1);
            }
            ponitImg.setPadding(5, 0, 5, 0);
            mAddPoint.addView(ponitImg);
            mPoints.add(ponitImg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeMessages(START_LOOP);
        }
    }
}