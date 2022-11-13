package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.Course;
import com.example.entity.User;
import com.example.mapper.CourseMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.utils.Base64Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SbMpCrudApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseMapper courseMapper;

    @Test
    void contextLoads() {
        System.out.println(Base64Utils.encode("123456"));
    }

    @Test
    void testMapper() {
//        User user = userMapper.selectById(1);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserid, 1);
        User user = userService.getOne(lqw);
        System.out.println(user);
//        userService.removeById(user);
    }

    @Test
    public void testJoin() {
        List<Course> courses = courseMapper.selectAll();
        System.out.println(courses);
    }

}
