package com.example.springbootredis.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.time.Duration;


@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {
    @Autowired
    private  Environment environment;

    
    //@Value("${spring.redis.port}")
    private final int REDIS_PORT=Integer.parseInt(environment.getProperty("spring.redis.port"));



    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration lettuce=LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(5))
                .shutdownTimeout(Duration.ZERO)
                .build();
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", REDIS_PORT), lettuce);
    }

    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory factory) {
        Duration expiration=Duration.ofSeconds(25255);
        RedisCacheManager.RedisCacheManagerBuilder builder=RedisCacheManager.builder(factory).cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration));
        return builder.build();
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }

}
