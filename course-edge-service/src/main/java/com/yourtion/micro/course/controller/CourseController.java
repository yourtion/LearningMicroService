package com.yourtion.micro.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yourtion.micro.course.dto.CourseDTO;
import com.yourtion.micro.course.dubbo.ICourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseController {

    @Reference
    private ICourseService courseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseList() {
        return courseService.courseList();
    }
}
