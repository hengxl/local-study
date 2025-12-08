package com.hxl.deploy.mapper;

import com.hxl.deploy.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 普通用户 Mapper
 *
 * @author hengxiaoliang
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User queryUserByName(String username);

}
