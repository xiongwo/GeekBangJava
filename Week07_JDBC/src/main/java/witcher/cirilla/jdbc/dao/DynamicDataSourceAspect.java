package witcher.cirilla.jdbc.dao;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Lazy(false)
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void begin(JoinPoint joinPoint, Datasource ds) {
        DynamicDataSourceContextHolder.setDataSourceRouterKey(ds.name());
    }

    @After("@annotation(ds)")
    public void after(JoinPoint point, Datasource ds) {
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
    }

    public Datasource getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        Datasource annotation = objMethod.getDeclaredAnnotation(Datasource.class);
        // 返回
        return annotation;
    }

}
