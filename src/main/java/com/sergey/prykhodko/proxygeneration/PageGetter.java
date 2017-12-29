package com.sergey.prykhodko.proxygeneration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageGetter {

    public Document getPage(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        return doc;
    }

    public String getReorgionisedPage(String URL){

        Document doc = null;
        try {
            doc = getPage(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Element> scripts = doc.getAllElements();

        List<Element> filteredSrcList = scripts.stream().filter(s -> s.hasAttr("src")).collect(Collectors.toList());

        List<Element> filteredHrefList = scripts.stream().filter(s -> s.hasAttr("href")).collect(Collectors.toList());

        List<Element> filteredAll = new ArrayList<>();

        Stream.of(filteredHrefList, filteredSrcList).forEach(filteredAll::addAll);

        filteredAll.forEach(s -> s.attr("href", "https://ua.sportsdirect.com" + s.attr("href")));
        filteredAll.forEach(s -> s.attr("src", "https://ua.sportsdirect.com" + s.attr("src")));

        Element first = filteredAll.get(0);

        first.tagName();

        first.attr("href", "https://ua.sportsdirect.com" + first.attr("href"));
        return doc.toString();
    }


}
