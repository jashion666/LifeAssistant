package com.assistant.exception;

/**
 * 自定义异常信息类
 *
 * @author ：WKH.
 * @date ：2018-09-22.
 */
public class CustomerException extends Exception {

    /**
     * 自定义异常信息
     */
    private String message;

    public CustomerException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
