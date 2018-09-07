package zhiwenyan.cmccaifu.com.android2017;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 * 2. 执行顺序

 一个测试类单元测试的执行顺序为：

 @BeforeClass –> @Before –> @Test –> @After –> @AfterClass

 每一个测试方法的调用顺序为：

 @Before –> @Test –> @After
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest2 {

    /**
     * 针对所有测试，只执行一次，且必须为static void
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("in BeforeClass================");
    }

    /**
     * 针对所有测试，只执行一次，且必须为static void
     * @throws Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("in AfterClass=================");
    }

    /**
     * 初始化方法
     */
    @Before
    public void before() {
        System.out.println("in Before");
    }

    /**
     * @After
     */
    @After
    public void after() {
        System.out.println("in After");
    }

    /**
     * 测试方法，在这里可以测试期望异常和超时时间

     */
    @Test(timeout = 10000)
    public void testadd() {
        JDemo a = new JDemo();
        assertEquals(6, a.add(3, 3));
        System.out.println("in Test ----Add");
    }

    @Test
    public void testdivision() {
        JDemo a = new JDemo();
        assertEquals(3, a.division(6, 2));
        System.out.println("in Test ----Division");
    }

    /**
     * 忽略的测试方法
     */
    @Ignore
    @Test
    public void test_ignore() {
        JDemo a = new JDemo();
        assertEquals(6, a.add(1, 5));
        System.out.println("in test_ignore");
    }

}

class JDemo extends Thread {

    int result;

    public int add(int a, int b) {
        try {
            sleep(1000);
            result = a + b;
        } catch (InterruptedException e) {
        }
        return result;
    }

    public int division(int a, int b) {
        return result = a / b;
    }


}