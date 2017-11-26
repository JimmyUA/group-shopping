package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.ShopDAO;
import com.sergey.prykhodko.dao.factory.FactoryDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;

import java.util.List;

public class ShopService {
    private ShopDAO shopDAO;

    private ShopService(FactoryType factoryType) {
        shopDAO = FactoryDAO.getFactory(factoryType).getShopDAO();
    }

    public static ShopService getShopService(FactoryType factoryType){
        return new ShopService(factoryType);
    }

    public List<String> getAllShopsNames() {
        return shopDAO.getAllShopsNames();
    }
}
