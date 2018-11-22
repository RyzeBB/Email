package com.cwca.customer.course.service;

import com.cwca.customer.course.entity.Course;

import java.util.Map;

public interface CourseService {
    Map<String,Object> findObjects(
            String name,
            Integer valid,
            int pageCurrent);
    void validById(
            String idStr,
            Integer valid);
    void saveObject(Course entity);
    void updateObject(Course entity);
    Course findObjectById(Integer id);
}
