package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @TableId(type = IdType.AUTO)
    private Long courseid;
    private String course;
    private Integer fromdep;
    private String teacher;
    private String duration;
    private Integer deleted;
    private Integer stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course1 = (Course) o;
        return Objects.equals(courseid, course1.courseid) && Objects.equals(course, course1.course) && Objects.equals(fromdep, course1.fromdep) && Objects.equals(teacher, course1.teacher) && Objects.equals(duration, course1.duration) && Objects.equals(deleted, course1.deleted) && Objects.equals(stock, course1.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseid, course, fromdep, teacher, duration, deleted, stock);
    }
}
