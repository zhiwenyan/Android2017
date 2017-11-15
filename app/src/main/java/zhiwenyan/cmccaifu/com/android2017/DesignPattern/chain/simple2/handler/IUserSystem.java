package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2.handler;

/**
 * Created by yanzhiwen on 11/15/2017.
 *
 * 校验用户的处理接口
 */

public interface IUserSystem {
    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param pwd
     * @return
     */
    public UserInfo queryUserInfo(String username, String pwd);
}
