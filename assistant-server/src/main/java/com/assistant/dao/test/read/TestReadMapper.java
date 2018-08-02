package com.assistant.dao.test.read;

import com.assistant.entity.test.TestServiceEntity;
import org.springframework.stereotype.Repository;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/8/2.
 */
public interface TestReadMapper {

    TestServiceEntity findUserInfo();
}
