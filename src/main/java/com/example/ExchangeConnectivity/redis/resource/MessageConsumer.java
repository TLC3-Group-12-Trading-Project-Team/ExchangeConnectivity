package com.example.ExchangeConnectivity.redis.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

@Component
public class MessageConsumer {

    @Autowired
    Jedis jedis;
    private static final int TIMEOUT = 30000;

    @Value("${spring.redis.queue.name}")
    String QUEUE_NAME;

    @EventListener(ApplicationReadyEvent.class)
    public void listenForMessages() {
        System.out.println("listening for messages from " + QUEUE_NAME);

        while (true) {
            List<String> messages = jedis.blpop(TIMEOUT, QUEUE_NAME);
            System.out.println(messages.get(1));
            messages.clear();
        }
    }
}
