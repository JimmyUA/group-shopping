package com.sergey.prykhodko.front.pages.admin.admincabinet.order;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.pages.admin.admincabinet.order.suborder.SubOrderForAdminPage;
import com.sergey.prykhodko.front.pages.basepage.adminbasepage.AdminBasePage;
import com.sergey.prykhodko.front.util.data_providers.SubOrderDataProvider;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import com.sergey.prykhodko.services.SubOrderService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.component.IRequestablePage;

import java.util.ArrayList;
import java.util.List;

public class OrderPageForAdmin extends AdminBasePage {

    private Form<Void> form;
    private Order order;

    public OrderPageForAdmin(Order order) {
        this.order = order;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addOrderGeneralInfoLabel();
        addShopLabel();
        form = new Form<>("form");
        addGrid();
        add(form);
    }

    private void addGrid() {
        List<SubOrder> subOrders = order.getSubOrders();
        DataView<SubOrder> dataView = new DataView<SubOrder>("subOrdersDataView", new SubOrderDataProvider<>(subOrders, "id")) {
            @Override
            protected void populateItem(Item<SubOrder> item) {
                SubOrder current = (SubOrder) item.getDefaultModelObject();

                item.add(new SubmitLink("subOrderId", Model.of(current.getId())){
                    @Override
                    public void onSubmit() {
                        super.onSubmit();
                        setResponsePage(new SubOrderForAdminPage(current));
                    }
                }.add(new Label("id", current.getId())));

                item.add(new SubmitLink("subOrderOwnerId", Model.of(current.getId())){
                    @Override
                    public void onSubmit() {
                        super.onSubmit();
                    }
                }.add(new Label("ownerId", current.getOwnerId())));

                item.add(new Label("isPaid", current.isPaid()));
                item.add(new Label("subOrderSum", current.getSumSubOrder()));
                item.add(new AjaxButton("setPaidButton", form) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        super.onSubmit(target, form);
                        current.setPaid(true);
                        SubOrderService.getSubOrderService(FactoryType.SPRING).update(current);
                        target.add(form);
                    }
                });

            }
        };

        form.add(dataView);
    }

    private void addShopLabel() {
        add(new Label("shopLabel", order.getShopName().getName()));
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
        if (order.isStarted()) {
            return "открыт";
        } else {
            return "закрыт";
        }
    }
}
