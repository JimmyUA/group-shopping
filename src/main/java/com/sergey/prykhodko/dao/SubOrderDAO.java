package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.model.order.suborder.SubOrder;

public interface SubOrderDAO {

    void addSubOrder(SubOrder subOrder);

    Integer getLastId();
}
