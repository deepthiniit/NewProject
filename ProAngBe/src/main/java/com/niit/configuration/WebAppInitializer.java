package com.niit.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	public WebAppInitializer() {
		System.out.println("hi");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebAppConfig.class, WebSocketconfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DBConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("inside");
		return new String[] { "/" };
	}

}