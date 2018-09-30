package com.assistant.enums;

/**
 * 返回结果枚举类
 *
 * @author ：会写代码的厨师.
 * @date ：2018/9/29.
 */
public enum ResultCodeEnum {

    /**
     * 通用api错误code
     */
    RESULT_FAILED_CODE(-1),

    /**
     * 通用api成功code
     */
    RESULT_SUCCESS_CODE(1);



    private final int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
