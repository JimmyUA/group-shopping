package com.sergey.prykhodko.front.pages.user.orderList;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.pages.basepage.BasePage;
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

public class OrdersListPage extends BasePage {
    private List<Order> orders;

    public OrdersListPage() {

        orders = OrderService.getOrderService(FactoryType.SPRING).getActiveOrders();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        ListDataProvider<Order> listDataProvider = new ListDataProvider<>(orders);

        DataView<Order> dataView = new DataView<Order>("orderView", listDataProvider) {
            @Override
            protected void populateItem(Item<Order> item) {
                item.add(new Label("id", "Заказ # " + item.getModelObject().getOrderId()));
                item.add(new Label("shopName", item.getModelObject().getShopName()));
                item.add(new ExternalLink("shopLink", new Model<>(item.getModelObject().getShopName().getLink()),
                        new Model<>("Ссылка на магазин")){

                    @Override
                    protected void onComponentTag(ComponentTag tag) {
                        super.onComponentTag(tag);
                        tag.put("target", "_blank");
                    }
                });
                Link imageLink = new Link("imageLink") {

                    @Override
                    public void onClick() {
                        setResponsePage(new SubOrderAddingPage(item.getModelObject()));
                    }
                };
                ContextImage shopImage = new ContextImage("img", item.getModelObject().getShopName().getLogoPath());
                imageLink.add(shopImage);
                item.add(imageLink);
            }
        };

        add(dataView);
    }
}
