package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhiwenyan on 29/10/2017.
 * <p>
 * 导航栏的规范
 */

public interface INavigation {
    void createNavigationBar();

    void attachParent(View navigationBar, ViewGroup parent);

    void attachNavigationParams();
}
