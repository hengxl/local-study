package com.hxl.deploy.controller;

import com.hxl.deploy.constant.AppConst;
import com.hxl.deploy.domain.entity.User;
import com.hxl.deploy.service.UserService;
import com.hxl.deploy.tools.AppCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 系统用户前端控制器
 *
 * @author hengxiaoliang
 */
@RestController
@Slf4j
public class SystemUserController {

    @Resource
    private UserService userService;

    /**
     * 1.先从缓存里获取，如果获取到，则表明是系统用户
     * 2.否则从数据库里查询，表示是普通用户
     * 3.如果数据库也没有，则表示是非法用户，抛出异常
     */
    @GetMapping("/user")
    public User getSystemUser(@RequestParam(value = "name", required = true) String username) {
        User user = AppCache.get(AppConst.SYSTEM_USER_PREFIX, username, User.class);
        if (Objects.isNull(user)) {
            log.info("用户缓存: {}未命中，正在查询数据库...", username);
            user = userService.getUserByName(username);
            Assert.isTrue(Objects.nonNull(user), AppConst.USER_NOT_EXISTS);
            AppCache.put(AppConst.SYSTEM_USER_PREFIX, username, user);
            log.info("用户缓存: {} 已写入...", username);
        }
        return user;
    }
}
