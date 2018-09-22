package com.assistant.result;

import java.io.Serializable;

/**
 * @author ：WKH.
 * @date ：2018-09-22.
 */
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标识
     */
    public static final int SUCCESS_CODE = 1;

    /**
     * 失败标识
     */
    public static final int ERROR_CODE = -1;

    /**
     * 返回信息正常
     */
    private static final String MESSAGE = "正常";

    /**
     * 状态码 成功：1，失败：-1
     */
    private int state;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 无参正常返回
     */
    public JsonResult() {
        this.state = SUCCESS_CODE;
        this.message = MESSAGE;
        this.data = null;
    }

    /**
     * 自定义返回
     *
     * @param state   状态 （正常=> 1、 异常 =>-1 请正确填写）
     * @param message 信息
     */
    public JsonResult(int state, String message) {
        this.state = state;
        this.message = message;
        this.data = null;
    }

    /**
     * 自定义返回
     *
     * @param state   状态 （正常=> 1、 异常 =>-1 请正确填写）
     * @param message 信息
     * @param data    数据
     */
    public JsonResult(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }


    /**
     * 有参正常返回
     *
     * @param data 返回数据
     */
    public JsonResult(T data) {
        this.state = SUCCESS_CODE;
        this.message = MESSAGE;
        this.data = data;
    }

    /**
     * 异常返回
     *
     * @param e 异常
     */
    public JsonResult(Throwable e) {
        this.state = ERROR_CODE;
        this.message = e.getMessage();
        this.data = null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult[state=" + state + ", message=" + message + ", data=" + data + "]";
    }

}
