package com.sergey.prykhodko.front.pages.basepage;

import com.sergey.prykhodko.front.pages.user.orderList.OrdersListPage;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.scheduler.OrderScheduler;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.Arrays;

public class MenuPanel extends Panel {
    private static final String HOME_PAGE_LINK_ID = "toHomePage";
    private static final String ORDERS_LIST_PAGE_LINK_ID = "toOrdersList";
    private static final String LOG_OUT_LINK_ID = "logOut";

    public MenuPanel(String id) {
        super(id);
        addComponentsToForm();
    }

    private void addComponentsToForm() {
        addHomePageLink();

        addUsersListLink();

        addLogOutLink();
    }

    private void addLogOutLink() {
        add(new Link(LOG_OUT_LINK_ID) {
            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }

        });
    }

    private void addUsersListLink() {
        add(new Link(ORDERS_LIST_PAGE_LINK_ID) {
            @Override
            public void onClick() {
//                OrderScheduler scheduler = (OrderScheduler) getApplication().getSessionStore().getAttribute(null, "schrduler");
//                Order order = scheduler.getOrder();
//                setResponsePage(new OrdersListPage(Arrays.asList(order)));

            }
        });
    }

    private void addHomePageLink() {
        add(new Link(HOME_PAGE_LINK_ID) {
            @Override
            public void onClick() {
                setResponsePage(getApplication().getHomePage());
            }
        });
    }
}
