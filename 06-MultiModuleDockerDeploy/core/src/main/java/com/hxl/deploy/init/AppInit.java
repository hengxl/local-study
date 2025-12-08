package com.hxl.deploy.init;

import com.hxl.deploy.constant.AppConst;
import com.hxl.deploy.domain.entity.User;
import com.hxl.deploy.mapper.SystemUserMapper;
import com.hxl.deploy.tools.AppCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 应用启动时执行初始化操作
 *
 * @author hengxiaoliang
 */
@Component
@Slf4j
public class AppInit implements ApplicationRunner {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public void run(ApplicationArguments args) {
        log.info("开始初始化Redis缓存...");
        try {
            // 1.清空Redis缓存
            clearSystemUserRedisCache();

            // 2.查询所有系统用户
            List<User> systemUserList = systemUserMapper.queryAll();

            // 3.批量写入Redis缓存
            systemUserList.forEach(systemUser ->
                    AppCache.put(AppConst.SYSTEM_USER_PREFIX, systemUser.getUsername(), systemUser));

            log.info("Redis缓存初始化完成...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 清空Redis缓存
     */
    private void clearSystemUserRedisCache() {
        // 获取系统用户的统配符key
        String pattern = String.join(AppConst.SYSTEM_USER_PREFIX, AppConst.DOUBLE_COLON, AppConst.ASTERISK);
        // 一定不为null，查不到则返回空集合
        Set<String> keys = redisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
        log.info("Redis缓存已清空...");
    }
}
