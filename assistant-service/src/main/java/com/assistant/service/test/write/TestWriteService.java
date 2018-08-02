package com.assistant.service.test.write;

import com.assistant.entity.test.TestServiceEntity;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/8/2.
 */
public interface TestWriteService {

    /**
     * 保存用户信息
     * @param user 用户信息
     * @return int
     */
    int saveUserInfo(TestServiceEntity user);
}
