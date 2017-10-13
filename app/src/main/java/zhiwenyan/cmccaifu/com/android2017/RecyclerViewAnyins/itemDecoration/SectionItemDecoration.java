package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.Model.Movies;
import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;

/**
 * Created by yanzhiwen on 2017/10/11.
 */

public class SectionItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDrawable;
    private Context mContext;
    private List<Movies.DataBean.ComingBean> mLists;
    private Paint mTextPaint;

    public SectionItemDecoration(Context context, int drawableId, List<Movies.DataBean.ComingBean> lists) {
        mDrawable = ContextCompat.getDrawable(context, drawableId);
        mContext = context;
        mLists = lists;
        initTextPaint();
    }

    private void initTextPaint() {
        mTextPaint = new Paint();
        mTextPaint.setTextSize(DensityUtil.sp2px(mContext, 14));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setColor(Color.WHITE);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mDrawable.getIntrinsicHeight();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawRect(c, parent, state);
        drawText(c, parent, state);
    }

    /**
     * 画矩形
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawRect(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < parent.getChildCount(); i++) {
            rect.top = parent.getChildAt(i).getTop() - mDrawable.getIntrinsicHeight();
            rect.bottom = rect.top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(rect);
            mDrawable.draw(c);
        }
    }

    /**
     * 画文字
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawText(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            String text = mLists.get(i).getComingTitle();
            Rect textBounds = new Rect();
            mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            int dx = parent.getPaddingLeft() + DensityUtil.dp2px(8);
            int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            int baseLine = parent.getChildAt(i).getTop() + dy - mDrawable.getIntrinsicHeight() / 2;
            c.drawText(text, dx, baseLine, mTextPaint);

        }
    }

}
