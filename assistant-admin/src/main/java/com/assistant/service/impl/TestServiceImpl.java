package com.assistant.service.impl;

import com.assistant.dao.TestMapper;
import com.assistant.entity.TestAdminEntity;
import com.assistant.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/7/31.
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public TestAdminEntity getTestInfo() {
        return testMapper.getTestInfo();
    }
}
