package com.dikzz.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Created by dikzz on 8/17/16.
 */
public class TestWebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        DelegatingFilterProxy filter1 = new DelegatingFilterProxy("filter");
        filter1.setServletContext(servletContext);
        FilterRegistration.Dynamic filter = servletContext.addFilter("filter", filter1);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
