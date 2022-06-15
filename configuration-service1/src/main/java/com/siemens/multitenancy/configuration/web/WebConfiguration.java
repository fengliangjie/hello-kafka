package com.siemens.multitenancy.configuration.web;

import com.siemens.multitenancy.configuration.interceptor.TenantRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
    private TenantRequestInterceptor tenantInterceptor;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor).addPathPatterns("/api/**").excludePathPatterns("/swagger-ui.html");
    }

}
