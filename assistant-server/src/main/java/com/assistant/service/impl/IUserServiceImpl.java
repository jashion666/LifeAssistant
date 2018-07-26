package com.assistant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.assistant.service.IUserService;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/7/25.
 */
@Service
@org.springframework.stereotype.Service
public class IUserServiceImpl implements IUserService {

    @Override
    public String getUserInfo(int userId) {

        Map<Integer, String> userInfo = new HashMap<>(1);

        userInfo.put(1, "厨师长--大王");
        userInfo.put(2, "传菜员--小王");

        return userInfo.get(userId);
    }
}
