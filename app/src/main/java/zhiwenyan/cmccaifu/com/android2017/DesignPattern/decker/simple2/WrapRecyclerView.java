package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiwenyan on 05/11/2017.
 */

public class WrapRecyclerView extends RecyclerView {
    private WrapRecyclerAdapter mAdapter;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mAdapter == null) {
            mAdapter = new WrapRecyclerAdapter(adapter);
        }
        super.setAdapter(mAdapter);
    }


    /**
     * 添加头部View
     *
     * @param headerView
     */
    public void addHeaderView(View headerView) {
        //必须要设置Adapter添加头部和底部
        if (mAdapter != null) {
            mAdapter.addHeaderView(headerView);
        }
    }

    /**
     * 添加底部View
     *
     * @param footerView
     */
    public void addFooterView(View footerView) {
        if (mAdapter != null) {
            mAdapter.addFooterView(footerView);
        }
    }

    /**
     *
     */
    public void removeHeaderView() {
        if (mAdapter != null) {
            //
        }
    }
}
