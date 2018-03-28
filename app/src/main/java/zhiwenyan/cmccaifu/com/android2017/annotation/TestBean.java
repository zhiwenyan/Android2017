package zhiwenyan.cmccaifu.com.android2017.annotation;

/**
 * Created by yanzhiwen on 2017/10/26.
 */

public class TestBean {
    private String name;

    public TestBean() {

    }

    public TestBean(String name) {
        this.name = name;
    }

    private void getName() {
        System.out.println("getName");
    }

    private void setName() {

    }
}
