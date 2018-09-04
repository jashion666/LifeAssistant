package com.assistant.service.impl.test.write;

import com.alibaba.dubbo.config.annotation.Service;
import com.assistant.dao.test.write.TestWriteMapper;
import com.assistant.entity.test.TestServiceEntity;
import com.assistant.service.test.write.TestWriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/8/2.
 */
@Service(timeout = 20000)
@org.springframework.stereotype.Service
public class TestWriteServiceImpl implements TestWriteService {

    private static final Logger logger = LoggerFactory.getLogger(TestWriteServiceImpl.class);

    @Resource
    TestWriteMapper testWriteMapper;

    @Override
    public int saveUserInfo(TestServiceEntity user) {
        int result;
        for (int i = 0; i < 5; i++) {
            TestServiceEntity u = new TestServiceEntity();
            u.setUsername("测试" + i);
            u.setPassword("000" + i);
            result = testWriteMapper.saveUserInfo(u);
            logger.debug("aa" + result);
            System.out.println(result);
        }
        return 1/0;
    }
}
