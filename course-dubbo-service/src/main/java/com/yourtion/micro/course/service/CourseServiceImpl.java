package com.yourtion.micro.course.service;

import com.yourtion.micro.course.dto.CourseDTO;
import com.yourtion.micro.course.dubbo.ICourseService;
import com.yourtion.micro.course.mapper.CourseMapper;
import com.yourtion.micro.course.thrift.ServiceProvider;
import com.yourtion.micro.user.dto.TeacherDTO;
import com.yourtion.micro.user.thrift.UserInfo;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CourseDTO> courseList() {
        var courceDTOS = courseMapper.listCourse();
        if (courceDTOS != null) {
            for (var c : courceDTOS) {
                var teacherId = courseMapper.getCourseTeacher(c.getId());
                if (teacherId != null) {
                    try {
                        var userInfo = serviceProvider.getUserService().getTeacherById(teacherId);
                        c.setTeacher(trans2Teacher(userInfo));
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return courceDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        var teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(userInfo, teacherDTO);
        return teacherDTO;
    }
}
