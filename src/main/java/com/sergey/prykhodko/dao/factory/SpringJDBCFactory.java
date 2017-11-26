package com.sergey.prykhodko.dao.factory;

import com.sergey.prykhodko.dao.*;
import com.sergey.prykhodko.dao.implementations.*;

public class SpringJDBCFactory implements FactoryDAO {
    @Override
    public UserDAO getUserDAO() {
        return new UserMySQLDAO();
    }

    @Override
    public LinkDAO getLinkDAO() {
        return new LinkMySQLDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OrderMySQLDAO();
    }

    @Override
    public SubOrderDAO getSubOrderDAO() {
        return new SubOrderMySQLDAO();
    }

    @Override
    public ShopDAO getShopDAO() {
        return new ShopMySQLDAO();
    }
}
