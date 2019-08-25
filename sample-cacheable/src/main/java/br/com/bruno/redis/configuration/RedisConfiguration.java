package br.com.bruno.redis.configuration;

import br.com.bruno.redis.order.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Order.class);
        jsonRedisSerializer.setObjectMapper(objectMapper());

        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jsonRedisSerializer);

        return redisTemplate;
    }

    @Bean("redisCacheManager")
    public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        Map<String, Long> expires = new HashMap<>();
        expires.put("ORDERS", 10L);
        expires.put("NEW_ORDERS", 1L);

        cacheManager.setCacheNames(expires.keySet());
        cacheManager.setExpires(expires);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
