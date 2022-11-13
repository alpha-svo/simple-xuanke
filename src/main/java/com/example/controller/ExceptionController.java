package com.example.controller;

import com.example.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    public R exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return R.error("系统异常，请联系管理员！");
    }
}
