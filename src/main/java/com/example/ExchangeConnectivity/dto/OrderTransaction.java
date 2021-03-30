package com.example.ExchangeConnectivity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

//import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderTransaction implements Serializable {

    private String status;

    private String exchange;

    private double price;

    private Order orders;

    private String exchangeOrderId;

    private int orderId;


    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getExchangeOrderId() {
        return exchangeOrderId;
    }

    public String getExchange() {
        return exchange;
    }

    public Order getOrders() {
        return orders;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setExchangeOrderId(String exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrders(Order orders) {

    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
