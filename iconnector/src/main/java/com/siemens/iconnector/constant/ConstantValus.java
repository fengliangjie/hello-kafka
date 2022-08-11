/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2022 Siemens AG
 *
 * Licensed under the Siemens Inner Source License 1.2
 ******************************************************************************/

package com.siemens.iconnector.constant;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/10 3:05 PM
 */
public final class ConstantValus {

    public static final List<String> JSON_MESSAGE = ImmutableList.of("dec");

    public static final String CONFIG_TOPIC = "iconnector-config.%s-%s";

    public static final String DATA_TOPIC = "iconnector-data.%s-%s";

    public static final String CONFIG_GROUP = "group-config";

    public static final String DATA_GROUP = "group-data";

    public static final String PRINCIPAL = "User:CN=%s";

    public static final String CONNECTOR_ID = "connectorId";

    public static final String DATA = "data";

    public static final String COLLECT_STATUS = "collectStatus";

    public static final String X_TENANT_ID = "X-TENANT-ID";

    public static final String TOPIC_PATTERN_DATA = "iconnector-data.*";

    public static final String TOPIC_PATTERN_INFO = "iconnector-info.*";

    public static final String GROUP_DATA = "group-data";

    public static final String GROUP_INFO = "group-info";

    public static final String CONFIGURATION_SERVICE_INFO_URL = "https://config-service.sigreen-connect.cn:8084/api/v1/iconnector/info";

    public static final String PCF_SERVICE_DATA_URL = "https://pcf-engine.sigreen-connect.cn:8082/pcf/api/v1/config/data/productconsumptionsync";

    public static final String DASH = "-";

    public static final String POINT_TRANSLATE = "\\.";

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String APPLICATION_JOSE = "application/jose";

    public static final String JWT_PRIVATE_KEY = "jwt.private";

    public static final String JWT_PUBLIC_KEY = "jwt.public.%s";

}
