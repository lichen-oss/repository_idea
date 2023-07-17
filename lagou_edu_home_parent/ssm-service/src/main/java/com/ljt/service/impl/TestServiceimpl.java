package com.ljt.service.impl;

import com.ljt.dao.TestMaper;
import com.ljt.domain.Test;
import com.ljt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceimpl implements TestService {
    @Autowired
    private TestMaper testMaper;
    @Override
    public List<Test> findAll() {
        List<Test> tests = testMaper.findAll();
        return tests;
    }
}
