package com.sergey.prykhodko.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {
    public static String encodePassword(String password){
        return DigestUtils.sha256Hex(password);
    }
}
