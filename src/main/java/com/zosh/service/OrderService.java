package com.zosh.service;


import com.zosh.domain.OrderType;
import com.zosh.modal.Coins;
import com.zosh.modal.Order;
import com.zosh.modal.OrderItem;
import com.zosh.modal.User;

import java.util.List;

public interface OrderService {



    Order createOrder(User user, OrderItem orderItem, OrderType orderType);
    Order getOrderById(Long orderId) throws Exception;

    List<Order> getAllOrderOfUser(Long userId,OrderType orderType,String assetSymbol);

    Order processOrder(Coins coin, double quantiy,OrderType orderType,User user) throws Exception;







}
