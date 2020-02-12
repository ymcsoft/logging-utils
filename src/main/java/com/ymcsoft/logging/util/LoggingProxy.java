package com.ymcsoft.logging.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingProxy implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingProxy.class);

    private final Object obj;

    public LoggingProxy(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Logging.class) || obj.getClass().isAnnotationPresent(Logging.class)){
            Logging annotation = method.getAnnotation(Logging.class);
            LoggingUtil.Levels loggingLevel = StringUtils.isNotBlank(annotation.level())?
                    LoggingUtil.Levels.valueOf(annotation.level()) : LoggingUtil.Levels.DEBUG;
            return LoggingUtil.logEx(LOGGER, loggingLevel, obj.getClass().getSimpleName() + "." + method.getName(), () -> method.invoke(obj, args));
        }

        return method.invoke(obj, args);
    }
}
