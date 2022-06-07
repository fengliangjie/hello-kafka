package com.deviniti.multitenancy.separate.schema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages="com.deviniti.multitenancy.separate.schema")
@EnableJpaRepositories(basePackages= "com.deviniti.multitenancy.separate.schema")
@EnableAspectJAutoProxy
public class SeparateSchemaMultitenancyApplication {
	
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context= SpringApplication.run(SeparateSchemaMultitenancyApplication.class, args);
	}

//	public static <T> T getBean(Class<T> beanClass) {
//		return Optional.ofNullable(context)
//				.map(ctx -> ctx.getBean(beanClass))
//				.orElse(null);
//				
//	}
}
