package com.assistant.util;

import java.util.Random;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/10/12.
 */
public class RandomGeneratorUtil {

    /**
     * 大写汉字数组
     */
    private static final String[] NUM_UPPER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};

    /**
     * 最大的数
     */
    private static final int MAX_NUM = 10;

    /**
     * 获取随机字母数字组合
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String getRandomCharAndNumber(Integer length) {

        String str = "";
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // 字符串
            if (random.nextBoolean()) {
                int choice = random.nextBoolean() ? 65 : 97;
                // 取得大小写混合字符串
                str += (char) (choice + random.nextInt(26));
                continue;
            }

            str += String.valueOf(random.nextInt(10));
        }
        return str;
    }

    /**
     * 数字转化为大写的汉字(0-10范围内)
     *
     * @param num 将要转化的数字
     * @return 汉字数字
     */
    public static String toChineseUpper(int num) {

        if (num < 0 || num > MAX_NUM) {
            throw new RuntimeException("请输入0-10范围内的数字");
        }

        return NUM_UPPER[num];
    }

    /**
     * 生成随机数
     *
     * @param num 位数
     * @return 随机数
     */
    public static int getRandom(int num) {
        return new Random().nextInt(num) + 1;
    }

}
