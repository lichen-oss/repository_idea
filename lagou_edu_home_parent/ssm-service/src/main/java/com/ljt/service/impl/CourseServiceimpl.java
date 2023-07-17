package com.ljt.service.impl;

import com.ljt.dao.CourseMapper;
import com.ljt.domain.Course;
import com.ljt.domain.CourseVO;
import com.ljt.domain.Teacher;
import com.ljt.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceimpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByConditioin(CourseVO courseVO) {
        List<Course> courseList = courseMapper.findCourseByConditioin(courseVO);
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
        Course course = new Course();
        try {
            BeanUtils.copyProperties(course,courseVO);
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);
            courseMapper.saveCourse(course);

            int id = course.getId();
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVO);
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            courseMapper.saveTeaecher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public CourseVO findCourseById(int id) {
        CourseVO course = courseMapper.findCourseById(id);
        return course;
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        try {
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVO);
            Date date = new Date();
            course.setUpdateTime(date);
            courseMapper.updateCourse(course);

            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVO);

            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);
            courseMapper.updateTeacher(teacher);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateCourseStatus(int id, int status) {
        Course course = new Course();
        course.setStatus(status);
        course.setId(id);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);

    }


}
