package com.ityang.ssm.utils;

import java.util.UUID;

/**
 * 获取UUid
 */
public class UuidUtils {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;

    }

    public static void main(String[] args) {
        String uuid = getUuid();
        System.out.println(uuid);
    }
}
