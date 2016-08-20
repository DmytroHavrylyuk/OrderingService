package com.dikzz.service.tracking;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.engine.internal.JoinSequence;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by dikzz on 8/13/16.
 */
@Aspect
@Component
public class ServiceTracker {

    @Pointcut("execution(* com.dikzz.service.*.*(..))")
    public void servicesPointcut() {
    }

    @Before("servicesPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("Before" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "servicesPointcut()", returning = "some")
    public void doAfterReturn(JoinPoint joinPoint, Object some) {
        System.out.println("After " + joinPoint.toLongString() + " " + some);
    }

}
