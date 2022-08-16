package com.siemens.iconnector.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

/**
 * @author: liangjie.feng
 * @date: 2022/8/10 16:43
 */
@Configuration
public class PropertiesConfig {

    @Bean
    Properties properties() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yml"));
        return yamlPropertiesFactoryBean.getObject();
    }
}
