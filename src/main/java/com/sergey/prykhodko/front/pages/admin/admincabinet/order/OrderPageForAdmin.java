package com.sergey.prykhodko.front.pages.admin.admincabinet.order;

import com.sergey.prykhodko.front.pages.basepage.adminbasepage.AdminBasePage;
import com.sergey.prykhodko.model.order.Order;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.component.IRequestablePage;

public class OrderPageForAdmin extends AdminBasePage {

    Order order;

    public OrderPageForAdmin(Order order) {
        this.order = order;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addOrderGeneralInfoLabel();
    }

    private void addOrderGeneralInfoLabel() {
        StringBuilder messageDraft = new StringBuilder("Заказ # ")
                .append(order.getOrderId())
                .append(", текущая сумма ")
                .append(order.getSumOrder())
                .append(", статус - ")
                .append(getStatus())
                .append(", дата закрытия: ")
                .append(order.getStartDate().plusDays(2));

        add(new Label("orderBaseInfo", messageDraft.toString()));
    }

    private String getStatus() {
        if (order.isStarted()){
            return "открыт";
        } else {
            return "закрыт";
        }
    }
}
