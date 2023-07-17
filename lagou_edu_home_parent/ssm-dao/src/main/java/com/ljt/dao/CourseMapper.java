package com.ljt.dao;

import com.ljt.domain.Course;
import com.ljt.domain.CourseVO;
import com.ljt.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    public List<Course> findCourseByConditioin(CourseVO courseVO);

    public int saveCourse(Course course);

    public void saveTeaecher(Teacher teacher);

    public CourseVO findCourseById(int id);

    public void updateCourse(Course course);

    public void updateTeacher(Teacher teacher);

    public void updateCourseStatus(Course course);
}
