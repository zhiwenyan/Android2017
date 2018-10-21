package zhiwenyan.cmccaifu.com.android2017.dragg2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 *
 * @author yanzhiwen
 * @Module 和 @Provides，Module标注的对象，你可以把它想象成一个工厂，可以向外提供一些类的对象。那么到底提供什么对象呢？
 * @Provides标注的方法就是提供对象的，这种方法一般会返回一个对象实例，例如上面返回一个 Person对象
 * Data：10/15/2018-4:32 PM
 */
@Module
public class MainModule {
    @Singleton
    @Provides
    Person providesPerson() {
        System.out.println("a person created from MainModule");
        return new Person();
    }
}
