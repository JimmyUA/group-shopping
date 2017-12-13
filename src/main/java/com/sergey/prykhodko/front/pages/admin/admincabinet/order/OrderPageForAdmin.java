package com.sergey.prykhodko.front.pages.admin.admincabinet.order;

import com.sergey.prykhodko.front.pages.basepage.adminbasepage.AdminBasePage;
import com.sergey.prykhodko.front.util.data_providers.SubOrderDataProvider;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.component.IRequestablePage;

import java.util.ArrayList;
import java.util.List;

public class OrderPageForAdmin extends AdminBasePage {

    Order order;

    public OrderPageForAdmin(Order order) {
        this.order = order;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addOrderGeneralInfoLabel();
        addShopLabel();
        addGrid();
    }

    private void addGrid() {
        List<IColumn<SubOrder, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<SubOrder, String>(Model.of("Id"), "id", "id"));
        columns.add(new PropertyColumn<SubOrder, String>(Model.of("OwnerId"), "ownerId", "ownerId"));
        columns.add(new PropertyColumn<SubOrder, String>(Model.of("SumSubOrder"), "sumSubOrder", "sumSubOrder"));
        columns.add(new PropertyColumn<SubOrder, String>(Model.of("IsPaid"), "isPaid", "isPaid"));

        DataTable<SubOrder, String> dataTable = new DataTable<>("subOrdersDataTable", columns,
                new SubOrderDataProvider<SubOrder, String>(order.getSubOrders(), "id"), 10);

        add(dataTable);
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
