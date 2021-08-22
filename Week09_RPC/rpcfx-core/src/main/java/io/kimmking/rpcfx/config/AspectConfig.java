package io.kimmking.rpcfx.config;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// MARK
@Aspect
@Component
@Log
public class AspectConfig {

    @Pointcut("execution(public * io.kimmking.rpcfx.client.Rpcfx.RpcfxInvocationHandler.invoke(..))")
    public void logPointcut(){}

    @AfterThrowing
    public void logThrowing(JoinPoint joinPoint, Throwable e) {
        log.info(">>>>>>>>>>>>>>>>>>>>> Throwing Exception Start");
        log.info("request method name: " + joinPoint.getSignature().getName());
        log.info("exception description: " + e);
        log.info(">>>>>>>>>>>>>>>>>>>>> Throwing Exception End");
    }

}
