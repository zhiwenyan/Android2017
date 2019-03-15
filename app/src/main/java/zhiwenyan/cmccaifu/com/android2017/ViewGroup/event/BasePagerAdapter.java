package zhiwenyan.cmccaifu.com.android2017.ViewGroup.event;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:
 * Data：2/18/2019-1:48 PM
 *
 * @author yanzhiwen
 */
class BasePagerAdapter extends PagerAdapter {
    private List<View> mViews;

    public BasePagerAdapter(List<View> views) {
        this.mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
     //   super.destroyItem(container, position, object);

    }
}
