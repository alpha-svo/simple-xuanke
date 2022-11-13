package com.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private int code;
    private String msg;
    private long count;
    private Object data;

    public static R ok(String msg, long count, Object data) {
        return new R(0, msg, count, data);
    }

    public static R ok(String msg, Object data) {
        return new R(0, msg, 0L, data);
    }

    public static R ok(String msg) {
        return new R(0, msg, 0L, null);
    }

    public static R error(String msg) {
        return new R(1, msg, 0L, null);
    }
}
