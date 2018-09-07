package zhiwenyan.cmccaifu.com.android2017;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @ https://blog.csdn.net/wangpeng047/article/details/9628449
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     * 查看两个数组是否相等。
     */
    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    /**
     * 查看两个对象是否相等。类似于字符串比较使用的equals()方法
     */
    @Test
    public void testAssertEquals() {
        assertEquals("failure - strings not same", 5l, 5l);
    }

    /**
     * 查看两个对象是否不相等。
     */
    @Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }

    /**
     * 查看对象是否不为空。
     */
    @Test
    public void testAssertNotNull() {
        assertNotNull("should not be null", new Object());
    }

    /**
     * 查看对象是否为空。
     */
    @Test
    public void testAssertNull() {
        assertNull("should be null", null);
    }

    /**
     * 查看两个对象的引用是否相等。类似于使用“==”比较两个对象
     */
    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(768);
        assertSame("should be same", aNumber, aNumber);
    }

    /**
     * 查看两个对象的引用是否不相等。类似于使用“!=”比较两个对象
     */

    @Test
    public void testAssertNotSame() {
        assertNotSame("should not be same Object", new Object(), new Object());
    }
}