package com.yourtion.micro.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yourtion.micro.course.dto.CourseDTO;
import com.yourtion.micro.course.dubbo.ICourseService;
import com.yourtion.micro.user.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Reference
    private ICourseService courseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseList(HttpServletRequest request) {

        var userDTO = (UserDTO) request.getAttribute("user");
        System.out.println(userDTO);

        return courseService.courseList();
    }
}
