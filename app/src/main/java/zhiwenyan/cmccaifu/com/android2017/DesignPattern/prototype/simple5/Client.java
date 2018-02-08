package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple5;

/**
 * Description:
 * Data：2/7/2018-11:29 AM
 *
 * @author: yanzhiwen
 */
public class Client {
    public static void main(String[] args) {
        User user = new User();
        user.age = 24;
        user.userName = "Steven";
        user.userAddress = new User.Address("上海", "金穗大厦");
        //深拷贝
        try {
            //拷贝对象
            User copyUser = user.clone();
            copyUser.userAddress.addressName = "纽约";
            System.out.println(user.toString());
            System.out.println(copyUser.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
