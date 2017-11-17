package com.sergey.prykhodko.model.order;

import com.sergey.prykhodko.model.order.suborder.SubOrder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {
    @Test
    public void calculatesSumCorrectly() throws Exception {
        Order order = new Order();
        Integer expectedSum = 200;

        SubOrder subOrder1 = new SubOrder();
        SubOrder subOrder2 = new SubOrder();
        subOrder1.setSumSubOrder(100);
        subOrder2.setSumSubOrder(100);

        order.addSubOrder(subOrder1);
        order.addSubOrder(subOrder2);

        assertEquals(expectedSum, order.getSumOrder());
    }

    @Test
    public void calculateSumCorrectlyWhenSettingListSuborders() throws Exception {
        Order order = new Order();
        Integer expectedSum = 200;

        SubOrder subOrder1 = new SubOrder();
        SubOrder subOrder2 = new SubOrder();
        subOrder1.setSumSubOrder(100);
        subOrder2.setSumSubOrder(100);

        List<SubOrder> subOrderList = Arrays.asList(subOrder1, subOrder2);

        order.setSubOrders(subOrderList);

        assertEquals(expectedSum, order.getSumOrder());

    }
}