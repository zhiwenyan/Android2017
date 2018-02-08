package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple4;

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
        return (User) super.clone();
    }

    public static class Address {
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
