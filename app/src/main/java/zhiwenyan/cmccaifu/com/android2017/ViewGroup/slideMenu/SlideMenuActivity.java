package zhiwenyan.cmccaifu.com.android2017.ViewGroup.slideMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class SlideMenuActivity extends AppCompatActivity {
    private SlideMenu mSlideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);
        mSlideMenu = (SlideMenu) findViewById(R.id.slideMenu);
    }
}
