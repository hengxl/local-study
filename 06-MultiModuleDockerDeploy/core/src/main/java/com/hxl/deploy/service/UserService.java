package com.hxl.deploy.service;

import com.hxl.deploy.domain.entity.User;

/**
 * 普通用户服务
 *
 * @author hengxiaoliang
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByName(String username);

}
