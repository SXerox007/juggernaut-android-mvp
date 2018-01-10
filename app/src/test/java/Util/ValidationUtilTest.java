package Util;

import com.skeleton.mvp.util.ValidationUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sumitthakur on 10/01/18.
 */

public class ValidationUtilTest {

    @Test
    public void emailValidator_incorrectMail_returnsFalse() throws Exception {
        Assert.assertEquals(false, ValidationUtil.checkEmail("abc1"));
    }

    @Test
    public void emailValidator_correctMail_returnsTrue() throws Exception {
        Assert.assertEquals(true, ValidationUtil.checkEmail("abc@gmail.com"));
        Assert.assertEquals(true, ValidationUtil.checkEmail("sumit.thakur@gmail.co"));

    }

    @Test
    public void passwordValidator_emptyPassword_returnsFalse() throws Exception {
        Assert.assertEquals(false, ValidationUtil.checkPassword(""));
    }

    @Test
    public void passwordValidator_invalidPassword_returnsFalse() throws Exception {
        Assert.assertEquals(false, ValidationUtil.checkPassword("   "));
    }

    @Test
    public void nameValidator_emptyName_returnsFalse() throws Exception {
        Assert.assertEquals(false, ValidationUtil.validateName(""));
    }

    @Test
    public void nameValidator_invalidName_returnsFalse() throws Exception {
        Assert.assertEquals(false, ValidationUtil.validateName("23"));
    }

    @Test
    public void nameValidator_validName_returnsTrue() throws Exception {
        Assert.assertEquals(true, ValidationUtil.validateName("Sumit Thakur"));
    }
}
