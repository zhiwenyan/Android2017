package zhiwenyan.cmccaifu.com.android2017.view.redPacket;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：15/04/2018
 * Author: Steven
 */
public class RedPacketView extends View {
    //红包的背景
    private Bitmap mRedPacketBitmap;
    //进度条的背景
    private Bitmap mProgressBitmap;


    private float mTotalProgress=2.0f;
    private float mCurrentProgress=2.0f;

    private Paint mProgressPaint;

    public RedPacketView(Context context) {
        this(context,null);
    }

    public RedPacketView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RedPacketView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRedPacketBitmap= BitmapFactory.decodeResource(getResources(),
                R.mipmap.icon_game_red_package_normal);
        mProgressBitmap=BitmapFactory.decodeResource(getResources(),
                R.mipmap.icon_game_red_package_pb_bg);

        mProgressPaint=new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //指定宽高
        int size=(int)Math.max(mRedPacketBitmap.getWidth(),mRedPacketBitmap.getHeight()*1.1f);
        setMeasuredDimension(size,size);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height=getHeight();
        int width=getWidth();
        //画红包
        canvas.drawBitmap(mRedPacketBitmap,0,0,null);
        //画进度条
        int top=(int) (mRedPacketBitmap.getHeight()-mProgressBitmap.getHeight()*0.8f);
        int left=(int)((width-mProgressBitmap.getWidth())/2-mProgressBitmap.getHeight()*0.02f);
        canvas.drawBitmap(mProgressBitmap,left,top,null);

        //画进度
        int progressWidth=(int)(mProgressBitmap.getWidth()*0.75f);
        int currentProgressWidth=(int) (progressWidth*(mCurrentProgress/mTotalProgress));
        int progressHeight=(int)(mProgressBitmap.getHeight()*0.3f);
        left=(int)(mProgressBitmap.getWidth()*0.2f);
        top= (int) (height*0.75f);
        RectF rectF=new RectF(left,top,
                left+currentProgressWidth,top+progressHeight);
        int round=mProgressBitmap.getHeight()/2;
        mProgressPaint.setColor(Color.BLUE);
        canvas.drawRoundRect(rectF,round,round,mProgressPaint);


    }
}
