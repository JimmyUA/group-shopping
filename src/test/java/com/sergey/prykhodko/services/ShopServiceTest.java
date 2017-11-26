package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.ShopDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.ShopName;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ShopServiceTest {

    @Test
    public void returnsCorrectShops() throws Exception {
        ShopService service = ShopService.getShopService(FactoryType.SPRING);
        ShopDAO shopDAO = mock(ShopDAO.class);

        when(shopDAO.getAllShopsNames()).thenReturn(Arrays.asList("Sport Direct", "Next"));

        assertEquals(service.getAllShops(), Arrays.asList(ShopName.SPORT_DIRECT));
    }
}