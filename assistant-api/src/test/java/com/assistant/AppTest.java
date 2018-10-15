package com.assistant;


import com.assistant.util.RandomGeneratorUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * 中文加号
     */
    private static final String CHINESE_PLUS_SIGN = "加";


    private static RandomNumberGenerator RANDOM_NUMBER_GENERATOR = new SecureRandomNumberGenerator();

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        String salt = RANDOM_NUMBER_GENERATOR.nextBytes().toHex();
        System.out.println(salt);
        String username = "test";
        String password = "123456";
        String credentialsSalt = username + salt;
        String newPassword = new SimpleHash("md5", password, ByteSource.Util.bytes(credentialsSalt), 2).toHex();
        System.out.println(newPassword);

    }

    @Test
    public void randomData() {
        System.out.println(RandomGeneratorUtil.getRandomCharAndNumber(4));

        System.out.println(RandomGeneratorUtil.toChineseUpper(10));

        int num1 = RandomGeneratorUtil.getRandom(10);
        int num2 = RandomGeneratorUtil.getRandom(10);

        int result = num1 + num2;

        System.out.println(RandomGeneratorUtil.toChineseUpper(num1) + CHINESE_PLUS_SIGN + RandomGeneratorUtil.toChineseUpper(num2)
                + "=?");


    }

}
