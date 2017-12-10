package com.sergey.prykhodko.front.pages.basepage.userbasepage;

import com.sergey.prykhodko.front.pages.basepage.MenuPanel;
import com.sergey.prykhodko.front.pages.user.orderList.OrdersListPage;
import com.sergey.prykhodko.util.currency.CurrencyConversionResponse;
import com.sergey.prykhodko.util.currency.CurrencyExchangeRatesGetter;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.Link;

public class UserMenuPanel extends MenuPanel {
    private static final String HOME_PAGE_LINK_ID = "toHomePage";
    private static final String ORDERS_LIST_PAGE_LINK_ID = "toOrdersList";
    private static final String LOG_OUT_LINK_ID = "logOut";
    private static final String CURRENCIES_APP_STORAGE_KEY = "currencies";


    public UserMenuPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addComponentsToForm();
        if (getApplication().getSessionStore().getAttribute(getRequest(), CURRENCIES_APP_STORAGE_KEY) == null) {
            CurrencyConversionResponse currencyRates = new CurrencyExchangeRatesGetter().getResponse();
            getApplication().getSessionStore().setAttribute(getRequest(), CURRENCIES_APP_STORAGE_KEY, currencyRates);
        }
    }

    private void addComponentsToForm() {
        addHomePageLink();
        addOrdersListLink();
        addLogOutLink();
    }

    private void addLogOutLink() {
        add(new Link(LOG_OUT_LINK_ID) {
            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }

        });
    }

    private void addOrdersListLink() {
        add(new Link(ORDERS_LIST_PAGE_LINK_ID) {
            @Override
            public void onClick() {
                setResponsePage(OrdersListPage.class);
            }
        });
    }

    private void addHomePageLink() {
        add(new Link(HOME_PAGE_LINK_ID) {
            @Override
            public void onClick() {
                setResponsePage(getApplication().getHomePage());
            }
        });
    }

}