package zhiwenyan.cmccaifu.com.android2017.ViewGroup.refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：7/2/2018-9:36 AM
 *
 * @author yanzhiwen
 */
public class SwipeRefreshMultiStatusLayout extends SwipeRefreshLayout {
    private View mNetWorkView;
    private View mEmptyView;


    public SwipeRefreshMultiStatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeRefreshMultiStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary));
        mNetWorkView = LayoutInflater.from(context).inflate(R.layout.network_view, this, false);
        mEmptyView = LayoutInflater.from(context).inflate(R.layout.empty_view, this, false);
        mEmptyView.setVisibility(GONE);
        mNetWorkView.setVisibility(GONE);
        addView(mNetWorkView, 0);
        addView(mEmptyView, 0);
    }

    public void showNetWorkView() {
        mNetWorkView.setVisibility(VISIBLE);
    }

    public void showEmptyView() {
        mEmptyView.setVisibility(VISIBLE);
    }

}
