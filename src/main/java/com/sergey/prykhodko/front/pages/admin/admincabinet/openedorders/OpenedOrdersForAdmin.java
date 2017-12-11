package com.sergey.prykhodko.front.pages.admin.admincabinet.openedorders;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.pages.admin.admincabinet.order.OrderPageForAdmin;
import com.sergey.prykhodko.front.pages.basepage.adminbasepage.AdminBasePage;
import com.sergey.prykhodko.front.pages.user.suborder.SubOrderAddingPage;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.services.OrderService;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import java.util.List;

public class OpenedOrdersForAdmin extends AdminBasePage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<Order> orders = getOpenedOrders();
        ListDataProvider<Order> listDataProvider = new ListDataProvider<>(orders);

        DataView<Order> dataView = getDataView(listDataProvider);

        add(dataView);
    }

    public List<Order> getOpenedOrders() {
        OrderService orderService = OrderService.getOrderService(FactoryType.SPRING);
        return orderService.getActiveOrders();
    }

    private DataView<Order> getDataView(ListDataProvider<Order> listDataProvider) {
        return new DataView<Order>("orderView", listDataProvider) {
            @Override
            protected void populateItem(Item<Order> item) {
                item.add(new Label("id", "Заказ # " + item.getModelObject().getOrderId()));
                item.add(new Label("shopName", item.getModelObject().getShopName()));
                item.add(new ExternalLink("shopLink", new Model<>(item.getModelObject().getShopName().getLink()),
                        new Model<>("Ссылка на магазин")) {

                    @Override
                    protected void onComponentTag(ComponentTag tag) {
                        super.onComponentTag(tag);
                        tag.put("target", "_blank");
                    }
                });
                Link imageLink = new Link("imageLink") {

                    @Override
                    public void onClick() {
                        setResponsePage(new OrderPageForAdmin(item.getModelObject()));
                    }
                };
                ContextImage shopImage = new ContextImage("img", item.getModelObject().getShopName().getLogoPath());
                imageLink.add(shopImage);
                item.add(imageLink);
            }
        };
    }
}
