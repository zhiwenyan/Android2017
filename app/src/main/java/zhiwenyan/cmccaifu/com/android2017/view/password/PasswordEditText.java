package zhiwenyan.cmccaifu.com.android2017.view.password;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 6/2/17.
 * 自定义密码输入框
 */

public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {
    // 画笔
    private Paint mPaint;
    // 一个密码所占的宽度
    private int mPasswordItemWidth;
    // 密码的个数默认为6位数
    private int mPasswordNumber = 6;
    // 背景边框颜色
    private int mBgColor = Color.parseColor("#d1d2d6");
    // 背景边框大小
    private int mBgSize = 1;
    // 背景边框圆角大小
    private int mBgCorner = 0;
    // 分割线的颜色
    private int mDivisionLineColor = mBgColor;
    // 分割线的大小
    private int mDivisionLineSize = 1;
    // 密码圆点的颜色
    private int mPasswordColor = mDivisionLineColor;
    // 密码圆点的半径大小
    private int mPasswordRadius = 4;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAttributeSet(context, attrs);
        // 设置输入模式是密码
        setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        // 不显示光标
        setCursorVisible(false);
        setBackground(null);
    }

    /**
     * 初始化属性
     */
    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditText);
        // 获取大小
        mDivisionLineSize = (int) array.getDimension(R.styleable.PasswordEditText_divisionLineSize, dip2px(mDivisionLineSize));
        mPasswordRadius = (int) array.getDimension(R.styleable.PasswordEditText_passwordRadius, dip2px(mPasswordRadius));
        mBgSize = (int) array.getDimension(R.styleable.PasswordEditText_bgSize, dip2px(mBgSize));
        mBgCorner = (int) array.getDimension(R.styleable.PasswordEditText_bgCorner, 0);
        // 获取颜色
        mBgColor = array.getColor(R.styleable.PasswordEditText_bgColor, mBgColor);
        mDivisionLineColor = array.getColor(R.styleable.PasswordEditText_divisionLineColor, mDivisionLineColor);
        mPasswordColor = array.getColor(R.styleable.PasswordEditText_passwordColor, mDivisionLineColor);
        array.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);

    }


    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        //一个密码的宽度
        mPasswordItemWidth = (getWidth() - 2 * mBgSize - (mPasswordNumber - 1) * mDivisionLineSize) / mPasswordNumber;
        drawBg(canvas);
        drawDivisionLine(canvas);
        drawPassword(canvas);
        if (mPasswordFullListener != null) {
            String password = getText().toString().trim();
            if (password.length() >= mPasswordNumber) {
                mPasswordFullListener.passwordFull(password);
            }
        }
    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    private void drawBg(Canvas canvas) {
        RectF rect = new RectF(mBgSize, mBgSize, getWidth() - mBgSize, getHeight() - mBgSize);
        mPaint.setStrokeWidth(mBgSize);
        //画空心
        mPaint.setStyle(Paint.Style.STROKE);
        if (mBgCorner == 0) {
            canvas.drawRect(rect, mPaint);
        } else {
            canvas.drawRoundRect(rect, mBgCorner, mBgCorner, mPaint);
        }
    }

    /**
     * 绘制分割线
     *
     * @param canvas
     */
    private void drawDivisionLine(Canvas canvas) {
        mPaint.setStrokeWidth(mDivisionLineSize);
        for (int i = 0; i < mPasswordNumber - 1; i++) {
            int startX = mBgSize + (i + 1) * mPasswordItemWidth + i * mDivisionLineSize;
            int startY = 0;
            int endX = startX - mBgSize;
            int endY = getHeight() - mBgSize;
            canvas.drawLine(startX, startY, endX, endY, mPaint);
        }
    }

    /**
     * 绘制密码
     *
     * @param canvas
     */
    private void drawPassword(Canvas canvas) {
        //密码需要实心
        mPaint.setStyle(Paint.Style.FILL);
        // mPaint.setColor(mPasswordColor);
        String text = getText().toString();
        int passwordLength = text.length();
        for (int i = 0; i < passwordLength; i++) {
            int cx = mBgSize + i * mPasswordItemWidth + i * mDivisionLineSize + mPasswordItemWidth / 2;
            int cy = getHeight() / 2;
            canvas.drawCircle(cx, cy, mPasswordRadius, mPaint);
        }
    }

    public void addPassword(String number) {
        //把之前的密码取出来
        String password = getText().toString().trim();
        if (password.length() >= mPasswordNumber) {
            return;
        }
        //密码叠加
        password += number;
        setText(password);
    }

    public void deletePassword() {
        String password = getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            return;
        }
        password = password.substring(0, password.length() - 1);
        setText(password);
    }

    PasswordFullListener mPasswordFullListener;

    public void setPasswordFullListener(PasswordFullListener passwordFullListener) {
        this.mPasswordFullListener = passwordFullListener;
    }

    public interface PasswordFullListener {
        void passwordFull(String password);
    }
}
