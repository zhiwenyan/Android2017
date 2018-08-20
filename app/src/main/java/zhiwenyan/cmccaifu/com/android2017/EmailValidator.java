package zhiwenyan.cmccaifu.com.android2017;

import java.util.regex.Pattern;

/**
 * Description:
 * Data：8/7/2018-10:26 AM
 *
 * @author yanzhiwen
 */
public class EmailValidator {
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static boolean isValidEmail(String email) {
        return email.length() > 0 && Pattern.matches(REGEX_EMAIL, email);
    }
}
