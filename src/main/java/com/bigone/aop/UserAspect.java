package com.bigone.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 声明切面
 * 将切面对象交给spring管理
 * */
@Aspect
@Component
public class UserAspect {


    /**
     * 定义切点
     * 连接点的集合，定位目标方法
     * */
    @Pointcut("execution(* com.bigone.aop.UserTransfer.*(..))")// the pointcut expression
    private void anyDateTransfer() {}


    /**
     * 为切点绑定通知
     */
    @Before("com.bigone.aop.UserAspect.anyDateTransfer()")
    public void doAccessCheck() {
        // 执行
        System.out.println("-------before---------");
    }

    @After("com.bigone.aop.UserAspect.anyDateTransfer()")
    public void doLog() {
        // 执行
        System.out.println("-------after---------");
    }

    //环绕通知
    @Around("com.bigone.aop.UserAspect.anyDateTransfer()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-------around start---------");
        joinPoint.proceed();
        System.out.println("-------around end---------");
    }

}
