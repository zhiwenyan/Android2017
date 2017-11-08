package zhiwenyan.cmccaifu.com.android2017.view.textView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by yanzhiwen on 2017/11/8.
 */

public class MyLiseView extends ListView {

    public MyLiseView(Context context) {
        super(context);
    }

    public MyLiseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLiseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //解决显示不全的问题
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
