package com.ljt.controller;

import com.ljt.domain.Course;
import com.ljt.domain.CourseVO;
import com.ljt.domain.ResponseResult;
import com.ljt.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByConditioin")
    public ResponseResult findCourseByConditioin(@RequestBody CourseVO courseVO) {
        List<Course> courseList = courseService.findCourseByConditioin(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);
        return responseResult;


    }

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            String realpath = request.getServletContext().getRealPath("/");

            String webappspath = realpath.substring(0, realpath.indexOf("ssm-web"));

            String fileName = file.getOriginalFilename();

            String newFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("."));

            String uploadPath = webappspath + "upload\\";
            File filePath = new File(uploadPath, newFileName);
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录" + filePath);
            }
            file.transferTo(filePath);

            HashMap<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:8080" + "/upload/" + newFileName);
            ResponseResult result = new ResponseResult(true, 200, "上传成功", map);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) {
        if (courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        }else{
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        }
    }
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id){
        CourseVO course = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;
    }
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id,@RequestParam int status){
        courseService.updateCourseStatus(id,status);
        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }
//    public ResponseResult updateCourseOrTeacher(@RequestBody CourseVO courseVO){
//
//    }
}
