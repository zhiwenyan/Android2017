package zhiwenyan.cmccaifu.com.android2017.view.pieview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public class LineView extends View {
    private Paint mPaintXY = new Paint();
    private Paint mCirclePaint = new Paint();
    private Paint mLinePaint = new Paint();

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintXY.setAntiAlias(true);
        mPaintXY.setColor(Color.RED);
        mPaintXY.setStrokeWidth(2);

        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);

        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(10, 10, 10, getHeight() / 2, mPaintXY);
        canvas.drawLine(10, getHeight() / 2, getWidth() - 10, getHeight() / 2, mPaintXY);


    }
}
