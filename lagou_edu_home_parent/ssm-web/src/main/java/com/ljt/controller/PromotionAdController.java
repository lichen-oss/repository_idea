package com.ljt.controller;

import com.github.pagehelper.PageInfo;
import com.ljt.domain.PromotionAd;
import com.ljt.domain.PromotionAdVo;
import com.ljt.domain.ResponseResult;
import com.ljt.service.PromotionAdService;
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

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAd")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo) {
        PageInfo allAdByPage = promotionAdService.findAllAdByPage(promotionAdVo);
        return new ResponseResult(true, 200, "响应成功", allAdByPage);

    }

    @RequestMapping("/PromotionAdUpload")
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

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        try {
            if (promotionAd.getId() == null) {
                promotionAdService.savePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",
                        null);
                return result;
            } else {
                promotionAdService.updatePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",
                        null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id) {
        try {
            PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionAd);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id, @RequestParam
            int status) {
        try {
            if (status == 1) {
                promotionAdService.updatePromotionAdStatus(id, status);
            }else {
                promotionAdService.updatePromotionAdStatus(id, 0);
            }
            HashMap<String, Integer> map = new HashMap<>();
            map.put("status", status);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
