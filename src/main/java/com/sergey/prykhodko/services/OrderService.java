package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.OrderDAO;
import com.sergey.prykhodko.dao.factory.FactoryDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.order.Order;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService(FactoryType factoryType) {
        orderDAO = FactoryDAO.getFactory(FactoryType.SPRING).getOrderDAO();
    }

    public static OrderService getOrderService(FactoryType factoryType) {
        return new OrderService(factoryType);
    }

    public List<Order> getActiveOrders() {
        return orderDAO.getActiveOrders();
    }
}
