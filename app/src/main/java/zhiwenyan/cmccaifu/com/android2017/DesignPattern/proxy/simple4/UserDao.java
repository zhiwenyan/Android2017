package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple4;

/**
 * Description:
 * Data：4/27/2018-11:10 AM
 *
 * @author yanzhiwen
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");

    }
}
