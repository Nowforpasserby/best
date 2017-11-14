package com.logictech.config;

import com.logictech.entity.so.ParamValidException;
import com.logictech.entity.so.ResultEntity;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.LinkedList;
import java.util.List;

import static com.logictech.App.logger;

/**
 * @author JG.Hannibal
 * @since 2017/11/10 上午10:58
 * @desc 其他自定义异常, 请加上 @ResponseStatus(value = HttpStatus.XXXXX)
 */
@RestController
@ControllerAdvice
public class GlobalExceptionConfig {
    /**
     * Hibernate-Validator 异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ParamValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResultEntity paramValidExceptionHandler(ParamValidException ex) {
        ResultEntity result = new ResultEntity(ex);
        result.setData(ex.getFieldErrors());
        return result;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResultEntity bindExceptionHandler(BindException ex) {
        return paramValidExceptionHandler(new ParamValidException(ex));
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResultEntity undeclaredThrowableException(UndeclaredThrowableException ex) throws Exception {
        // 获得实际异常
        Throwable throwable = ex.getUndeclaredThrowable();
        // 如果是我们自定义异常就调用自定义异常的处理方法
        if (throwable instanceof ParamValidException) {
            return paramValidExceptionHandler((ParamValidException) throwable);
        }
        return exception(ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public ResultEntity exception(Exception ex) throws Exception {
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }
        // 原始错误集合数据偏多，包含native错误
        final StackTraceElement[] stackTrace = ex.getStackTrace();
        // 过滤错误信息
        final List<StackTraceElement> filters = new LinkedList<>();
        for (StackTraceElement st : stackTrace) {
            if (st.getClassName().contains("logictech.") && st.getFileName().contains(".java")) {
                filters.add(st);
            }
        }
        final StackTraceElement[] dwStackTrace = new StackTraceElement[filters.size()];
        // 计数器
        Integer i = 0;
        for (StackTraceElement filter : filters) {
            dwStackTrace[i++] = filter;
        }
        // 填充dwStackTrace
        ex.setStackTrace(dwStackTrace);
        ex.printStackTrace();
        logger.error(ex.getMessage(), ex);
        return new ResultEntity(ex);
    }

}
    