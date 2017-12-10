package com.sergey.prykhodko.front.pages.basepage.adminbasepage;

import com.sergey.prykhodko.front.pages.admin.admincabinet.OpenedOrdersForAdmin;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class AdminNavigationPanel extends Panel {

    public AdminNavigationPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addOpenedOrdersLink();
    }

    private void addOpenedOrdersLink() {
        add(new Link<Void>("openedOrders") {
            @Override
            public void onClick() {
                setResponsePage(OpenedOrdersForAdmin.class);
            }
        });
    }
}
