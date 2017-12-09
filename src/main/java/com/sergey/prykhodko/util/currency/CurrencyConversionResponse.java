package com.sergey.prykhodko.util.currency;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyConversionResponse implements Serializable{

    private String base;
    private String date;

    private Map<String, String> quotes = new TreeMap<String, String>();
    private LocalDate responseDate;

    public LocalDate getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDate responseDate) {
        if (responseDate != null){
            this.responseDate = responseDate;
        }
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, String> quotes) {
        this.quotes = quotes;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
