package com.example.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {
    public static String encode(String content){
        return Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8));
    }

    public static String uncode(String secret){
        byte[] decode = Base64.getDecoder().decode(secret);
        return new String(decode);
    }
}
