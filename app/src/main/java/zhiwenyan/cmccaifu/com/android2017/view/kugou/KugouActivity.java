package zhiwenyan.cmccaifu.com.android2017.view.kugou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.view.kugou.parallax.animation.ParallaxViewPager;

public class KugouActivity extends AppCompatActivity {
    private ParallaxViewPager mParallaxViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kugou);
        mParallaxViewPager = (ParallaxViewPager) findViewById(R.id.viewPager);
        mParallaxViewPager.setLayout(getSupportFragmentManager(),
                new int[]{R.layout.fragment_page_first, R.layout.fragment_page_second, R.layout.fragment_page_third});
    }
}
