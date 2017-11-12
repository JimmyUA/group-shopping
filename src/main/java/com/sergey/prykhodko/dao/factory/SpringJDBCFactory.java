package com.sergey.prykhodko.dao.factory;

import com.sergey.prykhodko.dao.LinkDAO;
import com.sergey.prykhodko.dao.OrderDAO;
import com.sergey.prykhodko.dao.SubOrderDAO;
import com.sergey.prykhodko.dao.UserDAO;
import com.sergey.prykhodko.dao.implementations.LinkMySQLDAO;
import com.sergey.prykhodko.dao.implementations.OrderMySQLDAO;
import com.sergey.prykhodko.dao.implementations.SubOrderMySQLDAO;
import com.sergey.prykhodko.dao.implementations.UserMySQLDAO;

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
}
