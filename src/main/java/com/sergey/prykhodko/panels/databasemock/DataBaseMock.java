package com.sergey.prykhodko.panels.databasemock;

import java.util.HashMap;
import java.util.Map;

public class DataBaseMock {

    private static Map<Integer, String > storage;

    static {
        storage = new HashMap<>();
        storage.put(1, "TextPanel");
        storage.put(3, "FormPanel");
        storage.put(2, "ImagePanel");
    }

    public static String getPanelName(Integer position){
        return storage.get(position);
    }
}
