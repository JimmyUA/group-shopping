package com.sergey.prykhodko.front.pages.admin.admincabinet.order.suborder;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.pages.admin.admincabinet.client.ClientForAdminPage;
import com.sergey.prykhodko.front.pages.basepage.adminbasepage.AdminBasePage;
import com.sergey.prykhodko.front.util.data_providers.LinksDataProvider;
import com.sergey.prykhodko.front.util.data_providers.SubOrderDataProvider;
import com.sergey.prykhodko.model.order.suborder.Link;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import com.sergey.prykhodko.services.SubOrderService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import java.util.List;

public class SubOrderForAdminPage extends AdminBasePage{

    private SubOrder subOrder;

    public SubOrderForAdminPage(SubOrder subOrder) {
        this.subOrder = subOrder;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addMainLabel();
        add(new SubmitLink("ownerIdLink", Model.of(subOrder.getOwnerId())){
            @Override
            public void onSubmit() {
                super.onSubmit();
                setResponsePage(new ClientForAdminPage(subOrder.getOwnerId()));
            }
        }.add(new Label("ownerId", "Owner: " + subOrder.getOwnerId())));

        addGrid();
    }

    private void addMainLabel() {
        String message = "SubOrder # " +
                subOrder.getId() +
                ", Status - " +
                (subOrder.isPaid() ? "paid" : "not paid") +
                ", Sum - " +
                subOrder.getSumSubOrder()/100;

        add(new Label("mainLabel", message));
    }

    private void addGrid() {
        List<Link> links = subOrder.getLinks();
        DataView<Link> dataView = new DataView<Link>("linksDataView", new LinksDataProvider<>(links, "id")) {
            @Override
            protected void populateItem(Item<Link> item) {
                Link current = (Link) item.getDefaultModelObject();

                item.add(new Label("id", current.getId()));
                final String linkString = current.getLinkString();
                item.add(new ExternalLink("linkString", linkString, linkString));
                item.add(new Label("amount", current.getItemAmount()));
                item.add(new Label("price", current.getItemPrice()/100));

            }
        };
        add(dataView);
    }
}
