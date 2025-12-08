package com.hxl.deploy.mapper;

import com.hxl.deploy.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户 Mapper
 *
 * @author hengxiaoliang
 */
@Mapper
public interface SystemUserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User queryUserByName(@Param("username") String username);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<User> queryAll();

}
