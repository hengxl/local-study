package com.hxl.deploy.tools;

import com.hxl.deploy.constant.AppConst;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 * TODO: 实现思路:
 *       Spring扫描到 AppCache 类上的 @Component 注解，将其注册为bean
 *       Spring检测到 setRedisTemplate 方法上的 @Resource 注解
 *       Spring查找容器中匹配的 RedisTemplate<String, Object> bean
 *       Spring自动调用 setRedisTemplate 方法，将找到的bean实例传入
 *       这种机制确保了在使用静态工具方法之前，redisTemplate 已经被正确初始化！
 *
 * @author hengxiaoliang
 */
@Component
public class AppCache {

    /**
     * 引入自定义的序列化器
     */
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存工具类初始化时，将RedisTemplate注入进来
     * TODO: 不能用 this.redisTemplate，因为this是实例对象的引用，而redisTemplate是类级别的静态变量
     *       在生命周期上，AppCache类级别的静态属性 早于 this实例对象
     */
    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        AppCache.redisTemplate = redisTemplate;
    }

    // ==================== 构建key ===================

    /**
     * 统一构建Redis Key（封装拼接逻辑，避免重复）
     */
    private static String buildKey(String cacheName, String key) {
        // 参数非空校验：提前抛出异常，避免无效Key
        Assert.hasText(cacheName, AppConst.CACHE_NAME_NOT_NULL);
        Assert.hasText(key, AppConst.CACHE_KEY_NOT_NULL);
        return String.join(AppConst.DOUBLE_COLON, cacheName, key);
    }

    // ==================== 基础写入 ====================

    /**
     * 存储缓存（无过期时间）
     */
    public static void put(String cacheName, String key, Object value) {
        redisTemplate.opsForValue().set(buildKey(cacheName, key), value);
    }

    /**
     * 存储缓存（有过期时间）
     */
    public static void put(String cacheName, String key, Object value, long expireSeconds) {
        redisTemplate.opsForValue().set(buildKey(cacheName, key), value, expireSeconds, TimeUnit.SECONDS);
    }

    // ==================== 基础读取 ====================

    /**
     * 获取缓存（泛型返回，避免强制类型转换）
     */
    public static <T> T get(String cacheName, String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(buildKey(cacheName, key));
        return Objects.isNull(value) ? null : clazz.cast(value);
    }

    /**
     * 获取缓存（无泛型，返回Object）
     */
    public static Object get(String cacheName, String key) {
        return redisTemplate.opsForValue().get(buildKey(cacheName, key));
    }

    // ==================== 基础删除 ====================

    /**
     * 删除单个缓存
     */
    public static void remove(String cacheName, String key) {
        redisTemplate.delete(buildKey(cacheName, key));
    }

    /**
     * 批量删除相关缓存
     */
    public static void removePattern(String cacheName) {
        redisTemplate.delete(redisTemplate.keys(buildKey(cacheName, AppConst.ASTERISK)));
    }

    // ==================== 辅助方法 ====================

    /**
     * 判断缓存是否存在
     */
    public static boolean exists(String cacheName, String key) {
        return redisTemplate.hasKey(buildKey(cacheName, key));
    }
}
