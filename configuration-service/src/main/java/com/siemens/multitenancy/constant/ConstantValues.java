package com.siemens.multitenancy.constant;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * @author: liangjie.feng
 * @date: 2022/6/15 2:59 PM
 */
public final class ConstantValues {

    public static final List<String> TENANT_IDS = ImmutableList.of("siemens_001", "siemens_002");

    public static final String TENANT_ID = "tenantId";

    public static final String CONNECTOR_ID = "connectorId";

    public static final String MESSAGE_HANDLER_URL = "http://localhost:8887/api/v1/iconnector/registration";

    public static final String X_TENANT_ID = "X-TENANT-ID";

    public static final String HIBERNATE_PROPERTIES_PATH = "/hibernate-%s.properties";

    public static final String DB_MIGRATION_TENANTS = "db/migration/all";

    public static final String DB_MIGRATION_SPECYFIC_FOR_TENANT = "db/migration/%s";

    public static final String DEFAULT_TENANT_ID = "siemens_001";
}
