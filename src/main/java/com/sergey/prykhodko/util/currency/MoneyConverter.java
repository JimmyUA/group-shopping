package com.sergey.prykhodko.util.currency;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;

import java.util.Map;

public class MoneyConverter {
    private static final String CURRENCIES_APP_STORAGE_KEY = "currencies";

    private double USDtoGBP = 0.75;
    private double USDtoUAH = 20.0;                                    // Default values for testing
    private double USDtoEUR = 0.9;

    private static final String USD_TO_EURO_KEY = "USDEUR";
    private static final String USD_TO_UAH_KEY = "USDUAH";
    private static final String USD_TO_POUNDS_KEY = "USDGBP";

    public MoneyConverter(WebPage page) {
        Map<String, String> rates = getRatesFromSessionStore(page);

        USDtoEUR = Double.parseDouble(rates.get(USD_TO_EURO_KEY));
        USDtoGBP = Double.parseDouble(rates.get(USD_TO_POUNDS_KEY));
        USDtoUAH = Double.parseDouble(rates.get(USD_TO_UAH_KEY));


    }

    public MoneyConverter() {
    }

    private Map<String, String> getRatesFromSessionStore(WebPage page) {
        final Request request = page.getRequestCycle().getRequest();
        final CurrencyConversionResponse response = (CurrencyConversionResponse) page.getApplication().getSessionStore().
                getAttribute(request, CURRENCIES_APP_STORAGE_KEY);
        final CurrencyConversionResponse currencies = (CurrencyConversionResponse) page.getApplication().getSessionStore().getAttribute(page.getRequest(), "currencies");
        System.out.println(currencies);
        return response.getQuotes();
    }

    public Integer convertToUAH(Integer sum, String currentCurrency){
        switch (currentCurrency){
            case "(UAH) Гривны":
                return sum;
            case "(GBP) Фунты":
                return convertGBPtoUAH(sum);
            case "(USD) Доллары":
                return convertUSDtoUAH(sum);
            case "(EUR) Евро":
                return convertEURtoUAH(sum);
                default:return sum;
        }
    }

    private Integer convertEURtoUAH(Integer sum) {
        Double sumUSD = sum / USDtoEUR;
        Double result = sumUSD * USDtoUAH;
        return result.intValue();
    }

    private Integer convertUSDtoUAH(Integer sum) {
        Double sumUSD = sum * USDtoUAH;
        return sumUSD.intValue();
    }

    private Integer convertGBPtoUAH(Integer sum) {
        Double sumUSD = sum / USDtoGBP;
        Double result = sumUSD * USDtoUAH;
        return result.intValue();
    }

    public Integer convertFromUAHtoTarget(Integer sum, String target){
        switch (target){
            case "(UAH) Гривны":
                return sum;
            case "(GBP) Фунты":
                return convertUAHtoGBP(sum);
            case "(USD) Доллары":
                return convertUAHtoUSD(sum);
            case "(EUR) Евро":
                return convertUAHtoEUR(sum);
            default:return sum;
        }
    }

    private Integer convertUAHtoEUR(Integer sum) {
        Double sumUSD = sum / USDtoUAH;
        Double result = sumUSD * USDtoEUR;
        return result.intValue();
    }

    private Integer convertUAHtoUSD(Integer sum) {
        Double sumUSD = sum / USDtoUAH;
        return sumUSD.intValue();
    }

    private Integer convertUAHtoGBP(Integer sum) {
        Double sumUSD = sum / USDtoUAH;
        Double result = sumUSD * USDtoGBP;
        return result.intValue();
    }
}
