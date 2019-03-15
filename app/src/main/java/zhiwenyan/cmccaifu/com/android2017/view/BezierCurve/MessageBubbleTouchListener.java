package zhiwenyan.cmccaifu.com.android2017.view.BezierCurve;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by zhiwenyan on 2017/8/24.
 */

/**
 * 监听当前View的Touch事件
 */
public class MessageBubbleTouchListener implements View.OnTouchListener, MessageBubbleView.MessageBubbleListener {
    private View mView;
    private WindowManager mWindowManager;
    private MessageBubbleView mMessageBubbleView;
    private WindowManager.LayoutParams mLayoutParams;
    private Context mContext;


    public MessageBubbleTouchListener(View view, Context context) {
        this.mView = view;
        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.mMessageBubbleView = new MessageBubbleView(context);
        this.mLayoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams.format = PixelFormat.TRANSPARENT;
        this.mContext = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mView.setVisibility(View.INVISIBLE);
                //在WindowManager上搞一个View,上一次写好的贝塞尔View
                mWindowManager.addView(mMessageBubbleView, mLayoutParams);
                //getX(); 相对于父布局的位置  getRawX()相对于屏幕的位置
                mMessageBubbleView.initPoint(event.getRawX(), event.getRawY() - BubbleUtils.getStatusBarHeight(mContext));
                //给消息拖拽设置一个Bitmap
                mMessageBubbleView.setDragBitmap(getBitmapByView(mView));
                mMessageBubbleView.setMessageBubbleListener(this);
                break;
            case MotionEvent.ACTION_MOVE:
                mMessageBubbleView.updateDragPoint(event.getRawX(), event.getRawY() - BubbleUtils.getStatusBarHeight(mContext));

                break;
            case MotionEvent.ACTION_UP:
                mMessageBubbleView.handleActionUp();
                break;
        }
        return true;
    }


    public Bitmap getBitmapByView(View view) {
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public void restore() {
        mWindowManager.removeView(mMessageBubbleView);
        mView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        //帧动画
    }
}
