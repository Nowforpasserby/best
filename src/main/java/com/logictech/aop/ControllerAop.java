package com.logictech.aop;

import com.alibaba.fastjson.JSON;
import com.logictech.entity.so.FieldError;
import com.logictech.entity.so.ParamValidException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.logictech.App.logger;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 下午3:43
 */
@Aspect
@Component
public class ControllerAop {
    /**
     * 一分钟，即1000ms
     */
    private static final long ONE_MINUTE = 1000;
    private static final String HEAD = "##########|    ";

    @Pointcut("execution(* com.logictech.web..*(..))")
    public void controllerPointCut() {
    }

    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Before("controllerPointCut()")
    public void doBefore(JoinPoint joinPoint) throws ParamValidException {
        // Receives the request and get request content
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info(HEAD + "URL : " + request.getRequestURL().toString());
        logger.info(HEAD + "HTTP_METHOD : " + request.getMethod());
        logger.info(HEAD + "IP : " + request.getRemoteAddr());
        logger.info(HEAD + "CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // =================================
        // 获得切入目标对象
        Object target = joinPoint.getThis();
        // 获得切入方法参数
        Object[] args = joinPoint.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

        if (!validResult.isEmpty()) {
            // 获得方法的参数名称
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            List<FieldError> errors = validResult.stream().map(constraintViolation -> {
                // 获得校验的参数路径信息
                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
                // 获得校验的参数位置
                int paramIndex = pathImpl.getLeafNode().getParameterIndex();
                // 获得校验的参数名称
                String paramName = parameterNames[paramIndex];
                // 将需要的信息包装成简单的对象，方便后面处理
                FieldError error = new FieldError();
                // 参数名称（校验错误的参数名称）
                error.setName(paramName);
                // 校验的错误信息
                error.setMessage(constraintViolation.getMessage());
                return error;
            }).collect(Collectors.toList());
            // 我个人的处理方式，抛出异常，交给上层处理
            logger.error(HEAD + "参数校验发生错误 ===> {}", errors.toString());
            throw new ParamValidException(errors);
        }

    }

    @AfterReturning(returning = "ret", pointcut = "controllerPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // Processes the request and returns the content
        logger.info(HEAD + "RESPONSE : " + JSON.toJSONString(ret));
        logger.info("====================================================");
    }


    @Around("controllerPointCut()")
    public Object interceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        stopWatch.stop();

        Long cost = stopWatch.getTotalTimeMillis();
        if (cost > ONE_MINUTE) {
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

            logger.info(HEAD + "执行 {} 方法, 用时: {} ms.", methodName, cost);
        }

        logger.info("====================================================");
        return obj;
    }

    /**
     * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
     *
     * @param methodName
     * @param startTime
     * @param endTime
     */
    private void printExecTime(String methodName, long startTime, long endTime) {
        long diffTime = endTime - startTime;
        if (diffTime > ONE_MINUTE) {
            logger.warn(HEAD + methodName + " 方法执行耗时：" + diffTime + " ms.");
        }
    }

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }

}
    