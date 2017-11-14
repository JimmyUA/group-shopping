package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.model.order.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getActiveOrders();
}

