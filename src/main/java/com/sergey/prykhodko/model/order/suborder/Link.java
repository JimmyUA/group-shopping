package com.sergey.prykhodko.model.order.suborder;

import java.io.Serializable;


public class Link implements Serializable{
    private Integer id = 0;
    private String linkString;
    private Integer itemAmount;
    private Integer subOrderId;
    private Integer itemPrice; // all in 0.01 grn

    public Link() {
    }

    public String getLinkString() {
        return linkString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLinkString(String linkString) {
        this.linkString = linkString;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Integer getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!id.equals(link.id)) return false;
        if (!linkString.equals(link.linkString)) return false;
        if (!itemAmount.equals(link.itemAmount)) return false;
        if (!subOrderId.equals(link.subOrderId)) return false;
        return itemPrice.equals(link.itemPrice);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + linkString.hashCode();
        result = 31 * result + itemAmount.hashCode();
        result = 31 * result + subOrderId.hashCode();
        result = 31 * result + itemPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkString='" + linkString + '\'' +
                ", itemAmount=" + itemAmount +
                ", subOrderId=" + subOrderId +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
