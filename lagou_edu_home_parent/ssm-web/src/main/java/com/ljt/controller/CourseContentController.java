package com.ljt.controller;

import com.ljt.domain.*;
import com.ljt.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam int courseId) {
        try {
            List<CourseSection> sectionAndLessonByCourseId = courseContentService.findSectionAndLessonByCourseId(courseId);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", sectionAndLessonByCourseId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int courseId) {
        try {
//调用service
            Course course = courseContentService.findCourseByCourseId(courseId);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {
        try {
//判断携带id是修改操作否则是插入操作
            if (section.getId() == null) {
                courseContentService.saveSection(section);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                courseContentService.updateSection(section);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/updateSectionStatus")
        public ResponseResult updateSectionStatus(@RequestParam int id, @RequestParam int status){
        try {
            courseContentService.updateSectionStatus(id,status);
//封装最新的状态信息
            Map<String,Integer> map = new HashMap<>();
            map.put("status",status);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        try {
            if (courseLesson.getId() == null) {
                courseContentService.saveLesson(courseLesson);
                return new ResponseResult(true, 200, "响应成功", null);
            }else {
                courseContentService.updateLesson(courseLesson);
                return new ResponseResult(true, 200, "修改成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

}
