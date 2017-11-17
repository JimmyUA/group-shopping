package com.sergey.prykhodko.model.order;

import com.sergey.prykhodko.front.util.ShopName;
import com.sergey.prykhodko.model.order.suborder.SubOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{

    private Integer orderId;
    private boolean isOpened = true;
    private boolean isStarted = false;
    private LocalDate startDate;
    private ShopName shopName;
    private List<SubOrder> subOrders;
    private Integer sumOrder = 0;  // All money in 0.01 of grn


    public Order() {
        subOrders = new ArrayList<>();
    }

    public List<SubOrder> getSubOrders() {
        return subOrders;
    }


    public void setSubOrders(List<SubOrder> subOrders) {

        this.subOrders = subOrders;
        for (SubOrder suborder : subOrders
             ) {
            sumOrder += suborder.getSumSubOrder();
        }
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public Order(ShopName shopName) {
        this.shopName = shopName;
    }

    public Integer getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(Integer sumOrder) {
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

    public void addSubOrder(SubOrder subOrder) {
        subOrders.add(subOrder);
        sumOrder += subOrder.getSumSubOrder();
    }
}
