package com.dikzz.configuration.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * Created by dikzz on 7/25/16.
 */
public class TrackingInterceptor implements WebRequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(TrackingInterceptor.class);

    @Override
    public void preHandle(WebRequest request) throws Exception {
        logger.info("preHandle");
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        logger.info("postHandle");

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        logger.info("afterCompletion");
    }
}
