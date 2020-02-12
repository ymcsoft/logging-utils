package com.ymcsoft.logging.util;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static final <T>  T create(T obj) {
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new LoggingProxy(obj));
    }

    public static final <T,R>  R create(T obj, Class<R>... iface) {
        return (R) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                iface,
                new LoggingProxy(obj));
    }
}
