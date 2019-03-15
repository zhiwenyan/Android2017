package zhiwenyan.cmccaifu.com.android2017.ViewGroup.tagLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiwenyan on 6/13/17.
 * <p>
 * 流式布局
 */

public class TagLayout extends ViewGroup {

    private List<List<View>> mChildViews = new ArrayList<>();

    public TagLayout(Context context) {
        this(context, null);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mChildViews.clear();
        //for循环测量子View

        int childCount = getChildCount();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        //一行的宽度
        int lineWidth = getPaddingLeft();
        ArrayList<View> childViews = new ArrayList<>();
        // mChildViews.add(childViews);
        //子View高度不一致的情况
        int maxFirstHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量子View的高度，因为会调用子View的onMeasure
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //根据子View计算和指定自己的布局
            //什么时候需要换行   一行不够的时候需要换行
            ViewGroup.MarginLayoutParams param = (MarginLayoutParams) childView.getLayoutParams();
            if (lineWidth + (childView.getMeasuredWidth() + param.leftMargin + param.rightMargin) > width) {
                mChildViews.add(childViews);
                lineWidth += childView.getMeasuredWidth() + param.leftMargin + param.rightMargin;
                //换行  累加高度
                height += childView.getMeasuredHeight() + param.bottomMargin + param.topMargin;
                childViews = new ArrayList<>();
            } else {
                lineWidth += childView.getMeasuredWidth() + param.leftMargin + param.rightMargin;
                maxFirstHeight = Math.max(childView.getMeasuredHeight() + param.topMargin + param.bottomMargin, maxFirstHeight);
            }
            childViews.add(childView);
            if (i == childCount - 1) {
                mChildViews.add(childViews);
            }
        }
        height += maxFirstHeight;
        //指定自己的宽高
        setMeasuredDimension(width, height);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    //摆放子布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top = getPaddingTop(), right, bottom;
        for (List<View> childViews : mChildViews) {
            left = getPaddingLeft();
            for (View childView : childViews) {
                ViewGroup.MarginLayoutParams param = (MarginLayoutParams) childView.getLayoutParams();
                left += param.leftMargin;
                right = left + childView.getMeasuredWidth();
                int childTop = top + param.topMargin;
                bottom = childTop + childView.getMeasuredHeight();
                //摆放
                childView.layout(left, childTop, right, bottom);
                //left叠加
                left += childView.getMeasuredWidth() + param.rightMargin;
            }
            //不断的叠加top
            ViewGroup.MarginLayoutParams param = (MarginLayoutParams) childViews.get(0).getLayoutParams();
            top += childViews.get(0).getMeasuredHeight() + param.topMargin + param.bottomMargin;
        }
    }
}
