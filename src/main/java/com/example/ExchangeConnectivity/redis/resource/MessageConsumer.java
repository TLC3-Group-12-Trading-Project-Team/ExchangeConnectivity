package com.example.ExchangeConnectivity.redis.resource;

import com.example.ExchangeConnectivity.dto.Order;
import com.example.ExchangeConnectivity.service.RestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageConsumer {

    @Autowired
    Jedis jedis;

    @Autowired
    RestService restService;

    private static final int TIMEOUT = 30000;

    private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);


    @Value("${spring.redis.queue.name}")
    String QUEUE_NAME;
    ObjectMapper objectMapper = new ObjectMapper();

    @EventListener(ApplicationReadyEvent.class)
    public void listenForMessages() {
        System.out.println("listening for messages from " + QUEUE_NAME);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        while (true) {
            List<String> messages = jedis.blpop(TIMEOUT, QUEUE_NAME);
            System.out.println(messages.get(1));
            String orderJsonString = messages.get(1);
            logger.info("===========json order: "+orderJsonString);
            try {
                Order order = objectMapper.readValue(orderJsonString, Order.class);
                String action = order.getAction();
                System.out.println(order.getAction());
                System.out.println(action.equals("create"));
                if (order.getAction().equals("create")) {
                    restService.createOrder(order);
                }
                else if (order.getAction().equals("cancel"))
                    restService.deleteOrder(order);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            logger.info("this line is printed");
            messages.clear();
        }
    }
}
