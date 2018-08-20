package zhiwenyan.cmccaifu.com.android2017;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Description:
 * Data：8/7/2018-10:25 AM
 *
 * @author yanzhiwen
 */
public class EmailValidatorTest {

    @Test
    public void isValidEmail() {
        assertThat(EmailValidator.isValidEmail("name@email.com"), is(true));
    }
}