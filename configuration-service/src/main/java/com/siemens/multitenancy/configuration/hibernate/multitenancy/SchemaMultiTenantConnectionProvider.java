package com.siemens.multitenancy.configuration.hibernate.multitenancy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.siemens.multitenancy.entity.context.TenantContext;

@SuppressWarnings("serial")
public class SchemaMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {
	
	public static final String HIBERNATE_PROPERTIES_PATH = "/hibernate-%s.properties";
	private final Map<String, ConnectionProvider> connectionProviderMap;

	public SchemaMultiTenantConnectionProvider() {
		this.connectionProviderMap = new HashMap<>();
	}
	
	@Override
	protected ConnectionProvider getAnyConnectionProvider() {
		return getConnectionProvider(TenantContext.DEFAULT_TENANT_ID);
	}

	@Override
	protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
		return getConnectionProvider(tenantIdentifier);
	}
	
	private ConnectionProvider getConnectionProvider(String tenantIdentifier) {
		return Optional.ofNullable(tenantIdentifier)
				.map(connectionProviderMap::get)
				.orElseGet(() -> createNewConnectionProvider(tenantIdentifier));
	}

	private ConnectionProvider createNewConnectionProvider(String tenantIdentifier) {
		return Optional.ofNullable(tenantIdentifier)
				.map(this::createConnectionProvider)
				.map(connectionProvider -> {
					connectionProviderMap.put(tenantIdentifier, connectionProvider);
					return connectionProvider;
				})
				.orElseThrow(() -> new ConnectionProviderException("Cannot create new connection provider for tenant: "+tenantIdentifier));
	}
	
	private ConnectionProvider createConnectionProvider(String tenantIdentifier) {
		return Optional.ofNullable(tenantIdentifier)
				.map(this::getHibernatePropertiesForTenantId)
				.map(this::initConnectionProvider)
				.orElse(null);
	}
	
	private Properties getHibernatePropertiesForTenantId(String tenantId) {
        try {
        	Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(String.format(HIBERNATE_PROPERTIES_PATH, tenantId)));
			return properties;
		} catch (IOException e) {
			throw new RuntimeException("Cannot open hibernate properties: "+ HIBERNATE_PROPERTIES_PATH);
		}
	}

	private ConnectionProvider initConnectionProvider(Properties hibernateProperties) {
		DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();
		connectionProvider.configure(hibernateProperties);
		return connectionProvider;
	}
	
}
