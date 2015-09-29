package com.crossover.utility;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is web initializer class of Spring in Test Exam System.
 *
 */
public class SpringWebIntializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
