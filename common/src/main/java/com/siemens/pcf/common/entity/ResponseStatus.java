/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.pcf.common.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response status.
 *
 * @author pdai
 */
@Getter
@AllArgsConstructor
public enum ResponseStatus {

    /**
     * success.
     */
    SUCCESS(200, "success"),

    /**
     * fail.
     */
    FAIL(500, "failed"),

    /**
     * http 200.
     */
    HTTP_STATUS_200(200, "ok"),
    /**
     * http 400.
     */
    HTTP_STATUS_400(400, "request error"),
    /**
     * http 401.
     */
    HTTP_STATUS_401(401, "no authentication"),

    /**
     * http 403.
     */
    HTTP_STATUS_403(403, "no authorities"),
    /**
     * http 500.
     */
    HTTP_STATUS_500(500, "server error");

    /**
     * all http status.
     */
    public static final List<ResponseStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500));

    /**
     * response code.
     */
    private final int responseCode;

    /**
     * description.
     */
    private final String description;

}
