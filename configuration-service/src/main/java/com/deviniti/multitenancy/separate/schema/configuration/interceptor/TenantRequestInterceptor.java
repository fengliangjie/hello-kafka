package com.deviniti.multitenancy.separate.schema.configuration.interceptor;

import com.deviniti.multitenancy.separate.schema.entity.context.TenantContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author: liangjie.feng
 * @date: 2022/5/31 3:25 PM
 */
@Component
public class TenantRequestInterceptor implements AsyncHandlerInterceptor{

	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		 return Optional.of(request)
				 .map(req -> req.getHeader("X-TENANT-ID"))
				 .map(this::setTenantContext)
				 .orElse(false);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		TenantContext.clear();
	}
	    
	private boolean setTenantContext(String tenant) {
		TenantContext.setCurrentTenant(tenant);
		return true;
	}
}
