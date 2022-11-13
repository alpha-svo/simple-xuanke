package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.service.UserService;
import com.example.utils.Base64Utils;
import com.example.utils.JWTUtils;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public R login(String username, String password, HttpServletRequest request) {
        String pwd = Base64Utils.encode(password);
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Student::getNumber, username).eq(Student::getPassword, pwd);
        Student stu = studentService.getOne(lqw);
        if (stu != null) {
            String token = JWTUtils.generateToken(stu, request);
            return R.ok("登录成功", token);
        } else {
            return R.error("用户不存在");
        }
    }

    @GetMapping("/verify")
    public R verify(String token) {
        boolean flag = JWTUtils.verifyToken(token);
        String username = JWTUtils.getSubject(token);
        return flag ? R.ok(username) : R.error("验证失败");
    }


    //用于接口测试
    @PutMapping("/test")
    public R test(@RequestBody Student stu) {
        return R.ok("ok", stu);
    }
}
