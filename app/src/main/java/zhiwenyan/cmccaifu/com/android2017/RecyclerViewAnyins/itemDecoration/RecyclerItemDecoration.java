package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhiwenyan on 5/24/17.
 * 绘制分割线
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;

    public RecyclerItemDecoration() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    /**
     * 留出空间
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //当前View的位置
        int position = parent.getChildAdapterPosition(view);
        if (position != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = 10;
        }
    }

    /**
     * 绘制分割线
     *
     * @param c
     * @param parent
     * @param state
     */

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        RectF rectF = new RectF();
        rectF.left = parent.getPaddingLeft();
        rectF.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 1; i < childCount; i++) {
            rectF.bottom = parent.getChildAt(i).getTop();
            rectF.top = rectF.bottom - 10;
            c.drawRect(rectF, mPaint);
        }

    }
}
