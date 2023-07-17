package com.ljt.service;

import com.ljt.domain.Course;
import com.ljt.domain.CourseLesson;
import com.ljt.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    public Course findCourseByCourseId(int courseId);

    public void saveSection(CourseSection section);

    public void updateSection(CourseSection section);

    public void updateSectionStatus(int id,int status);

    public void saveLesson(CourseLesson courseLesson);

    public void updateLesson(CourseLesson courseLesson);
}
