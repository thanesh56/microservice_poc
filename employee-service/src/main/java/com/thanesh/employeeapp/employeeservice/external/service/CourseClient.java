package com.thanesh.employeeapp.employeeservice.external.service;

import com.thanesh.employeeapp.employeeservice.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE", path = "/course-app/api")
public interface CourseClient {
    @GetMapping("/courses")
    List<CourseResponse> getAllCourse();

    @GetMapping("/courses/{courseId}")
    CourseResponse getCourseById(@PathVariable("courseId") Integer courseId);
}
