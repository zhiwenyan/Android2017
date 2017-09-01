package zhiwenyan.cmccaifu.com.android2017.indicatorViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.view.textView.ColorTrackTextView;

public class CommonViewPagerActivity extends AppCompatActivity {
    private String items[] = {"推荐", "直播", "视频", "游戏", "精华", "同城", "娱乐", "搞笑"};
    private List<ColorTrackTextView> mIndicators = new ArrayList<>();
    private TrackIndicatorView mIndicatorView;
    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_view_pager);
        mIndicatorView = (TrackIndicatorView) findViewById(R.id.trackIndicatorView);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mIndicatorView.setAdapter(new IndicatorAdapter() {
            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                ColorTrackTextView colorTrackTextView = new ColorTrackTextView(CommonViewPagerActivity.this);
                colorTrackTextView.setTextSize(30);
                colorTrackTextView.setChangeColor(Color.RED);
                colorTrackTextView.setText(items[position]);
                mIndicators.add(colorTrackTextView);
                ItemFragment itemFragment = new ItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", items[position]);
                itemFragment.setArguments(bundle);
                mFragments.add(itemFragment);
                return colorTrackTextView;
            }

            @Override
            public void highIndicator(View view) {
                TextView tv = (TextView) view;
                tv.setTextColor(Color.RED);
            }

            @Override
            public void restoreIndicator(View view) {
                TextView tv = (TextView) view;
                tv.setTextColor(Color.BLACK);
            }

            @Override
           public View getBottomTrackView() {
                View view=new View(CommonViewPagerActivity.this);
                view.setBackgroundColor(Color.RED);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,8));
                return view;
            }
        }, mViewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    // 获取左边
                    ColorTrackTextView left = mIndicators.get(position);
                    // 设置朝向
                    left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                    // 设置进度  positionOffset 是从 0 一直变化到 1
                    left.setCurrentProgress(1 - positionOffset);

                    // 获取右边
                    ColorTrackTextView right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextView.Direction.LEDT_TO_RIGHT);
                    right.setCurrentProgress(positionOffset);
                }
            }
        });
        // 默认一进入就选中第一个
        ColorTrackTextView left = mIndicators.get(0);
        left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        left.setCurrentProgress(1);

    }
}
