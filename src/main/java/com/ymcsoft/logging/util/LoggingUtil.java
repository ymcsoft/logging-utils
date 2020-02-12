package com.ymcsoft.logging.util;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.ymcsoft.function.util.Action;
import com.ymcsoft.function.util.ActionWithException;
import com.ymcsoft.function.util.BooleanSupplierWithException;
import com.ymcsoft.function.util.SupplierWithException;
import org.slf4j.Logger;

public final class LoggingUtil {
    public enum Levels {ERROR, WARN, INFO, DEBUG, TRACE}

    /**
     * Logs time value Supplier required to to execute in ms
     * @param logger Logger
     * @param level Log level (ERROR, WARN, INFO, DEBUG, TRACE)
     * @param msg String custom message displayed
     * @param supplier Supplier&lt;T&gt; supplier functions
     * @return T object
     */
    public static <T> T log(Logger logger, Levels level, String msg, Supplier<T> supplier) {
        log(logger, level, String.format("%s is starting...", msg));
        long start = System.currentTimeMillis();
        try {
            return supplier.get();
        } finally {
            log(logger, level, String.format("%s call is completed in %d ms", msg, System.currentTimeMillis() - start ));
        }
    }

    /**
     * Logs time value Supplier required to to execute in ms
     * @param logger Logger
     * @param level Log level (ERROR, WARN, INFO, DEBUG, TRACE)
     * @param msg String custom message displayed
     * @param supplier Supplier&lt;T&gt; supplier functions
     * @return T object
     * @throws E  exception thrown
     */
    public static <T, E extends Exception> T logEx(Logger logger, Levels level, String msg, SupplierWithException<T,E> supplier) throws E {
        log(logger, level, String.format("%s is starting...", msg));
        long start = System.currentTimeMillis();
        try {
            return supplier.get();
        } finally {
            log(logger, level, String.format("%s call is completed in %d ms", msg, System.currentTimeMillis() - start ));
        }
    }

    /**
     * Logs time value BooleanSupplier required to to execute in ms
     * @param logger Logger
     * @param level Log level (ERROR, WARN, INFO, DEBUG, TRACE)
     * @param msg String custom message displayed
     * @param supplier Supplier&lt;T&gt; supplier functions
     * @return boolean value
     */
    public static boolean log(Logger logger, Levels level, String msg, BooleanSupplier supplier) {
        log(logger, level, String.format("%s is starting...", msg));
        long start = System.currentTimeMillis();
        try {
            return supplier.getAsBoolean();
        } finally {
            log(logger, level, String.format("%s call is completed in %d ms", msg, System.currentTimeMillis() - start ));
        }
    }

    /**
     * Logs time value BooleanSupplier required to to execute in ms
     * @param logger Logger
     * @param level Log level (ERROR, WARN, INFO, DEBUG, TRACE)
     * @param msg String custom message displayed
     * @param supplier Supplier&lt;T&gt; supplier functions
     * @return boolean value
     * @throws E  exception thrown
     */
    public static <E extends Exception> boolean logEx(Logger logger, Levels level, String msg, BooleanSupplierWithException<E> supplier) throws E {
        log(logger, level, String.format("%s is starting...", msg));
        long start = System.currentTimeMillis();
        try {
            return supplier.getAsBoolean();
        } finally {
            log(logger, level, String.format("%s call is completed in %d ms", msg, System.currentTimeMillis() - start ));
        }
    }

    /**
     * Logs time value Action required to execute in ms
     * @param logger Logger
     * @param level Log level (ERROR, WARN, INFO, DEBUG, TRACE)
     * @param msg String custom message displayed
     * @param action Action function
     */
    public static void log(Logger logger, Levels level, String msg, Action action) {
        log(logger, level, String.format("%s is starting...", msg));
        long start = System.currentTimeMillis();
        try {
            action.execute();
        } finally {
            log(logger, level, String.format("%s call is completed in %d ms", msg, System.currentTimeMillis() - start ));
        }
    }

    public static <E extends Exception> void logEx(Logger logger, Levels level, String msg, ActionWithException<E> action) throws E {
        log(logger, level, String.format("%s is starting...", msg));
        long start = System.currentTimeMillis();
        try {
            action.execute();
        } finally {
            log(logger, level, String.format("%s call is completed in %d ms", msg, System.currentTimeMillis() - start ));
        }
    }

    private static void log(Logger logger, Levels level, String message) {
        switch (level) {
            case TRACE:
                logger.trace(message);
                break;
            case DEBUG:
                logger.debug(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
                logger.warn(message);
                break;
        }
    }

}
