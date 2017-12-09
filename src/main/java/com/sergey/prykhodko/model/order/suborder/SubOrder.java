package com.sergey.prykhodko.model.order.suborder;

import com.sergey.prykhodko.model.user.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SubOrder implements Serializable {
    private Integer id;
    private Integer orderId;
    private Integer ownerId;
    private List<Link> links;
    private boolean isPaid = false;
    private Integer sumSubOrder = 0;  // All money in 0.01 of grn

    public SubOrder() {
        links = new ArrayList<>();
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {

        this.links = links;
    }

    public Integer getSumSubOrder() {
        return sumSubOrder;
    }

    public void setSumSubOrder(Integer sumSubOrder) {
        this.sumSubOrder = sumSubOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubOrder subOrder = (SubOrder) o;

        if (isPaid != subOrder.isPaid) return false;
        if (!id.equals(subOrder.id)) return false;
        if (!orderId.equals(subOrder.orderId)) return false;
        if (!ownerId.equals(subOrder.ownerId)) return false;
        if (!links.equals(subOrder.links)) return false;
        return sumSubOrder.equals(subOrder.sumSubOrder);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + ownerId.hashCode();
        result = 31 * result + links.hashCode();
        result = 31 * result + (isPaid ? 1 : 0);
        result = 31 * result + sumSubOrder.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SubOrder{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", ownerId=" + ownerId +
                ", links=" + links +
                ", isPaid=" + isPaid +
                ", sumSubOrder=" + sumSubOrder +
                '}';
    }

    public void addLink(Link link) {
        links.add(link);
        sumSubOrder += link.getItemPrice() * link.getItemAmount();
    }
}
