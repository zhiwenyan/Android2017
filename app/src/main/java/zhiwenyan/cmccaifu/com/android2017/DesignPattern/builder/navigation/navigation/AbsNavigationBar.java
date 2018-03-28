package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Data：3/21/2018-10:22 AM
 *
 * @author: yanzhiwen
 */
public class AbsNavigationBar implements INavigation {
    private Builder mBuilder;
    //NavigationBar具体的View
    private View mNavigationBar;

    public AbsNavigationBar(Builder builder) {
        this.mBuilder = builder;
        createNavigationBar();

    }

    @Override
    public void createNavigationBar() {
        this.mNavigationBar = LayoutInflater.from(mBuilder.mContext)
                .inflate(mBuilder.mLayoutId, mBuilder.mParent, false);
        //添加
        attachParent(mNavigationBar, mBuilder.mParent);

        //绑定参数
        attachNavigationParams();
    }

    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar, 0);
    }

    @Override
    public void attachNavigationParams() {
        //设置文本
        Map<Integer, CharSequence> mTextViewMap = mBuilder.mTextViewMap;
        for (Map.Entry<Integer, CharSequence> entry : mTextViewMap.entrySet()) {
            TextView tv = findViewById(entry.getKey());
            tv.setText(entry.getValue());

        }
        //设置点击事件
        Map<Integer, View.OnClickListener> mListenerMap = mBuilder.mListenerMap;
        for (Map.Entry<Integer, View.OnClickListener> entry : mListenerMap.entrySet()) {
            TextView tv = findViewById(entry.getKey());
            tv.setOnClickListener(entry.getValue());

        }
    }

    public <T extends View> T findViewById(int viewId) {
        return (T) mNavigationBar.findViewById(viewId);
    }

    public abstract static class Builder<T extends Builder> {
        private Context mContext;
        private int mLayoutId;
        private ViewGroup mParent;
        //存放View
        private Map<Integer, CharSequence> mTextViewMap;
        //存放View的点击事件
        private Map<Integer, View.OnClickListener> mListenerMap;

        /**
         * @param context
         * @param layoutId NavigationBar具体的View
         * @param parent   NavigationBar添加到的父布局
         */
        public Builder(Context context, int layoutId, ViewGroup parent) {
            this.mContext = context;
            this.mLayoutId = layoutId;
            this.mParent = parent;
            this.mTextViewMap = new HashMap<>();
            this.mListenerMap = new HashMap<>();

        }

        /**
         * 创建AbsNavigationBar，子类去实现
         *
         * @return
         */
        public abstract AbsNavigationBar create();


        public T setText(int viewId, String text) {
            mTextViewMap.put(viewId, text);
            return (T) this;
        }

        public T setOnClickListener(int viewId, View.OnClickListener onClickListener) {
            mListenerMap.put(viewId, onClickListener);
            return (T) this;
        }
    }
}
