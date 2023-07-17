package com.ljt.service;

import com.ljt.domain.Course;
import com.ljt.domain.CourseVO;
import com.ljt.domain.Teacher;

import java.util.List;

public interface CourseService {

    public List<Course> findCourseByConditioin(CourseVO courseVO);

    public void saveCourseOrTeacher(CourseVO courseVO);

    public CourseVO findCourseById(int id);

    public void updateCourseOrTeacher(CourseVO courseVO);

    //public void updateTeacher(Teacher teacher);
    public void updateCourseStatus(int id,int status);
}
