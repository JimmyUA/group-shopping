package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.LinkDAO;
import com.sergey.prykhodko.dao.factory.FactoryDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.order.suborder.Link;

import java.util.List;

public class LinkService {
    private LinkDAO linkDAO;

    private LinkService(FactoryType factoryType) {
        linkDAO = FactoryDAO.getFactory(factoryType).getLinkDAO();
    }

    public static LinkService getLinkService(FactoryType factoryType){
        return new LinkService(factoryType);
    }


    public void saveLink(Link link) {
        linkDAO.add(link);
    }

    public List<Link> getLinksBySubOrderId(Integer subOrderId) {
        return linkDAO.getLinksBySubOrderId(subOrderId);
    }
}
