package com.sergey.prykhodko.model.order.suborder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Link implements Serializable{
    private Integer id;
    private String linkString;
    private int itemAmount;
    private BigInteger itemPrice; // all in 0.01 grn

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

    public BigInteger getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigInteger itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (itemAmount != link.itemAmount) return false;
        if (!linkString.equals(link.linkString)) return false;
        return itemPrice.equals(link.itemPrice);
    }

    @Override
    public int hashCode() {
        int result = linkString.hashCode();
        result = 31 * result + itemAmount;
        result = 31 * result + itemPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "linkString='" + linkString + '\'' +
                ", itemAmount=" + itemAmount +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
