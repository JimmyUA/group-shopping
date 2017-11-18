package com.sergey.prykhodko.front.util;

public enum ShopName {
    SPORT_DIRECT("Sport Direct", "http://sportsdirect.com");

    ShopName(String value, String link) {
        this.value = value;
        this.link = link;
    }

    private  String value;
    private  String link;
    private final String logoPath = "/drawable/sd.jpg";

    public String getLogoPath() {
        return logoPath;
    }

    public String getValue() {
        return value;
    }

    public String getLink() {
        return link;
    }
}
