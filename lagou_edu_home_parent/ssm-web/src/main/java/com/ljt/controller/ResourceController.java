package com.ljt.controller;

import com.github.pagehelper.PageInfo;
import com.ljt.domain.Resource;
import com.ljt.domain.ResourceCategory;
import com.ljt.domain.ResourceVo;
import com.ljt.domain.ResponseResult;
import com.ljt.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/resource")
@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {
        PageInfo<Resource> allResource =
                resourceService.findAllResource(resourceVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", allResource);
        return responseResult;
    }

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceService.findAllResourceCategory();
        return new ResponseResult(true, 100, "响应成功", allResourceCategory);

    }

    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource) {
        if (resource.getId() == null) {
            resourceService.saveResource(resource);
            return new ResponseResult(true, 200, "保存成功", "");
        } else {
            resourceService.updateResource(resource);
            return new ResponseResult(true, 200, "修改成功", "");
        }
    }

    @RequestMapping("/findResourceById")
    public ResponseResult findResourceById(@RequestParam Integer id) {
        Resource resourceById = resourceService.findResourceById(id);
        return new ResponseResult(true, 200, "响应成功", resourceById);

    }
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(@RequestParam Integer id){
        resourceService.deleteResource(id);
        return  new ResponseResult(true,200,"删除成功","");
    }
}
