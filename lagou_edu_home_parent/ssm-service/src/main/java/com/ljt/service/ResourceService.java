package com.ljt.service;

import com.github.pagehelper.PageInfo;
import com.ljt.domain.Resource;
import com.ljt.domain.ResourceCategory;
import com.ljt.domain.ResourceVo;

import java.util.List;

public interface ResourceService  {

    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);

    public List<ResourceCategory> findAllResourceCategory();

    void saveResource(Resource resource);

    void updateResource(Resource resource);

    Resource findResourceById(Integer id);

    void  deleteResource(Integer id);
}
