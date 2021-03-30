package com.example.ExchangeConnectivity.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.port}")
    int PORT;

    @Value("${spring.redis.host}")
    String host;

    @Bean
    Jedis jedis(){
        Jedis jedis = new Jedis("localhost", 6379);
        return jedis;
    }

}