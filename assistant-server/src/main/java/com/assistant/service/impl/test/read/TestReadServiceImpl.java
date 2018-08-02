package com.assistant.service.impl.test.read;

import com.alibaba.dubbo.config.annotation.Service;
import com.assistant.dao.test.read.TestReadMapper;
import com.assistant.entity.test.TestServiceEntity;
import com.assistant.service.test.read.TestReadService;

import javax.annotation.Resource;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/8/2.
 */
@Service(timeout = 12000)
@org.springframework.stereotype.Service
public class TestReadServiceImpl implements TestReadService {

    @Resource
    TestReadMapper testReadMapper;

    @Override
    public TestServiceEntity findUserInfo() {
        return testReadMapper.findUserInfo();
    }

}
