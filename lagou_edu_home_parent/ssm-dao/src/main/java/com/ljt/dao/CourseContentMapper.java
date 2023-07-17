package com.ljt.dao;

import com.ljt.domain.Course;
import com.ljt.domain.CourseLesson;
import com.ljt.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    public List<CourseSection> findSectionAndLessonByCourseId(int id);

    /**
     * 回显章节对应的课程信息
     * */
    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节
     * */
    public void saveSection(CourseSection section);

    /**
     * 修改章节
     * */
    public void updateSection(CourseSection section);

    public void updateSectionStatus(CourseSection section);

    public void saveLesson(CourseLesson courseLesson);

    public void updateLesson(CourseLesson courseLesson);
}
