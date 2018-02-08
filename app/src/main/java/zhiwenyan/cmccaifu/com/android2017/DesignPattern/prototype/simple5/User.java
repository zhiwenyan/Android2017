package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple5;

/**
 * Description:
 * Data：2/7/2018-11:25 AM
 *
 * @author: yanzhiwen
 */
public class User implements Cloneable {
    public String userName;
    public int age;
    public Address userAddress;

    @Override
    protected User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        //地址也拷贝下 达到深拷贝
        user.userAddress = userAddress.clone();
        return user;
    }

    public static class Address implements Cloneable {
        public String addressName;
        public String city;

        public Address(String addressName, String city) {
            this.addressName = addressName;
            this.city = city;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "addressName='" + addressName + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }

        @Override
        protected Address clone() throws CloneNotSupportedException {
            return (Address) super.clone();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", userAddress=" + userAddress +
                '}';
    }
}
