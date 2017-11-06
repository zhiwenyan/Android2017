package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple1;

/**
 * Created by yanzhiwen on 2017/11/6.
 * <p>
 * 被代理的对象
 */

public class Man implements IBank {
    private String name;

    public Man(String name) {
        this.name = name;
    }

    @Override
    public void applyBank() {
        System.out.println(name + "申请绑卡");
    }
}
