package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.model.order.suborder.SubOrder;

import java.util.List;

public interface SubOrderDAO {

    void addSubOrder(SubOrder subOrder);

    Integer getLastId();

    List<SubOrder> getSubOrdersByOrderId(Integer orderId);

    void update(SubOrder subOrder);
}
