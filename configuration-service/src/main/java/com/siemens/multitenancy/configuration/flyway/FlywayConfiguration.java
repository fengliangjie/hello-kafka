package com.siemens.multitenancy.configuration.flyway;

import com.siemens.multitenancy.configuration.hibernate.multitenancy.SchemaMultiTenantConnectionProvider;
import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.cfg.Environment;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Component
public class FlywayConfiguration {

	private static final String DB_MIGRATION_TENANTS = "db/migration/all";
	private static final String DB_MIGRATION_SPECYFIC_FOR_TENANT = "db/migration/%s";

	@PostConstruct
	Boolean tennantSchemaFlyway() {
		
		migrateTennants("siemens_001");
		migrateTennants("siemens_002");
		return true;
	}

	private void migrateTennants(String tenantId) {
		Pair<String, BasicDataSource> data = dataSource(tenantId);
		Flyway.configure()
				.locations(DB_MIGRATION_TENANTS, String.format(DB_MIGRATION_SPECYFIC_FOR_TENANT, tenantId))
				.baselineOnMigrate(true)
				.dataSource(data.getSecond())
				.schemas(data.getFirst())
				.load()
				.migrate();
	}
	
	public Pair<String, BasicDataSource> dataSource(String tenantId) {
		try {
			
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(String.format(SchemaMultiTenantConnectionProvider.HIBERNATE_PROPERTIES_PATH, tenantId)));
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.get(Environment.DRIVER).toString());
			dataSource.setUrl(properties.get(Environment.URL).toString());
			dataSource.setUsername(properties.get(Environment.USER).toString());
			dataSource.setPassword(properties.get(Environment.PASS).toString());
			return Pair.of(properties.get(Environment.DEFAULT_SCHEMA).toString(),  dataSource);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
				
	}
}
