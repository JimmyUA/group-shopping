package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.SubOrderDAO;
import com.sergey.prykhodko.dao.factory.FactoryDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.order.suborder.Link;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class SubOrderService {
    private SubOrderDAO subOrderDAO;

    private SubOrderService(FactoryType factoryType) {
        subOrderDAO = FactoryDAO.getFactory(factoryType).getSubOrderDAO();
    }

    public static SubOrderService getSubOrderService(FactoryType factoryType){
        return new SubOrderService(factoryType);
    }

    public void addSubOrder(SubOrder subOrder) {
        subOrderDAO.addSubOrder(subOrder);
        Integer id = subOrderDAO.getLastId();
        LinkService linkService = LinkService.getLinkService(FactoryType.SPRING);
        for (Link link: subOrder.getLinks()
             ) {
            link.setSubOrderId(id);
            linkService.saveLink(link);
        }
    }
}
