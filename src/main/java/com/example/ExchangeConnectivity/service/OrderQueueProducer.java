package com.example.ExchangeConnectivity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class OrderQueueProducer {
//    private static final Logger LOGGER = LoggerFactory.getLogger(OrderQueueProducer.class);
//
//    @Value("${spring.redis.queue.trade-engine.name}")
//    private String tradeEngineServiceQueue;
//
//    @Autowired
//    Jedis jedis;
//
//    public void sendDataToRedisQueue(String input) {
//        jedis.rpush(tradeEngineServiceQueue,input);
//        LOGGER.info("Data - " + input + " sent through Redis Topic - " );
//
//    }
}
