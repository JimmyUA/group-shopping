package com.sergey.prykhodko.dao.factory;

import com.sergey.prykhodko.dao.LinkDAO;
import com.sergey.prykhodko.dao.OrderDAO;
import com.sergey.prykhodko.dao.SubOrderDAO;
import com.sergey.prykhodko.dao.UserDAO;

public interface FactoryDAO {
    static FactoryDAO getFactory(FactoryType type) {
        switch (type) {
            case SPRING:
                return new SpringJDBCFactory();
            default:
                return new SpringJDBCFactory();
        }
    }

    UserDAO getUserDAO();

    LinkDAO getLinkDAO();

    OrderDAO getOrderDAO();

    SubOrderDAO getSubOrderDAO();


}
