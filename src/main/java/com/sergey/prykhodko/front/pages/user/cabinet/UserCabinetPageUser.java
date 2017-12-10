package com.sergey.prykhodko.front.pages.user.cabinet;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.pages.userbasepage.UserBasePage;
import com.sergey.prykhodko.front.pages.user.suborder.SubOrderAddingPageUser;
import com.sergey.prykhodko.front.util.events.CurrencyChangedEvent;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import com.sergey.prykhodko.model.user.User;
import com.sergey.prykhodko.services.OrderService;
import com.sergey.prykhodko.services.SubOrderService;
import com.sergey.prykhodko.services.UserService;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.currency.MoneyConverter;
import org.apache.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.cycle.RequestCycle;

import java.util.List;

public class UserCabinetPageUser extends UserBasePage {

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private String currency;
    private User user;


    @Override
    protected void onInitialize() {
        super.onInitialize();
        String login = getUserLoginFromSession();
        user = getUserByLogin(login);
        addGreetingLabel();
        addActiveOrdersLabel();
        List<Order> activeOrders = getActiveOrders();
        addActiveOrdersDataView(activeOrders);
    }

    private void addActiveOrdersDataView(List<Order> activeOrders) {
        ListDataProvider<Order> activeOrdersProvider = new ListDataProvider<>(activeOrders);

        DataView<Order> activeOrdersDataView = getActiveOrdersDataView(activeOrdersProvider);
        add(activeOrdersDataView);
    }

    private List<Order> getActiveOrders() {
        return OrderService.getOrderService(FactoryType.SPRING).getActiveOrders();
    }

    private void addActiveOrdersLabel() {
        String ACTIVE_ORDERS_LABEL_MESSAGE = "Активные заказы";
        Label activeOrdersLabel = new Label("activeOrders", ACTIVE_ORDERS_LABEL_MESSAGE);
        add(activeOrdersLabel);
    }

    private void addGreetingLabel() {
        String greeting = "Приветствую, " + user.getName();
        Label greetingLabel = new Label("greeting", greeting);
        add(greetingLabel);
    }

    private User getUserByLogin(String login) {
        return UserService.getUserService(FactoryType.SPRING).getUserByLogin(login);
    }

    private DataView<Order> getActiveOrdersDataView(ListDataProvider<Order> activeOrdersProvider) {
        return new DataView<Order>("activeOrdersView", activeOrdersProvider) {
            @Override
            protected void populateItem(Item<Order> item) {

                SubOrder openedSuborder = getNotPaidSuborderForOrderAndUser(item);

                addOrderLink(openedSuborder, item);

                addShopImage(item);

                addOpenedSubOrderLabel(openedSuborder, item);



                addSumMessage(item);
                logger.info("current currency: " + currency);
            }

            private void addOpenedSubOrderLabel(SubOrder openedSuborder, Item<Order> item) {
                String openedSuborderNotice;
                if (openedSuborder == null){
                    openedSuborderNotice = "У Вас нетоткрытых подзаказов!";
                } else {
                    openedSuborderNotice = "У Вас есть открытый подзаказ #" + openedSuborder.getId() + " на сумму " +
                            new MoneyConverter(UserCabinetPageUser.this)
                                    .convertFromUAHtoTarget(openedSuborder.getSumSubOrder(),
                                            getCurrencyValue()) / 100.0 + getCurrencyLabel() + " в текущем заказе";
                }


                item.add(new Label("openedSuborder", openedSuborderNotice));
            }

            private SubOrder getNotPaidSuborderForOrderAndUser(Item<Order> item) {
                List<SubOrder> subOrders = SubOrderService.getSubOrderService(FactoryType.SPRING).
                        getSubOrdersByOrderId(((Order) item.getDefaultModelObject()).getOrderId());
                for (SubOrder subOrder : subOrders
                     ) {
                    if(subOrder.getOwnerId().equals(user.getIdUser())){
                        return subOrder;
                    }
                }
                return null;
            }


            private void addSumMessage(Item<Order> item) {
                final Integer sumOrder = item.getModelObject().getSumOrder();
                String orderSumMessage = "Текущая сумма заказа - " +
                        new MoneyConverter(UserCabinetPageUser.this)
                        .convertFromUAHtoTarget(sumOrder,
                        getCurrencyValue()) / 100.0 + getCurrencyLabel();

                Label orderSumLabel = new Label("orderSum",
                        orderSumMessage);
                item.add(orderSumLabel);
            }

            private void addShopImage(Item<Order> item) {
                ContextImage shopImage = new ContextImage("shopImage",
                        item.getModelObject().getShopName().getLogoPath());
                item.add(shopImage);
            }

            private void addOrderLink(SubOrder openedSuborder, Item<Order> item) {
                Link<Void> orderLink = new Link<Void>("orderIDLink") {
                    @Override
                    public void onClick() {
                        if (openedSuborder == null) {
                            setResponsePage(new SubOrderAddingPageUser(item.getModelObject()));
                        } else {
                            setResponsePage(new SubOrderAddingPageUser(item.getModelObject(), openedSuborder));
                        }
                    }


                };

                String orderNumberMessage = "Заказ #" + item.getModelObject().getOrderId();
                Label linkMessageLabel = new Label("linkMessage", orderNumberMessage);
                orderLink.add(linkMessageLabel);
                item.add(orderLink);
            }

            private String getCurrencyValue() {
                final Session session = getSession();
                currency = (String) session.getAttribute("currency");
                logger.info("currency stored in session " + currency + " session id " + session.getId());
                return currency == null ? "(UA) Гривны" : currency;
            }
        };
    }


    private String getUserLoginFromSession() {
        return (String) getApplication().
                getSessionStore().
                getAttribute(RequestCycle.get().getRequest(), "userLogin");
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
        if(event instanceof CurrencyChangedEvent){
            removeAll();
            onInitialize();
        }
    }
}
