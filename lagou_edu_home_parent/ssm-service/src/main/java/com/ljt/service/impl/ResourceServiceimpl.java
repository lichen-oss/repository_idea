package com.ljt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljt.dao.ResourceMapper;
import com.ljt.domain.Resource;
import com.ljt.domain.ResourceCategory;
import com.ljt.domain.ResourceVo;
import com.ljt.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ResourceServiceimpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public  PageInfo<Resource> findAllResource(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVo);
        PageInfo<Resource> PageInfo = new PageInfo<>(allResource);

        return PageInfo;
    }

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceMapper.findAllResourceCategory();
        return allResourceCategory;
    }

    @Override
    public void saveResource(Resource resource) {
        resource.setCreatedTime(new Date());
        resource.setUpdatedTime(new Date());
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy("system");
        resourceMapper.updateResource(resource);
    }

    @Override
    public Resource findResourceById(Integer id) {
        Resource resourceById = resourceMapper.findResourceById(id);
        return resourceById;
    }

    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }
}
