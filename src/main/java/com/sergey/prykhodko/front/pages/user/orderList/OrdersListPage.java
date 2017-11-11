package com.sergey.prykhodko.front.pages.user.orderList;

import com.sergey.prykhodko.front.pages.basepage.BasePage;
import com.sergey.prykhodko.front.pages.user.suborder.SubOrderAddingPage;
import com.sergey.prykhodko.model.order.Order;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.util.List;

public class OrdersListPage extends BasePage {
    private List<Order> orders;

    public OrdersListPage(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        ListDataProvider<Order> listDataProvider = new ListDataProvider<>(orders);

        DataView<Order> dataView = new DataView<Order>("orderView", listDataProvider) {
            @Override
            protected void populateItem(Item<Order> item) {
                add(new Label("id", item.getModelObject().getOrderId()));
                add(new Label("shopName", item.getModelObject().getOrderId()));
                add(new ExternalLink("shopLink", item.getModelObject().getShopName().getLink()));
                add(new Link<Void>("imageLink") {
                    @Override
                    public void onClick() {
                        setResponsePage(new SubOrderAddingPage( item.getModelObject()));
                    }
                });
            }
        };
    }
}
