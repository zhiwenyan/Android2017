package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhiwenyan on 29/10/2017.
 * <p>
 * 这个是导航栏的基础类
 */

public class AbsNavigationBar implements INavigation {
    private Builder mBuilder;
    private View mNavigationBar;

    protected AbsNavigationBar(Builder builder) {
        this.mBuilder = builder;
        createNavigationBar();
    }

    @Override
    public void createNavigationBar() {
        mNavigationBar = LayoutInflater.from(mBuilder.mContext)
                .inflate(mBuilder.mLayoutId, mBuilder.mParent, false);

        //添加
        attachParent(mNavigationBar, mBuilder.mParent);

        //绑定参数
        attachNavigationParams();
    }


    /**
     * 将NavigationBar 添加到父布局中
     *
     * @param navigationBar
     * @param parent
     */
    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar, 0);
    }

    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
        //设置文本
        Map<Integer, CharSequence> textMaps = mBuilder.mTextMap;
        for (Map.Entry<Integer, CharSequence> entry : textMaps.entrySet()) {
            TextView textView = findById(entry.getKey());
            textView.setText(entry.getValue());
        }
        //设置点击事件
        Map<Integer, View.OnClickListener> clickMaps = mBuilder.mOnClickListenerMap;
        for (Map.Entry<Integer, View.OnClickListener> entry : clickMaps.entrySet()) {
            View view = findById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    public <T extends View> T findById(int id) {
        return (T) mNavigationBar.findViewById(id);

    }

    /**
     * 返回Builder
     *
     * @return
     */
    public Builder getBuilder() {
        return mBuilder;
    }

    /**
     * Builder 构建类
     * 构建NavigationBar 还有存储参数
     */
    public abstract static class Builder<T extends Builder> {
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;
        public Map<Integer, CharSequence> mTextMap;
        public Map<Integer, View.OnClickListener> mOnClickListenerMap;

        public Builder(Context context, int layoutId, ViewGroup parent) {
            this.mContext = context;
            this.mLayoutId = layoutId;
            this.mParent = parent;
            this.mTextMap = new HashMap<>();
            this.mOnClickListenerMap = new HashMap<>();

        }

        /**
         * 用来创建NavigationBar
         *
         * @return
         */
        public abstract AbsNavigationBar create();

        /**
         * 返回的是AbsNavigationBar的builder
         *
         * @param viewId
         * @param text
         * @return
         */
        public T setText(int viewId, String text) {
            mTextMap.put(viewId, text);
            return (T) this;

        }

        /**
         * 设置点击事件
         *
         * @param viewId
         * @param listener
         * @return
         */
        public T setOnClickListener(int viewId, View.OnClickListener listener) {
            mOnClickListenerMap.put(viewId, listener);
            return (T) this;
        }
    }


}

