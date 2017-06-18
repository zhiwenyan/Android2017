package zhiwenyan.cmccaifu.com.android2017.banner.cardViewPager;

import android.support.v7.widget.CardView;

/**
 * Created by zhiwenyan on 6/11/17.
 */

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}