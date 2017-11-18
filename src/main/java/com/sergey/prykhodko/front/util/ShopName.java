package com.sergey.prykhodko.front.util;

public enum ShopName {
    SPORT_DIRECT("Sport Direct", "http://sportsdirect.com");

    ShopName(String value, String link) {
        this.value = value;
        this.link = link;
    }

    private  String value;
    private  String link;

    public String getValue() {
        return value;
    }

    public String getLink() {
        return link;
    }
}
