package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.ShopDAO;
import com.sergey.prykhodko.dao.factory.FactoryDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.ShopName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopService {
    private ShopDAO shopDAO;

    private ShopService(FactoryType factoryType) {
        shopDAO = FactoryDAO.getFactory(factoryType).getShopDAO();
    }

    public static ShopService getShopService(FactoryType factoryType){
        return new ShopService(factoryType);
    }

    protected void setShopDAO(ShopDAO shopDAO){
        this.shopDAO = shopDAO;
    }
    public List<ShopName> getAllShops() {
        List<String> shopNames = shopDAO.getAllShopsNames();
        List<ShopName> shops = Arrays.asList(ShopName.values());

        List<ShopName> result = new ArrayList<>();

        for (String name : shopNames
             ) {
            for (ShopName shop: shops
                 ) {
                if (name.equals(shop.getName())){
                    result.add(shop);
                    break;
                }
            }
        }
        return result;
    }
}
