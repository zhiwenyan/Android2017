package zhiwenyan.cmccaifu.com.android2017.view.BezierCurve;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by zhiwenyan on 2017/8/18.
 */

public class MessageBubbleView extends View {
    //固定圆
    private PointF mFixationPoint;
    //拖拽圆
    private PointF mDragPoint;
    private Paint mPaint;
    private int mFixationRadiusMax = dip2px(8);
    private int mFixationRadius;
    private int mFixationRadiusMin = dip2px(2);
    private int mDragRadius = dip2px(12);
    private Bitmap mDragBitmap;

    public MessageBubbleView(Context context) {
        this(context, null);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float downX = event.getX();
                float downY = event.getY();
                initPoint(downX, downY);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                updateDragPoint(moveX, moveY);
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFixationPoint == null || mDragPoint == null) {
            return;
        }
        //拖拽圆
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint);
        //固定圆  随着距离的增大而减小
        //计算两个点的距离
        double distance = getDistance(mDragPoint, mFixationPoint);
        mFixationRadius = (int) (mFixationRadiusMax - distance / 10);

        Path bezierPath = getPath();
        if (bezierPath != null) {
            if (mFixationRadius > mFixationRadiusMin) {
                canvas.drawPath(bezierPath, mPaint);
                canvas.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint);
            }
            if (mDragBitmap != null) {
                // 搞一个渐变动画 中心位置才是手指拖动的位置
                canvas.drawBitmap(mDragBitmap, mDragPoint.x - mDragBitmap.getWidth() / 2, mDragPoint.y - mDragBitmap.getHeight() / 2, null);
            }
        }
    }


    /**
     * 更新拖拽点的位置
     *
     * @param moveX
     * @param moveY
     */
    public void updateDragPoint(float moveX, float moveY) {
        mDragPoint.x = moveX;
        mDragPoint.y = moveY;
        invalidate();

    }

    public void initPoint(float downX, float downY) {
        mFixationPoint = new PointF(downX, downY);
        mDragPoint = new PointF(downX, downY);
        invalidate();
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }

    /**
     * 获取两个圆的距离
     *
     * @param point1
     * @param point2
     * @return
     */
    private double getDistance(PointF point1, PointF point2) {
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point2.y - point1.y) * (point2.y - point1.y));
    }

    private Path getPath() {
        if (mFixationRadius < mFixationRadiusMin) {
            return null;
        }
        Path path = new Path();
//        //求斜率
        double dy = mDragPoint.y - mFixationPoint.y;
        double dx = mDragPoint.x - mFixationPoint.x;
        double tanA = dy / dx;
        //求角a
        double arcTanA = Math.atan(tanA);  //反三角 求角度

        float p0x = (float) (mFixationPoint.x + mFixationRadius * Math.sin(arcTanA));
        float p0y = (float) (mFixationPoint.y - mFixationRadius * Math.cos(arcTanA));

        float p1x = (float) (mDragPoint.x + mDragRadius * Math.sin(arcTanA));
        float p1y = (float) (mDragPoint.y - mDragRadius * Math.cos(arcTanA));

        float p2x = (float) (mDragPoint.x - mDragRadius * Math.sin(arcTanA));
        float p2y = (float) (mDragPoint.y + mDragRadius * Math.cos(arcTanA));

        float p3x = (float) (mFixationPoint.x - mFixationRadius * Math.sin(arcTanA));
        float p3y = (float) (mFixationPoint.y + mFixationRadius * Math.cos(arcTanA));
        //画第一条
        path.moveTo(p0x, p0y);
        PointF controlPointF = getControlPoint();
        path.quadTo(controlPointF.x, controlPointF.y, p1x, p1y);
        //画第二条
        path.lineTo(p2x, p2y);// 连接到
        path.quadTo(controlPointF.x, controlPointF.y, p3x, p3y);
        path.close();
        return path;
    }

    public PointF getControlPoint() {
        return new PointF((mDragPoint.x + mFixationPoint.x) / 2, (mDragPoint.y + mFixationPoint.y) / 2);
    }

    public void setDragBitmap(Bitmap dragBitmap) {
        this.mDragBitmap = dragBitmap;
    }

    /**
     * 绑定可以拖拽的控件
     *
     * @param view
     * @param bubbleListener
     */
    public static void attach(View view, MessageBubbleListener bubbleListener) {
        view.setOnTouchListener(new MessageBubbleTouchListener(view, view.getContext()));
    }

    /**
     * 处理手机松开
     */
    public void handleActionUp() {
        if (mFixationRadius > mFixationRadiusMin) {
            ValueAnimator animator = ObjectAnimator.ofFloat(1);
            animator.setDuration(350);
            PointF start = new PointF(mDragPoint.x, mDragPoint.y);
            PointF end = new PointF(mFixationPoint.x, mFixationPoint.y);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float percent = (float) animation.getAnimatedValue();
                    PointF pointF = BubbleUtils.getPointByPercent(start, end, percent);
                    updateDragPoint(pointF.x, pointF.y);
                }
            });
            animator.setInterpolator(new OvershootInterpolator());
            animator.start();
            //还要通知TouchListener 移除当前的View
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation, boolean isReverse) {
                    if (mMessageBubbleListener != null) {
                        mMessageBubbleListener.restore();
                    }
                }
            });
        } else {
            //爆炸
            mMessageBubbleListener.dismiss();
        }
    }

    private MessageBubbleListener mMessageBubbleListener;

    public void setMessageBubbleListener(MessageBubbleListener messageBubbleListener) {
        mMessageBubbleListener = messageBubbleListener;
    }

    public interface MessageBubbleListener {
        // 还原
        void restore();

        // 消失爆炸
        void dismiss();
    }
}
