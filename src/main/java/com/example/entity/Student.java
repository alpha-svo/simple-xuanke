package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(type = IdType.AUTO)
    private Long stuid;
    private String number;
    private String password;
    private String stuname;
    private String stugender;
    private Integer studep;
    private Integer stugrade;
    private Integer stuclass;
    @TableField(exist = false)
    private List<Course> courses;
}
