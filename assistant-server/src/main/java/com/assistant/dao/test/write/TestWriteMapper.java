package com.assistant.dao.test.write;

import com.assistant.entity.test.TestServiceEntity;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/8/2.
 */
public interface TestWriteMapper {

    /**
     * 保存用户信息
     *
     * @return int
     */
    int saveUserInfo(TestServiceEntity user);
}
