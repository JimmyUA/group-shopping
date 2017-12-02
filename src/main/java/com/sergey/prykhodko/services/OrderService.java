package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.OrderDAO;
import com.sergey.prykhodko.dao.factory.FactoryDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.suborder.Link;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import com.sun.org.apache.xpath.internal.operations.Or;

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
        List<Order> activeOrders = orderDAO.getActiveOrders();
        SubOrderService subOrderService = SubOrderService.getSubOrderService(FactoryType.SPRING);
        LinkService linkService = LinkService.getLinkService(FactoryType.SPRING);
        for (Order order : activeOrders
             ) {
            List<SubOrder> subOrders = subOrderService.getSubOrdersByOrderId(order.getOrderId());
            for (SubOrder subOrder : subOrders
                 ) {
                List<Link> links = linkService.getLinksBySubOrderId(subOrder.getId());
            }

            order.setSubOrders(subOrders);
        }
        return activeOrders;
    }

    public void updateOrder(Order order) {
        orderDAO.update(order);
    }


    public Order getActiveOrdersByShop(Integer id) {
        return orderDAO.getActiveOrderByID(id);
    }

    public void add(Order order) {
        orderDAO.add(order); // TODO do in one transaction
        Integer id = orderDAO.getLastId();
        order.setOrderId(id);
    }
}
