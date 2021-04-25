package com.lx.service;

import com.lx.pojo.Cart;

public interface OrderService {

    public String createOrder(Cart cart,Integer userId);
}
