package com.dikzz.service.preprocessors;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * Created by dikzz on 8/14/16.
 */
@Component
public class MyEventListener {

    @EventListener
    public void onStart(ContextStartedEvent event) {
        System.out.println("start");
    }

    @EventListener
    public void onStop(ContextStoppedEvent event) {
        System.out.println("stop");
    }

    @EventListener
    public void onClose(ContextClosedEvent event) {
        System.out.println("close");
    }

    @EventListener
    public void onRefresh(ContextRefreshedEvent event) {
        System.out.println("refresh");
    }

    @EventListener
    public void onRequest(RequestHandledEvent event) {
        System.out.println("request " + event.getUserName() + " " + event.getSessionId() + " " + event.getShortDescription());
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    }


}
