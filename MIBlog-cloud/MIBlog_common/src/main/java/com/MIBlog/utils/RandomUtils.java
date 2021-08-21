package com.MIBlog.utils;

import java.util.UUID;

/**
 * @author asuna
 */
public class RandomUtils {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
