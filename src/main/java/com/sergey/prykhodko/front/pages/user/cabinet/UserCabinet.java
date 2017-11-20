package com.sergey.prykhodko.front.pages.user.cabinet;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.pages.basepage.BasePage;
import com.sergey.prykhodko.front.pages.user.suborder.SubOrderAddingPage;
import com.sergey.prykhodko.front.util.events.PageRerenderEvent;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.user.User;
import com.sergey.prykhodko.services.OrderService;
import com.sergey.prykhodko.services.UserService;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.currency.MoneyConverter;
import org.apache.log4j.Logger;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.cycle.RequestCycle;

import java.io.Serializable;
import java.util.List;

public class UserCabinet extends BasePage {

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private final String ACTIVE_ORDERS_LABEL_MESSAGE = "Активные заказы";

    private User user;


    @Override
    protected void onInitialize() {
        super.onInitialize();
        logger.info("rendered");
        String login = (String) getApplication().getSessionStore().getAttribute(RequestCycle.get().getRequest(), "userLogin");
        user = UserService.getUserService(FactoryType.SPRING).getUserByLogin(login);
        String greeting = "Приветствую, " + user.getName();
        add(new Label("greeting", greeting));

        add(new Label("activeOrders", ACTIVE_ORDERS_LABEL_MESSAGE));

        List<Order> activeOrders = OrderService.getOrderService(FactoryType.SPRING).getActiveOrders();

        ListDataProvider<Order> activeOrdersProvider = new ListDataProvider<>(activeOrders);

        DataView<Order> activeOrdersDataView = new DataView<Order>("activeOrdersView", activeOrdersProvider) {
            @Override
            protected void populateItem(Item<Order> item) {
                Link<Void> orderLink = new Link<Void>("orderIDLink") {
                    @Override
                    public void onClick() {
                        setResponsePage(new SubOrderAddingPage(item.getModelObject()));
                    }


                };
                orderLink.add(new Label("linkMessage", "Заказ #" + item.getModelObject().getOrderId()));
                item.add(orderLink);
                item.add(new Label("orderSum",
                        new MoneyConverter(UserCabinet.this).convertFromUAHtoTarget(item.getModelObject().getSumOrder(),
                                getCurrencyValue())/100.0 + getCurrencyLabel()));
                logger.info("current currency: " + getCurrencyValue());
            }

            private String getCurrencyValue() {
                final String currency = (String) getSession().getAttribute("currency");
                logger.info("currency stored in session " + currency + " session id " + getSession().getId());
                return currency == null ? "(UA) Гривны" : currency;
            }
        };
        add(activeOrdersDataView);
    }

    private String getCurrencyLabel() {
        String currency = (String) getSession().getAttribute("currency");

        if (currency == null){
            return " гривен";
        }

        switch (currency){
            case "(GBP) Фунты":
                return " фунтов";
            case "(USD) Доллары":
                return " долларов";
            case "(EUR) Евро":
                return " евро";
                default:
                    return " гривен";
        }
    }

    @Override
    public void onEvent(IEvent<?> event) {
        super.onEvent(event);
        if(event instanceof PageRerenderEvent){
            logger.info("event PageRerender is catched!");
            removeAll();
            onInitialize();
        }
    }
}
