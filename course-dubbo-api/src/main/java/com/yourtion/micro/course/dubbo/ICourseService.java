package com.yourtion.micro.course.dubbo;

import com.yourtion.micro.course.dto.CourseDTO;

import java.util.List;

public interface ICourseService {

    List<CourseDTO> courseList();
}
