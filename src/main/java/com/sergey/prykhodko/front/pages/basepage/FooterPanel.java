package com.sergey.prykhodko.front.pages.basepage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class FooterPanel extends Panel {
    public FooterPanel(String id) {
        super(id);
        add(new Label("label", "All rights reserved"));
    }
}

