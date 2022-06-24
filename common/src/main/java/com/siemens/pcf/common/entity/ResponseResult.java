/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.pcf.common.entity;

import java.io.Serializable;

import com.siemens.pcf.common.util.DateTimeUtil;

import lombok.Data;

/**
 * Server response result entity.
 *
 * @author pdai
 * @param <T>
 *            response data type
 */
@Data
public class ResponseResult<T> {

    /**
     * response timestamp.
     */
    private long timestamp;

    /**
     * response code, 200 -> OK.
     */
    private int code;

    /**
     * response message.
     */
    private String message;

    /**
     * response data.
     */
    private T data;

    /**
     * response success result wrapper.
     *
     * @param <T>
     *            type of data class
     * @return response result
     */
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    /**
     * response success result wrapper.
     *
     * @param data
     *            response data
     * @param <T>
     *            type of data class
     * @return response result
     */
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setData(data);
        responseResult.setCode(ResponseStatus.SUCCESS.getResponseCode());
        responseResult.setMessage(ResponseStatus.SUCCESS.getDescription());
        responseResult.setTimestamp(DateTimeUtil.currentTime());
        return responseResult;
    }

    /**
     * response error result wrapper.
     *
     * @param message
     *            error message
     * @param <T>
     *            type of data class
     * @return response result
     */
    public static <T extends Serializable> ResponseResult<T> fail(String message) {
        return fail(null, message);
    }

    /**
     * response error result wrapper.
     *
     * @param data
     *            response data
     * @param message
     *            error message
     * @param <T>
     *            type of data class
     * @return response result
     */
    public static <T> ResponseResult<T> fail(T data, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setData(data);
        responseResult.setCode(ResponseStatus.FAIL.getResponseCode());
        responseResult.setMessage(message);
        responseResult.setTimestamp(DateTimeUtil.currentTime());
        return responseResult;
    }

}
