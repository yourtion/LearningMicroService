package com.yourtion.micro.course;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.yourtion.micro.course.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDubbo
public class ServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(ServiceApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        var bean = new FilterRegistrationBean();

        var filter = new CourseFilter();
        bean.setFilter(filter);

        var urlPtterns = List.of("/*");
        bean.setUrlPatterns(urlPtterns);

        return bean;
    }
}
