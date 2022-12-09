package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        byte a = 12;
        short b = 21;
        int c = 34;
        float d = 2.13f;
        double e = -0.14;
        boolean f = true;
        char g = 'a';
        long h = 834578347;
        LOG.debug("a: {}, b: {}, c: {}, d: {}, e: {}, f: {}, g: {}, h: {}", a, b, c, d, e, f, g, h);
    }
}