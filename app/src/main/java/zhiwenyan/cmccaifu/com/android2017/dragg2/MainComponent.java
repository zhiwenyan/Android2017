package zhiwenyan.cmccaifu.com.android2017.dragg2;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description:容器-->将module与activity关联
 * Data：10/15/2018-4:40 PM
 *
 * @author yanzhiwen
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(Activity activity);
}
