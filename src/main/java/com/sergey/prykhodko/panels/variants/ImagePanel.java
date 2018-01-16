package com.sergey.prykhodko.panels.variants;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.resource.ContextRelativeResource;

public class ImagePanel extends Panel {

    public ImagePanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Image("image", new ContextRelativeResource("drawable/sd.jpg")));
    }
}
