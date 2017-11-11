package com.sergey.prykhodko.model.order;

import com.sergey.prykhodko.front.util.ShopName;
import com.sergey.prykhodko.model.order.suborder.SubOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order implements Serializable{

    private boolean isOpened = true;
    private boolean isStarted = false;
    private LocalDate startDate;
    private ShopName shopName;
    private Integer orderId;
    private List<SubOrder> subOrders;
    private BigDecimal sumOrder;  // All money in 0.01 of grn


    public Order() {
    }

    public Order(ShopName shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(BigDecimal sumOrder) {
        this.sumOrder = sumOrder;
    }

    public ShopName getShopName() {
        return shopName;
    }

    public void setShopName(ShopName shopName) {
        this.shopName = shopName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void start(LocalDate current) {
        isStarted = true;
        startDate = current;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void stop() {
        isStarted = false;
        isOpened = false;
    }
}
