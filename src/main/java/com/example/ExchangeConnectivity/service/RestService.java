package com.example.ExchangeConnectivity.service;

import com.example.ExchangeConnectivity.dto.Order;
import com.example.ExchangeConnectivity.dto.OrderTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;
//    private final RestTemplate restTemplate;

    @Autowired
    private OrderQueueProducer orderQueueProducer;

    @Value("${api.key}")
    private String apiKey;

    private OrderTransaction orderTransaction = new OrderTransaction();
    ObjectMapper objectMapper = new ObjectMapper();
//    public RestService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
    HttpHeaders headers = new HttpHeaders();
    private static Logger logger = LoggerFactory.getLogger(RestService.class);

    public void createOrder( Order order){
        System.out.println("okay, we want to create an order");
        String uri = order.getExchange()+ "/"+ apiKey+"/order";
        HttpEntity<Order> request = new HttpEntity<>(order);
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", order.getOrderId());
        logger.info("api Key: "+ apiKey);
        logger.info("uri: "+ uri);
//        params.put("apiKey",apiKey);
//        logger.info("request: "+ request);
        ResponseEntity<String> orderResponse = this.restTemplate.postForEntity(uri, request, String.class);
        try {
            JsonNode exchangeOrderId = objectMapper.readTree(orderResponse.getBody());
            logger.info("root: "+String.valueOf(exchangeOrderId));
//            order.setOrderId(String.valueOf(exchangeOrderId));
            //send order to queue
            orderTransaction.setExchangeOrderId(String.valueOf(exchangeOrderId).replace("\"",""));
            orderTransaction.setExchange(order.getExchange());
            orderTransaction.setOrders(order);
            orderTransaction.setPrice(order.getPrice());
            orderTransaction.setStatus("active");
            orderTransaction.setOrderId(order.getId());
            HttpEntity<OrderTransaction> transactionRequest = new HttpEntity<>(orderTransaction);
            String exchangeUri = "http://localhost:23000/orders/"+order.getId() +"/transactions";
            logger.info(exchangeUri);
            ResponseEntity<String> orderTransactionResponse = this.restTemplate.
                                                                                postForEntity(exchangeUri,
                                                                                              transactionRequest,
                                                                                              String.class
                                                                                            );
            JsonNode response = objectMapper.readTree(orderTransactionResponse.getBody());
            logger.info("response: "+ response);
//            orderQueueProducer.sendDataToRedisQueue(String.valueOf(orderTransaction));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    public void  deleteOrder(Order order){
        String uri = order.getExchange() +"/{apiKey}/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", order.getOrderId());
        params.put("apiKey",apiKey);
        restTemplate.delete(uri,params);
        //update database

    }
}