package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.Serializable;

/**
 * Description:
 * Data：11/7/2018-10:26 AM
 *
 * @author yanzhiwen
 */
public class User implements Serializable {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
