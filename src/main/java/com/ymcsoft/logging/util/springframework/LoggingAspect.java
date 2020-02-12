package com.ymcsoft.logging.util.springframework;

import com.ymcsoft.logging.util.Logging;
import com.ymcsoft.logging.util.LoggingUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(logging)")
    public Object handle(ProceedingJoinPoint joinPoint, Logging logging) throws Exception {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod(); // get the origin method;
        LoggingUtil.Levels loggingLevel = StringUtils.isNotBlank(logging.level())?
                LoggingUtil.Levels.valueOf(logging.level()) : LoggingUtil.Levels.DEBUG;
        return LoggingUtil.logEx(LOGGER, loggingLevel, joinPoint.getTarget().getClass().getSimpleName() + "." + method.getName(), () -> executeMethod(joinPoint));
    }

    private Object executeMethod(ProceedingJoinPoint joinPoint) throws Exception {
        try{
            return joinPoint.proceed();
        } catch(Throwable th) {
            throw new Exception(th);
        }
    }
}
