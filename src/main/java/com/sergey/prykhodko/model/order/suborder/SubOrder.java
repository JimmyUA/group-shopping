package com.sergey.prykhodko.model.order.suborder;

import com.sergey.prykhodko.model.user.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SubOrder {
    private Integer id;
    private User owner;
    private List<Link> links;
    private boolean isPaid;
    private BigInteger sumSubOrder;  // All money in 0.01 of grn

    public SubOrder() {
        links = new ArrayList<>();
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public BigInteger getSumSubOrder() {
        return sumSubOrder;
    }

    public void setSumSubOrder(BigInteger sumSubOrder) {
        this.sumSubOrder = sumSubOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubOrder subOrder = (SubOrder) o;

        if (isPaid != subOrder.isPaid) return false;
        if (id != null ? !id.equals(subOrder.id) : subOrder.id != null) return false;
        if (!owner.equals(subOrder.owner)) return false;
        if (!links.equals(subOrder.links)) return false;
        return sumSubOrder.equals(subOrder.sumSubOrder);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + owner.hashCode();
        result = 31 * result + links.hashCode();
        result = 31 * result + (isPaid ? 1 : 0);
        result = 31 * result + sumSubOrder.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SubOrder{" +
                "id=" + id +
                ", owner=" + owner +
                ", links=" + links +
                ", isPaid=" + isPaid +
                ", sumSubOrder=" + sumSubOrder +
                '}';
    }

    public void addLink(Link link) {
        links.add(link);
    }
}
