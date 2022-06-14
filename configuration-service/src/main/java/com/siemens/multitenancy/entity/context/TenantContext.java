package com.siemens.multitenancy.entity.context;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 5:14 PM
 */
public abstract class TenantContext {

	public static final String DEFAULT_TENANT_ID = "multitenancy_default";
	private static ThreadLocal<String> currentTenant = new ThreadLocal<String>();

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
	
}
