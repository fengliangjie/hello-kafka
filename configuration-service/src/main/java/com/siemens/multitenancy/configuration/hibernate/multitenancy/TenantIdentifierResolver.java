package com.siemens.multitenancy.configuration.hibernate.multitenancy;

import java.util.Optional;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.siemens.multitenancy.entity.context.TenantContext;

import static com.siemens.multitenancy.constant.ConstantValues.DEFAULT_TENANT_ID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		return Optional.ofNullable(TenantContext.getCurrentTenant())
				.orElse(DEFAULT_TENANT_ID);
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
