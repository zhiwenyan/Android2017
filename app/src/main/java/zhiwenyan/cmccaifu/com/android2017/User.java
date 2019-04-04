package zhiwenyan.cmccaifu.com.android2017;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Description:
 * Data：3/21/2019-10:36 AM
 *
 * @author yanzhiwen
 */
public class User implements Serializable {

    public int id;
    public String username;
    public int age;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public static void main(String[] args) {
//        User user = new User(1, "jake");
//        try {
//            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cache.txt"));
//            outputStream.writeObject(user);
//            outputStream.close();
//            System.out.println("user=" + user);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("cache.txt"));
            User newUser = (User) inputStream.readObject();
            System.out.println("newUser=" + newUser);
            System.out.println("username=" + newUser.username);
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
