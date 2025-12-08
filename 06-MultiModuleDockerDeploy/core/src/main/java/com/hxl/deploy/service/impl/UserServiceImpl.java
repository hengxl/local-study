package com.hxl.deploy.service.impl;

import com.hxl.deploy.domain.entity.User;
import com.hxl.deploy.mapper.UserMapper;
import com.hxl.deploy.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 普通用户服务实现类
 *
 * @author hengxiaoliang
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
