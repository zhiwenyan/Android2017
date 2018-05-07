package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple4;

/**
 * Description:代理对象,静态代理
 * Data：4/27/2018-11:10 AM
 *
 * @author yanzhiwen
 */

public class UserDaoProxy implements IUserDao {
    //接收保存目标对象
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    public void save() {
        System.out.println("开始事务...");
        target.save();//执行目标对象的方法
        System.out.println("提交事务...");
    }
}