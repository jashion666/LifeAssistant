package com.assistant.result;

import com.assistant.enums.ResultCodeEnum;

import java.io.Serializable;

/**
 * @author ：WKH.
 * @date ：2018-09-22.
 */
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回信息正常
     */
    private static final String SUCCESS_MESSAGE = "正常";

    /**
     * 返回信息正常
     */
    private static final String FAILED_MESSAGE = "失败";

    /**
     * 状态码
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

    private JsonResult() {

    }

    /**
     * 无参正常返回
     */
    public static JsonResult successResult() {
        JsonResult result = new JsonResult();
        result.state = ResultCodeEnum.RESULT_SUCCESS_CODE.getCode();
        result.message = SUCCESS_MESSAGE;
        result.data = null;
        return result;
    }

    /**
     * 自定义正常返回
     *
     * @param message 信息
     */
    public static JsonResult successResult(String message) {
        JsonResult result = new JsonResult();
        result.state = ResultCodeEnum.RESULT_SUCCESS_CODE.getCode();
        result.message = message;
        result.data = null;
        return result;
    }

    /**
     * 自定义正常返回
     *
     * @param message 信息
     * @param data    数据
     */
    public static <T> JsonResult successResult(String message, T data) {
        JsonResult result = new JsonResult();
        result.state = ResultCodeEnum.RESULT_SUCCESS_CODE.getCode();
        result.message = message;
        result.data = data;
        return result;
    }

    /**
     * 无参失败返回
     */
    public static JsonResult failedResult() {
        JsonResult result = new JsonResult();
        result.state = ResultCodeEnum.RESULT_FAILED_CODE.getCode();
        result.message = FAILED_MESSAGE;
        result.data = null;
        return result;
    }

    /**
     * 自定义失败返回
     *
     * @param message 信息
     */
    public static JsonResult failedResult(String message) {
        JsonResult result = new JsonResult();
        result.state = ResultCodeEnum.RESULT_FAILED_CODE.getCode();
        result.message = message;
        result.data = null;
        return result;
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
