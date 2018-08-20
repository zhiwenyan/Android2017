package zhiwenyan.cmccaifu.com.android2017;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}