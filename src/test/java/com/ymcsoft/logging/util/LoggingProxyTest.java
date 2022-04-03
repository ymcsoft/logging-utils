package com.ymcsoft.logging.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggingProxyTest {

    @Test
    public void testLoggingProxy() {
        Pet proxy = ProxyFactory.create(new Dog("Arf"));
        assertEquals("Arf", proxy.reply());
    }
}