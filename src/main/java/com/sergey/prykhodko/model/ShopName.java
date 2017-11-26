package com.sergey.prykhodko.model;

public enum ShopName {
    SPORT_DIRECT(1, "Sport Direct", "http://sportsdirect.com", "/drawable/sd.jpg");

    ShopName(Integer id, String name, String link, String logoPath) {
        this.name = name;
        this.link = link;
        this.id = id;
        this.logoPath = logoPath;
    }

    private Integer id;
    private  String name;
    private  String link;
    private final String logoPath;

    public Integer getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
