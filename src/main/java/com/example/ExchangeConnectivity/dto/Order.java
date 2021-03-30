package com.example.ExchangeConnectivity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable {
    private int id;
    private String product;
    private int quantity;
    private double price;
    private String side;
    private String action;
    private String exchange;
    @JsonIgnore
    private String orderId;

    public String getExchange() {
        return exchange;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getAction() {
        return action;
    }

    public double getPrice() {
        return price;
    }

    public String getProduct() {
        return product;
    }

    public String getSide() {
        return side;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}