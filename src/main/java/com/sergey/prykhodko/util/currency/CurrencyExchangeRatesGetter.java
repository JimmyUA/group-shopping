package com.sergey.prykhodko.util.currency;

import com.google.gson.Gson;
import com.sergey.prykhodko.util.ClassName;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

public class CurrencyExchangeRatesGetter {

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private static final String API_PROVIDER = "http://apilayer.net/api/live?access_key=1ac5d3cd90f66f2f34cb220e87b8fe67";
    private static final String CURRENCIES = "&currencies = USD,EUR,GBP,UAH&format=1";


    private CurrencyConversionResponse getResponse(String responseUrl) {

        CurrencyConversionResponse response = null;

        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();

        URL url;
        try {
            url = new URL(responseUrl);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            InputStream stream = connection.getInputStream();

            int data = stream.read();

            while (data != -1) {

                sb.append((char) data);

                data = stream.read();
            }

            stream.close();

            response = gson.fromJson(sb.toString(), CurrencyConversionResponse.class);

            logger.info(sb.toString() + " " +this.getClass().getSimpleName());
            logger.info(response.getQuotes());

        } catch (IOException e) {

            logger.error(e);

        }

        response.setResponseDate(LocalDate.now());

        return response;
    }

    public CurrencyConversionResponse getResponse() {

        AtomicReference<CurrencyConversionResponse> response = new AtomicReference<>(getResponse(API_PROVIDER + CURRENCIES));


        return response.get();
    }

}
