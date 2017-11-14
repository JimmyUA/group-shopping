package com.sergey.prykhodko.front.pages.user.suborder;

import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.suborder.Link;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
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

import java.math.BigInteger;

public class SubOrderAddingPanel extends Panel{
    private Order order;
    private SubOrder subOrder;

    public SubOrderAddingPanel(String id, Order order) {
        super(id);
        this.order = order;
        subOrder = new SubOrder();
        order.addSubOrder(subOrder);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        ListDataProvider<Link> linksDataProvider = new ListDataProvider<>(subOrder.getLinks());
        DataView<Link> linksDataView = new DataView<Link>("linksDataView", linksDataProvider) {
            @Override
            protected void populateItem(Item<Link> item) {
                item.add(new Label("link", item.getModelObject().getLinkString()));
                item.add(new Label("amount", item.getModelObject().getItemAmount()));
                item.add(new Label("sum", item.getModelObject().getItemPrice()));
            }
        };

        Form<Void> form = new Form<>("form");
        TextField<String> linkTF = new TextField<>("linkTF", new Model<>());
        form.add(linkTF);
        NumberTextField<Integer> amountTF = new NumberTextField<>("amountTF", new Model<>());
        form.add(amountTF);
        TextField<String> sumTF = new TextField<>("sumTF", new Model<>());
        form.add(sumTF);
        form.add(new Button("addButton"){
            @Override
            public void onSubmit() {
                super.onSubmit();
                String link = linkTF.getModelObject();
                Integer amount = amountTF.getModelObject();
                double sumFloat = Double.parseDouble(sumTF.getModelObject());
                BigInteger price = BigInteger.valueOf(Double.doubleToLongBits(100 * sumFloat));

                Link linkObject = new Link();
                linkObject.setLinkString(link);
                linkObject.setItemAmount(amount);
                linkObject.setItemPrice(price);

            }
        });
    }
}
