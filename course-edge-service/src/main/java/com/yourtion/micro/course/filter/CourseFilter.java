package com.yourtion.micro.course.filter;

import com.yourtion.micro.user.client.LoginFilter;
import com.yourtion.micro.user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseFilter extends LoginFilter {

    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }
}
