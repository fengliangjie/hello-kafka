package com.siemens.multitenancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 4:00 PM
 */
@SpringBootApplication
@EntityScan(basePackages="com.siemens.multitenancy")
@EnableJpaRepositories(basePackages= "com.siemens.multitenancy")
@EnableAspectJAutoProxy
public class ConfigurationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServiceApplication.class, args);
	}
}
