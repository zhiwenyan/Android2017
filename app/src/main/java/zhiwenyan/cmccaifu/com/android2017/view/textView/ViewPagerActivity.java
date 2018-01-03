package zhiwenyan.cmccaifu.com.android2017.view.textView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ViewPagerActivity extends AppCompatActivity {
    private String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华", "关注", "同城"};
    private LinearLayout mIndicatorContainer;
    private List<ColorTrackTextView> mIndicators;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        mIndicators = new ArrayList<>();
        mIndicatorContainer = (LinearLayout) findViewById(R.id.indicator_view);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        initIndicator();
        initViewPager();
    }


    private void initViewPager() {
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        /**
         * 添加一个切换的监听那个setOnPageChangeListener过时了
         * 这个看源码去吧
         */
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            /**
             *
             * @param position  当前的位置
             * @param positionOffset  //滚动的百分比
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                if (positionOffset > 0) {
                    // 获取左边
                    ColorTrackTextView left = mIndicators.get(position);
                    // 设置朝向
                    left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                    // 设置进度  positionOffset 是从 0 一直变化到 1
                    left.setCurrentProgress(1 - positionOffset);

                    // 获取右边
                    ColorTrackTextView right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                    right.setCurrentProgress(positionOffset);
                }
            }
        });
        // 默认一进入就选中第一个
        ColorTrackTextView left = mIndicators.get(0);
        left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        left.setCurrentProgress(1);
    }

    /**
     * 初始化可变色的指示器
     */
    private void initIndicator() {
        for (int i = 0; i < items.length; i++) {
            // 动态添加颜色跟踪的TextView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
//            params.leftMargin = 8;
//            params.rightMargin = 8;
            params.weight = 1;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            // 设置两种颜色
//            colorTrackTextView.setOriginColor(Color.BLACK);
//          colorTrackTextView.setChangeColor(Color.RED);
            colorTrackTextView.setText(items[i]);
            colorTrackTextView.setLayoutParams(params);
            // 把新的加入LinearLayout容器
            mIndicatorContainer.addView(colorTrackTextView);
            // 加入集合
            mIndicators.add(colorTrackTextView);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ItemFragment.newInstance(items[position]);
        }

        @Override
        public int getCount() {
            return items.length;
        }
    }
}
