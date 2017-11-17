package com.sergey.prykhodko.model.order.suborder;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SubOrderTest {

    @Test
    public void calculatesSumCorrectly() throws Exception {
        SubOrder subOrder = new SubOrder();
        Integer expectedSum = 300;

        Link link1 = new Link();
        link1.setItemPrice(100);
        link1.setItemAmount(1);

        Link link2 = new Link();
        link2.setItemPrice(100);
        link2.setItemAmount(2);

        subOrder.addLink(link1);
        subOrder.addLink(link2);

        assertEquals(expectedSum, subOrder.getSumSubOrder());
    }

    @Test
    public void calculatesSumCorrectlyWhenSettingListLinks() throws Exception {
        SubOrder subOrder = new SubOrder();
        Integer expectedSum = 300;

        Link link1 = new Link();
        link1.setItemPrice(100);
        link1.setItemAmount(1);

        Link link2 = new Link();
        link2.setItemPrice(100);
        link2.setItemAmount(2);

        List<Link> links = Arrays.asList(link1, link2);

        subOrder.setLinks(links);

        assertEquals(expectedSum, subOrder.getSumSubOrder());
    }
}