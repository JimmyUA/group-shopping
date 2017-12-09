package com.sergey.prykhodko.model.order;

import com.sergey.prykhodko.model.ShopName;
import com.sergey.prykhodko.model.order.suborder.SubOrder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{

    private Integer orderId = 0;
    private boolean isOpened = true;
    private boolean isStarted = false;
    private LocalDate startDate;
    private ShopName shopName;
    private List<SubOrder> subOrders;
    private boolean isPaid = false;
    private Integer sumOrder = 0;  // All money in 0.01 of grn


    public Order() {
        subOrders = new ArrayList<>();
    }

    public void deleteSubOrder(SubOrder subOrder){
        for (SubOrder subOrderInOrder : subOrders
             ) {
            if (subOrderInOrder.getId().equals(subOrder.getId())){
                subOrders.remove(subOrder);
                sumOrder -= subOrder.getSumSubOrder();
            }
        }
    }

    public List<SubOrder> getSubOrders() {
        return subOrders;
    }


    public void setSubOrders(List<SubOrder> subOrders) {

        this.subOrders = subOrders;
    }

    public boolean checkIfPaid(){
        for (SubOrder subOrder : subOrders
             ) {
            if (!subOrder.isPaid()){
                return false;
            }
        }
        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (isOpened != order.isOpened) return false;
        if (isStarted != order.isStarted) return false;
        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        if (startDate != null ? !startDate.equals(order.startDate) : order.startDate != null) return false;
        if (shopName != order.shopName) return false;
        if (subOrders != null ? !subOrders.equals(order.subOrders) : order.subOrders != null) return false;
        return sumOrder != null ? sumOrder.equals(order.sumOrder) : order.sumOrder == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (isOpened ? 1 : 0);
        result = 31 * result + (isStarted ? 1 : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (shopName != null ? shopName.hashCode() : 0);
        result = 31 * result + (subOrders != null ? subOrders.hashCode() : 0);
        result = 31 * result + (sumOrder != null ? sumOrder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", isOpened=" + isOpened +
                ", isStarted=" + isStarted +
                ", startDate=" + startDate +
                ", shopName=" + shopName +
                ", subOrders=" + subOrders +
                ", sumOrder=" + sumOrder +
                '}';
    }
}
