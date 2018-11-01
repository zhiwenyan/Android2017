package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.Model.Movies;
import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;

/**
 * Created by yanzhiwen on 2017/10/11.
 */

public class SectionItemDecoration extends RecyclerView.ItemDecoration {
    //悬浮View的Drawable
    private Drawable mDrawable;
    private Drawable mDiverDrawable;
    private Context mContext;
    private List<Movies.DataBean.ComingBean> mLists;
    private Paint mTextPaint;
    private GroupListener mGroupListener;
    private boolean mFirstDraw = false;


    public SectionItemDecoration(Context context, int drawableId, int divierDrawableId,
                                 List<Movies.DataBean.ComingBean> lists, GroupListener groupListener) {
        mDrawable = ContextCompat.getDrawable(context, drawableId);
        mDiverDrawable = ContextCompat.getDrawable(context, divierDrawableId);
        mContext = context;
        mLists = lists;
        this.mGroupListener = groupListener;

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
        int groupHeight = mDrawable.getIntrinsicHeight();
        int divideHeight = mDiverDrawable.getIntrinsicHeight();
        int position = parent.getChildAdapterPosition(view);
        String groupId = mGroupListener.getGroupName(position);
        if (groupId == null) return;
        //只有是同一组的第一个才显示悬浮栏
        if (position == 0 || isFirstInGroup(position)) {
            //为悬浮view预留空间
            outRect.top = groupHeight;
        } else {
            //为分割线预留空间
            outRect.top = divideHeight;
        }
    }
//
//    /**
//     * onDraw：通过该方法，在Canvas上绘制内容，在绘制Item之调用。
//     * （如果没有通过getItemOffsets设置偏移的话，Item的内容会将其覆盖）
//     *
//     * @param c
//     * @param parent
//     * @param state
//     */
//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
//        Rect rect = new Rect();
//        rect.left = parent.getPaddingLeft();
//        rect.right = parent.getWidth() - parent.getPaddingRight();
//        for (int i = 0; i < parent.getChildCount(); i++) {
//            View view = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(view);
//            String text = mLists.get(position).getComingTitle();
//            if (isFirstInGroup(position)) {
//                drawView(rect, c, parent, i);
//                drawTitle(rect, c, parent, i, text);
//            } else {
//                drawItemDecoration(rect, c, parent, i);
//            }
//        }
//    }


    private void drawView(Rect rect, Canvas c, RecyclerView parent, int i) {
        //画悬浮View
        rect.top = parent.getChildAt(i).getTop() - mDrawable.getIntrinsicHeight();
        rect.bottom = rect.top + mDrawable.getIntrinsicHeight();
        mDrawable.setBounds(rect);
        mDrawable.draw(c);
    }


    private void drawTitle(Rect rect, Canvas c, RecyclerView parent, int i, String text) {
        //画文字
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dx = parent.getPaddingLeft() + DensityUtil.dp2px(8);
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = parent.getChildAt(i).getTop() + dy - mDrawable.getIntrinsicHeight() / 2;
        c.drawText(text, dx, baseLine, mTextPaint);
    }

    /**
     * 绘制分割线
     */
    private void drawItemDecoration(Rect rect, Canvas c, RecyclerView parent, int i) {
        rect.top = parent.getChildAt(i).getTop() - mDiverDrawable.getIntrinsicHeight();
        rect.bottom = rect.top + mDiverDrawable.getIntrinsicHeight();
        mDiverDrawable.setBounds(rect);
        mDiverDrawable.draw(c);

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            String text = mLists.get(position).getComingTitle();
            if (isFirstInGroup(position)) {
                drawView(rect, c, parent, i);
                drawTitle(rect, c, parent, i, text);
            } else {
                drawItemDecoration(rect, c, parent, i);
            }
        }
}

    //判断是不是组中的第一个位置
    //根据前一个组名，判断当前是否为新的组
    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            String prevGroupId = mGroupListener.getGroupName(pos - 1);
            String groupId = mGroupListener.getGroupName(pos);
            return !TextUtils.equals(prevGroupId, groupId);
        }
    }
}
