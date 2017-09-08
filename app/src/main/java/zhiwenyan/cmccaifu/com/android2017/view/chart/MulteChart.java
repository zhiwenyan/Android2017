package zhiwenyan.cmccaifu.com.android2017.view.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by yanzhiwen on 2017/9/7.
 */

public class MulteChart extends GraphView {
    private int axisDividerSizeX = 8;
    private int axisDividerSizeY = 8;

    public MulteChart(Context context) {
        this(context, null);
    }

    public MulteChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MulteChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void drawXAxis(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(originalX, originalY, originalX + mWidth, originalY, paint);
    }

    @Override
    public void drawYAxis(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(originalX, originalY, originalX, originalY - mHeight, paint);
    }

    //刻度
    @Override
    public void drawXAxisScale(Canvas canvas, Paint paint) {
        float cellWidth = mWidth / axisDividerSizeX;
        for (int i = 0; i < axisDividerSizeX; i++) {
            canvas.drawLine(cellWidth * (i + 1) + originalX, originalY,
                    cellWidth * (i + 1) + originalX, originalY - 10, paint);
        }
    }

    @Override
    public void drawXAxisScaleValue(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setTextSize(16);
        float cellWidth = mWidth / axisDividerSizeX;
        for (int i = 0; i < axisDividerSizeX; i++) {
            canvas.drawText(i + "", cellWidth * i + originalX - 35, originalY + 30, paint);
        }
    }

    @Override
    public void drawYAxisScale(Canvas canvas, Paint paint) {
        float cellHeight = mHeight / axisDividerSizeY;
        for (int i = 0; i < axisDividerSizeY; i++) {
            canvas.drawLine(originalX, originalY - cellHeight * (i + 1), originalX + 10, originalY - cellHeight * (i + 1), paint);
        }
    }

    @Override
    public void drawYAxisScaleValue(Canvas canvas, Paint paint) {
        float cellHeight = mHeight / axisDividerSizeY;
        for (int i = 0; i < axisDividerSizeY; i++) {
            canvas.drawText(i * 100 + "", originalX - 35, originalY - cellHeight * i + 10, paint);

        }
    }

    @Override
    public void drawCloumn(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        float cellWidth = mWidth / axisDividerSizeX;
        float cellHeight = mHeight / axisDividerSizeY;

        for (int i = 0; i < axisDividerSizeX; i++) {
            RectF rectF = new RectF(cellWidth * (i + 1),
                    originalY - cellHeight * (i + 1), cellWidth * (i + 2), originalY - 20);
            canvas.drawRect(rectF, paint);
        }
    }
}
