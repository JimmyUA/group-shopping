package com.sergey.prykhodko.front.pages.user.suborder;

import com.sergey.prykhodko.front.pages.basepage.BasePage;
import com.sergey.prykhodko.model.order.Order;
import org.apache.wicket.markup.html.basic.Label;

public class SubOrderAddingPage extends BasePage {
    private Order order;


    public SubOrderAddingPage(Order order) {
        this.order = order;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        String orderLabelMessage = "Заказ # " + order.getOrderId() + " " + order.getShopName().getName();
        String currentLabelMessage = "текущая сумма: " + order.getSumOrder() + " гривен";
        add(new Label("orderLabel", orderLabelMessage));
        add(new Label("currentSumLabel", currentLabelMessage));
        add(new SubOrderAddingPanel("subOrderAdding", order));
    }
}
