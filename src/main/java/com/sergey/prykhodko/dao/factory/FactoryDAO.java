package com.sergey.prykhodko.dao.factory;

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
}
