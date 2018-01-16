package com.sergey.prykhodko.panels.variants;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class TextPanel extends Panel {

    public TextPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("label", "Some text"));
    }
}
