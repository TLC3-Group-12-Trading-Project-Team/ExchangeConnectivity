package com.example.ExchangeConnectivity.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.port}")
    int PORT;

    @Value("${spring.redis.host}")
    String URL;

    @Bean
    Jedis jedis(){
        Jedis jedis = new Jedis("172.25.0.2", 6379);
        return jedis;
    }
}