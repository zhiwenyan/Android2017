package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
    }

    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
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
    public abstract static class Builder {
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;

        public Builder(Context context, int layoutId, ViewGroup parent) {
            this.mContext = context;
            this.mLayoutId = layoutId;
            this.mParent = parent;
        }

        /**
         * 用来创建NavigationBar
         *
         * @return
         */
        public abstract AbsNavigationBar create();
    }


}

