package zhiwenyan.cmccaifu.com.android2017.banner.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by zhiwenyan on 6/1/17.
 */

public class BannerViewPager extends ViewPager {
    private BannerAdapter mBannerAdapter;
    private static final int SCROLL_MSG = 0x011;
    private int mCutDownTime = 3000;
    private BannerScroller mBannerScroller;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCROLL_MSG:
                    setCurrentItem(getCurrentItem() + 1);
                    startLoop();
                    break;
            }
        }
    };

    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //改变ViewPager切换的速率
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            mBannerScroller = new BannerScroller(context);
            //设置强制改变
            field.setAccessible(true);
            //设置参数 第一个参数object当前属性的那个类 第二参数需要设置的值
            field.set(this, mBannerScroller);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置切换页面的持续时间
     *
     * @param scrollerDuration
     */
    public void setScrollerDuration(int scrollerDuration) {
        mBannerScroller.setScrollerDuration(scrollerDuration);
    }

    public void setAdapter(BannerAdapter adapter) {
        this.mBannerAdapter = adapter;
        setAdapter(new BannerPagerAdapter());
    }

    /*
       开启轮播
       */
    public void startLoop() {
        mHandler.removeMessages(SCROLL_MSG);
        mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mCutDownTime);
    }

    /**
     * 销毁Handler
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(SCROLL_MSG);
        mHandler = null;

    }

    private class BannerPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //Adapter设计模式为了完全让用户自定义
            // position 0-2的31次方
            View bannerItemView = mBannerAdapter.getView(position % mBannerAdapter.getCount());
            container.addView(bannerItemView);
            return bannerItemView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeMessages(SCROLL_MSG);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mCutDownTime);
                break;
        }
        return super.onTouchEvent(ev);
    }
}
