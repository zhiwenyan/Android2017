package zhiwenyan.cmccaifu.com.android2017;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @ https://juejin.im/post/5b57e3fbf265da0f47352618
 * Description:
 *
 * 单元测试的目的以及测试内容
 为什么要进行单元测试？
 提高稳定性，能够明确地了解是否正确的完成开发；
 快速反馈bug，跑一遍单元测试用例，定位bug；
 在开发周期中尽早通过单元测试检查bug，最小化技术债，越往后可能修复bug的代价会越大，严重的情况下会影响项目进度；
 为代码重构提供安全保障，在优化代码时不用担心回归问题，在重构后跑一遍测试用例，没通过说明重构可能是有问题的，更加易于维护。
 单元测试要测什么？
 列出想要测试覆盖的正常、异常情况，进行测试验证;
 性能测试，例如某个算法的耗时等等。
 单元测试的分类
 本地测试(Local tests): 只在本地机器JVM上运行，以最小化执行时间，这种单元测试不依赖于Android框架，或者即使有依赖，
 也很方便使用模拟框架来模拟依赖，以达到隔离Android依赖的目的，模拟框架如google推荐的[Mockito][1]；
 仪器化测试(Instrumented tests): 在真机或模拟器上运行的单元测试，由于需要跑到设备上，比较慢，这些测试可以访问仪器（Android系统）信息，
 比如被测应用程序的上下文，一般地，依赖不太方便通过模拟框架模拟时采用这种方式。
 * Data：8/7/2018-10:25 AM
 *
 * @author yanzhiwen
 */
public class EmailValidatorTest {

    @Test(timeout=100)
    public void isValidEmail() {
        System.out.println(System.currentTimeMillis());
        assertThat(EmailValidator.isValidEmail("name@email.com"), is(true));
        System.out.println(System.currentTimeMillis());
    }
}