package zhiwenyan.cmccaifu.com.android2017;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


/**
 * Description:
 * 5. 通过模拟框架模拟依赖，隔离依赖：
 * 前面验证邮件格式的例子，本地JVM虚拟机就能提供足够的运行环境，但如果要测试的单元依赖了Android框架，
 * 比如用到了Android中的Context类的一些方法，
 * 本地JVM将无法提供这样的环境，这时候模拟框架[Mockito][1]就派上用场了。
 * <p>
 * Data：8/7/2018-10:46 AM
 *
 * @author yanzhiwen
 */

@RunWith(MockitoJUnitRunner.class)
public class MockUnitTest {
    private static final String FAKE_STRING = "AndroidUnitTest";
    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        //模拟方法调用的返回值，隔离对Android系统的依赖
        when(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING);
        assertThat(mMockContext.getString(R.string.app_name), is(FAKE_STRING));
        System.out.println(mMockContext.getString(R.string.app_name));

        when(mMockContext.getPackageName()).thenReturn("com.jdqm.androidunittest");
        System.out.println(mMockContext.getPackageName());
    }
}

