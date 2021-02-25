package com.example.newspape.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    private int id;
    private String cart_name;
    private String press;
    private String date;
    private Double price;
    private Double amount;
    private int num;
    private String url;
    private String username;

    public Cart(String cart_name, String press, String date, Double price, Double amount, int num, String url, String username) {
        this.cart_name = cart_name;
        this.press = press;
        this.date = date;
        this.price = price;
        this.amount = amount;
        this.num = num;
        this.url = url;
        this.username = username;
    }
}
