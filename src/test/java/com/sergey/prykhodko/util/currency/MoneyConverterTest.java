package com.sergey.prykhodko.util.currency;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyConverterTest {

    @Test
    public void correctConversionFromGBPtoUAH() throws Exception {
        Integer sumGBP = 10;
        Integer expectedInUAH = 266;

        Integer inUAH = new MoneyConverter().convertToUAH(sumGBP, "(GBP) Фунты");

        assertEquals(expectedInUAH, inUAH);
    }

    @Test
    public void correctConversionFromUSDtoUAH() throws Exception {
        Integer sumUSD = 10;
        Integer expectedInUAH = 200;

        Integer inUAH = new MoneyConverter().convertToUAH(sumUSD, "(USD) Доллары");

        assertEquals(expectedInUAH, inUAH);
    }

    @Test
    public void correctConversionFromEURtoUAH() throws Exception {
        Integer sumEUR = 10;
        Integer expectedInUAH = 222;

        Integer inUAH = new MoneyConverter().convertToUAH(sumEUR, "(EUR) Евро");

        assertEquals(expectedInUAH, inUAH);
    }

    @Test
    public void correctConversionFromUAHtoGBP() throws Exception {
        Integer sumUAH = 100;
        Integer expectedInGBP = 3;

        Integer inGBP = new MoneyConverter().convertFromUAHtoTarget(sumUAH, "(GBP) Фунты");

        assertEquals(expectedInGBP, inGBP);
    }

    @Test
    public void correctConversionFromUAHtoUSD() throws Exception {
        Integer sumUAH = 100;
        Integer expectedInUSD = 5;

        Integer inUAH = new MoneyConverter().convertFromUAHtoTarget(sumUAH, "(USD) Доллары");

        assertEquals(expectedInUSD, inUAH);
    }

    @Test
    public void correctConversionFromUAHtoEUR() throws Exception {
        Integer sumUAH = 100;
        Integer expectedInEUR = 4;

        Integer inUAH = new MoneyConverter().convertFromUAHtoTarget(sumUAH, "(EUR) Евро");

        assertEquals(expectedInEUR, inUAH);
    }
}