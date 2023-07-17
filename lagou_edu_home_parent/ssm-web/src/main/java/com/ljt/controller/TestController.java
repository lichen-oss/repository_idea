package com.ljt.controller;


import com.ljt.domain.Test;
import com.ljt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
private TestService testService;

    @RequestMapping("findAll")
    public List<Test> findAll(){
        List<Test> tests = testService.findAll();
        return  tests;
    }
}
