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
    private Drawable mDrawable; //悬浮View的Drawable
    private Drawable mDivierDrawable;
    private Context mContext;
    private List<Movies.DataBean.ComingBean> mLists;
    private Paint mTextPaint;
    private GroupListener mGroupListener;

    public SectionItemDecoration(Context context, int drawableId, int divierDrawableId, List<Movies.DataBean.ComingBean> lists, GroupListener groupListener) {
        mDrawable = ContextCompat.getDrawable(context, drawableId);
        mDivierDrawable = ContextCompat.getDrawable(context, divierDrawableId);
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
        int divideHeight = mDivierDrawable.getIntrinsicHeight();
        int position = parent.getChildAdapterPosition(view);
        String groupId = mGroupListener.getGroupName(position);
        if (groupId == null) return;
        //只有是同一组的第一个才显示悬浮栏
        if (position == 0 || isFirstInGroup(position)) {
            outRect.top = groupHeight; //为悬浮view预留空间
        } else {
            outRect.top = divideHeight; //为分割线预留空间
        }
    }

    /**
     * onDraw：通过该方法，在Canvas上绘制内容，在绘制Item之前调用。
     * （如果没有通过getItemOffsets设置偏移的话，Item的内容会将其覆盖）
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        String preGroupName;      //标记上一个item对应的Group
        String currentGroupName = null;       //当前item对应的Group
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupName = currentGroupName;
            currentGroupName = mGroupListener.getGroupName(position);
            if (currentGroupName != null && !TextUtils.equals(preGroupName, currentGroupName)) {
                //画悬浮View
                rect.top = parent.getChildAt(i).getTop() - mDrawable.getIntrinsicHeight();
                rect.bottom = rect.top + mDrawable.getIntrinsicHeight();
                mDrawable.setBounds(rect);
                mDrawable.draw(c);
                //画文字
                String text = mLists.get(i).getComingTitle();
                Rect textBounds = new Rect();
                mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
                Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
                int dx = parent.getPaddingLeft() + DensityUtil.dp2px(8);
                int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                int baseLine = parent.getChildAt(i).getTop() + dy - mDrawable.getIntrinsicHeight() / 2;
                c.drawText(text, dx, baseLine, mTextPaint);
            } else {
                //画分割线
                rect.top = parent.getChildAt(i).getTop() - mDivierDrawable.getIntrinsicHeight();
                rect.bottom = rect.top + mDivierDrawable.getIntrinsicHeight();
                mDivierDrawable.setBounds(rect);
                mDivierDrawable.draw(c);
            }

        }
    }

    /**
     * onDrawOver：通过该方法，在Canvas上绘制内容,在Item之后调用。(画的内容会覆盖在item的上层)
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int itemCount = state.getItemCount();
        final int childCount = parent.getChildCount();
        Rect rect = new Rect();
        rect.left = parent.getLeft() + parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();

        String preGroupName;      //标记上一个item对应的Group
        String currentGroupName = null;       //当前item对应的Group
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupName = currentGroupName;
            currentGroupName = mGroupListener.getGroupName(position);
            if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName))
                continue;
            int viewBottom = view.getBottom();
            float top = Math.max(mDrawable.getIntrinsicHeight(), view.getTop());//top 决定当前顶部第一个悬浮Group的位置
            if (position + 1 < itemCount) {
                //获取下个GroupName
                String nextGroupName = mGroupListener.getGroupName(position + 1);
                //下一组的第一个View接近头部
                if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
                    top = viewBottom;
                }
            }
            //根据top绘制group
            mDrawable.setBounds(rect);
            mDrawable.draw(c);
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            //文字竖直居中显示
            float baseLine = top - (mDivierDrawable.getIntrinsicHeight() - (fm.bottom - fm.top)) / 2 - fm.bottom;
            c.drawText(currentGroupName, rect.left, baseLine, mTextPaint);



//            //画文字
//            String text = mLists.get(i).getComingTitle();
//            Rect textBounds = new Rect();
//            mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
//            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
//            int dx = parent.getPaddingLeft() + DensityUtil.dp2px(8);
//            int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
//            int baseLine = parent.getChildAt(i).getTop() + dy - mDrawable.getIntrinsicHeight() / 2;
//            c.drawText(text, dx, baseLine, mTextPaint);
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
