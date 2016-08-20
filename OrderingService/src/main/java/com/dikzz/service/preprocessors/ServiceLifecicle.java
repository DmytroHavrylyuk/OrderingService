package com.dikzz.service.preprocessors;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * Created by dikzz on 8/14/16.
 */
@Component
public class ServiceLifecicle implements Lifecycle {
    @Override
    public void start() {
        System.out.println("start");
    }

    @Override
    public void stop() {
        System.out.println("stop");
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
