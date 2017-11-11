package com.sergey.prykhodko.model.order.suborder;

import com.sergey.prykhodko.model.user.User;

import java.math.BigDecimal;
import java.util.List;

public class SubOrder {
    private User owner;
    private List<Link> links;
    private BigDecimal sumSubOrder;  // All money in 0.01 of grn
}
