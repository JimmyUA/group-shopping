package com.sergey.prykhodko.dao.factory;

import com.sergey.prykhodko.dao.UserDAO;
import com.sergey.prykhodko.dao.implementations.UserMySQLDAO;

public class SpringJDBCFactory implements FactoryDAO {
    @Override
    public UserDAO getUserDAO() {
        return new UserMySQLDAO();
    }
}
