package com.sergey.prykhodko.dao;

import com.sergey.prykhodko.model.order.suborder.Link;

import java.util.List;

public interface LinkDAO {
    void add(Link link);

    List<Link> getLinksBySubOrderId(Integer subOrderId);
}
