package com.sergey.prykhodko.front.pages.user.suborder;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.suborder.Link;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import com.sergey.prykhodko.model.user.User;
import com.sergey.prykhodko.services.OrderService;
import com.sergey.prykhodko.services.SubOrderService;
import com.sergey.prykhodko.services.UserService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;


public class SubOrderAddingPanel extends Panel {
    private User user;
    private Order order;
    private SubOrder subOrder;
    private Button addSubOrderButton;

    private TextField<String> linkTF;
    private NumberTextField<Integer> amountTF;
    private TextField<String> priceTF;

    public SubOrderAddingPanel(String id, Order order) {
        super(id);
        this.order = order;
        subOrder = new SubOrder();
        order.addSubOrder(subOrder);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        getUserFromSession();
        setSubOrderInfo();

        ListDataProvider<Link> linksDataProvider = new ListDataProvider<>(subOrder.getLinks());
        DataView<Link> linksDataView = getLinksDataView(linksDataProvider);

        Form<Void> form = new Form<>("form");
        linkTF = new TextField<>("linkTF", new Model<>());
        amountTF = new NumberTextField<>("amountTF", new Model<>(), Integer.class);
        priceTF = new TextField<>("priceTF", new Model<>(""));
        Button addLinkButton = getAddLinkButton(linkTF, amountTF, priceTF);

        addSubOrderButton = getAddSubOrderButton();

        addSubOrderButton.setVisible(false);
        form.add(linkTF);
        form.add(amountTF);
        form.add(priceTF);
        form.add(addLinkButton);
        form.add(addSubOrderButton);
        add(linksDataView);
        add(form);
    }

    private void getUserFromSession() {
        String login = (String) getSession().getAttribute("userLogin");
        user = UserService.getUserService(FactoryType.SPRING).getUserByLogin(login);
    }

    private void setSubOrderInfo() {
        subOrder.setOwnerId(user.getIdUser());
        subOrder.setOrderId(order.getOrderId());
    }

    private Button getAddSubOrderButton() {
        return new Button("addSubOrderButton") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                order.addSubOrder(subOrder);
                SubOrderService.getSubOrderService(FactoryType.SPRING).addSubOrder(subOrder);
                OrderService.getOrderService(FactoryType.SPRING).updateOrder(order);
            }
        };
    }

    private Button getAddLinkButton(TextField<String> linkTF, NumberTextField<Integer> amountTF, TextField<String> priceTF) {
        return new Button("addLinkButton") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                String link = linkTF.getModelObject();
                Integer amount = amountTF.getModelObject();
                String priceString = priceTF.getDefaultModelObjectAsString();
                Integer price = setPrice(priceString);

                Link linkObject = new Link();
                linkObject.setLinkString(link);
                linkObject.setItemAmount(amount);
                linkObject.setItemPrice(price);
                linkObject.setSubOrderId(subOrder.getId());
                subOrder.addLink(linkObject);

                SubOrderAddingPanel.this.clearInput();
                setAddSubOrderButtonVisible();

            }
        };
    }


    private void clearInput() {
        linkTF.clearInput();
        linkTF.setModel(new Model<>());
        amountTF.clearInput();
        amountTF.setModel(new Model<>());
        priceTF.clearInput();
        priceTF.setModel(new Model<>());
    }

    private void setAddSubOrderButtonVisible() {
        if (!subOrder.getLinks().isEmpty()) {
            addSubOrderButton.setVisible(true);
        }
    }

    private DataView<Link> getLinksDataView(ListDataProvider<Link> linksDataProvider) {
        return new DataView<Link>("linksDataView", linksDataProvider) {
            @Override
            protected void populateItem(Item<Link> item) {
                item.add(new Label("link", item.getModelObject().getLinkString()));
                item.add(new Label("amount", item.getModelObject().getItemAmount()));
                item.add(new Label("sum", item.getModelObject().getItemPrice()));
            }
        };
    }

    private Integer setPrice(String input) {
        Double priceDouble = Double.parseDouble(input);
        Double priceDoubleWithCents = priceDouble * 100;

        return priceDoubleWithCents.intValue();
    }
}
