package com.sergey.prykhodko.front.pages.basepage;

import com.sergey.prykhodko.front.pages.user.orderList.OrdersListPage;
import com.sergey.prykhodko.front.util.events.CurrencyChangedEvent;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.currency.CurrencyConversionResponse;
import com.sergey.prykhodko.util.currency.CurrencyExchangeRatesGetter;
import org.apache.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.session.ISessionStore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MenuPanel extends Panel {
    private static final String HOME_PAGE_LINK_ID = "toHomePage";
    private static final String ORDERS_LIST_PAGE_LINK_ID = "toOrdersList";
    private static final String LOG_OUT_LINK_ID = "logOut";
    private static final String DROP_DOWN_ID = "currencyChoice";
    private static final String CURRENCIES_APP_STORAGE_KEY = "currencies";

    protected CurrencyConversionResponse currencyRates = (CurrencyConversionResponse) getCurrencyFromSessionStore(getApplication().getSessionStore(), RequestCycle.get().getRequest());

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private static final List<String> SEARCH_ENGINES = Arrays.asList("(UAH) Гривны", "(GBP) Фунты",
            "(USD) Доллары", "(EUR) Евро");

    private String selected = "(UA) Гривны";

    private ISessionStore sessionStore;

    public MenuPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addComponentsToForm();

        sessionStore = getApplication().getSessionStore();
        if (noCurrencySet(sessionStore)) {
            CurrencyConversionResponse currencyRates = new CurrencyExchangeRatesGetter().getResponse();
            setCurrency(sessionStore, currencyRates);
        }
    }

    private void setCurrency(ISessionStore sessionStore, CurrencyConversionResponse currencyRates) {
        sessionStore.setAttribute(getRequest(), CURRENCIES_APP_STORAGE_KEY, currencyRates);
    }

    private boolean noCurrencySet(ISessionStore sessionStore) {
        return getCurrencyFromSessionStore(sessionStore, getRequest()) == null;
    }

    private Serializable getCurrencyFromSessionStore(ISessionStore sessionStore, Request request) {
        return sessionStore.getAttribute(request, CURRENCIES_APP_STORAGE_KEY);
    }

    private void addComponentsToForm() {
        addHomePageLink();
        addOrdersListLink();
        addLogOutLink();

        addDropDownChoice();
    }

    private void addDropDownChoice() {
        final DropDownChoice<String> currencyChoice = new DropDownChoice<>(DROP_DOWN_ID, new PropertyModel<>(this, "selected"), SEARCH_ENGINES);
        add(currencyChoice);
        currencyChoice.add(new AjaxFormComponentUpdatingBehavior("change") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                selected = currencyChoice.getModelObject();
                final Session session = getSession();
                session.setAttribute("currency", selected);
                logger.info("currency " + selected + " is set to session!" + " session id: " + session.getId());
                target.add(getPage());
                target.add(getParent());
                send(getPage(), Broadcast.BREADTH, new CurrencyChangedEvent());
                logger.info("Currency Changed event is sent");
                getExchangeRates();
            }
        });
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

    private void getExchangeRates() {

        currencyRates = (CurrencyConversionResponse) getCurrencyFromSessionStore(sessionStore, getRequest());
        logger.info(currencyRates.getQuotes());
        if (noCurrenciesIsSet() || isRatesNoUpToDate()) {
            currencyRates = new CurrencyExchangeRatesGetter().getResponse();
            setCurrency(sessionStore, currencyRates);
        }
    }

    private boolean isRatesNoUpToDate() {
        return currencyRates.getResponseDate().compareTo(LocalDate.now()) != 0;
    }

    private boolean noCurrenciesIsSet() {
        return currencyRates == null;
    }
}
