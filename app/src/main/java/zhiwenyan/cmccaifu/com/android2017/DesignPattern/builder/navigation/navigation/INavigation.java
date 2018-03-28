package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation.navigation;

import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * Data：3/21/2018-10:21 AM
 *
 * @author: yanzhiwen
 */
public interface INavigation {
    void createNavigationBar();

    void attachParent(View NavigationBar, ViewGroup parent);

    void attachNavigationParams();


}
