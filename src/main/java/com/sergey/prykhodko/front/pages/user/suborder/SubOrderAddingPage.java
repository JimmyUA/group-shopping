package com.sergey.prykhodko.front.pages.user.suborder;

import com.sergey.prykhodko.front.pages.basepage.BasePage;
import com.sergey.prykhodko.front.util.events.CurrencyChangedEvent;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.currency.MoneyConverter;
import org.apache.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;

public class SubOrderAddingPage extends BasePage {

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private Order order;
    private String currency;
    private SubOrder subOrder;

    private String currentSumLabelMessage;
    private Label currentSumLabel;

    public SubOrderAddingPage(Order order) {
        this.order = order;
    }

    public SubOrderAddingPage(Order order, SubOrder subOrder) {
        this.order = order;
        this.subOrder = subOrder;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        addOrderLabel();

        currentSumLabelMessage = getCurrentSumLabelMessage();
        currentSumLabel = new Label("currentSumLabel", currentSumLabelMessage);
        add(currentSumLabel);
        SubOrderAddingPanel subOrderAddingPanel;
        if (userHaveNoSuborder()) {
            subOrderAddingPanel = new SubOrderAddingPanel("subOrderAdding", order);
        } else {
            subOrderAddingPanel = new SubOrderAddingPanel("subOrderAdding", order, subOrder);
        }
        subOrderAddingPanel.setCurrency(currency);
        add(subOrderAddingPanel);
    }

    private boolean userHaveNoSuborder() {
        return subOrder == null;
    }

    private void addOrderLabel() {
        String orderLabelMessage = "Заказ # " + order.getOrderId() + " " + order.getShopName().getName();
        add(new Label("orderLabel", orderLabelMessage));
    }

    private String getCurrentSumLabelMessage() {
        return new MoneyConverter(this).convertFromUAHtoTarget(order.getSumOrder(),
                getCurrencyValue())/100.0 + getCurrencyLabel();
    }

    private String getCurrencyValue() {
        final Session session = getSession();
        currency = (String) session.getAttribute("currency");
        logger.info("currency stored in session " + currency + " session id " + session.getId());
        return currency == null ? "(UA) Гривны" : currency;
    }

    private String getCurrencyLabel() {
        String currency = (String) getSession().getAttribute("currency");

        if (currency == null){
            return " гривен";
        }

        return MoneyConverter.choseCurrencyLabel(currency);
    }

    @Override
    public void onEvent(IEvent<?> event) {
        super.onEvent(event);
        if (event.getPayload() instanceof CurrencyChangedEvent){
            remove(currentSumLabel);
            currentSumLabel = new Label("currentSumLabel", getCurrentSumLabelMessage());
            add(currentSumLabel);
        }
    }
}
