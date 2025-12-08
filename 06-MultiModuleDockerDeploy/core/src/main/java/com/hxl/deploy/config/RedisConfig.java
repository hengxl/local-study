package com.hxl.deploy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 序列化配置: Key用字符串序列化，Value用JSON序列化（解决默认JDK序列化问题）
 *
 * @author hengxiaoliang
 */
@Configuration
public class RedisConfig {

    /**
     * 自定义RedisTemplate，替换默认序列化方式
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 1. 设置Redis连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);

        // 2. 初始化JSON序列化器（GenericJackson2JsonRedisSerializer：自动处理对象类型，无需手动指定Class）
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
        // 3. 字符串序列化器（Key/HashKey用）
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        // 4. 配置序列化规则
        // Key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        // Value序列化
        redisTemplate.setValueSerializer(jsonSerializer);
        // HashKey序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // HashValue序列化
        redisTemplate.setHashValueSerializer(jsonSerializer);

        // 5. 初始化RedisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
