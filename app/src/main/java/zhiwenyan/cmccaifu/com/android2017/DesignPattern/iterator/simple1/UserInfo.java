package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple1;

/**
 * Created by yanzhiwen on 2017/11/9.
 */

public class UserInfo {
    public String userName;
    public String userPwd;
    public String userId;
    public String userSex;

    public UserInfo(String userName, String userPwd, String userId, String userSex) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userId = userId;
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userId='" + userId + '\'' +
                ", userSex='" + userSex + '\'' +
                '}';
    }

}
