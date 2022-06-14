package com.siemens.messagehandler.constant;

/**
 * @author: liangjie.feng
 * @date: 2022/6/10 3:05 PM
 */
public final class ConstantValus {
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

    public static final String CONFIGURATION_SERVICE_INFO_URL = "http://localhost:8886/api/v1/iconnector/info";

    public static final String PCF_SERVICE_DATA_URL = "http://";

    public static final String DASH = "-";

    public static final String POINT_TRANSLATE = "\\.";

}
