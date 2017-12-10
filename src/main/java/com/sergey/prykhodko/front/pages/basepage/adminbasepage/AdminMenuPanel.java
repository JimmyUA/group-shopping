package com.sergey.prykhodko.front.pages.basepage.adminbasepage;

import com.sergey.prykhodko.front.pages.admin.UsersListPage;
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
import org.apache.wicket.request.cycle.RequestCycle;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AdminMenuPanel extends Panel{

    private static final String HOME_PAGE_LINK_ID = "toHomePage";
    private static final String ORDERS_LIST_PAGE_LINK_ID = "toOrdersList";
    private static final String USERS_LIST_PAGE_LINK_ID = "toUsersList";
    private static final String LOG_OUT_LINK_ID = "logOut";
    private static final String DROP_DOWN_ID = "currencyChoice";
    private static final String CURRENCIES_APP_STORAGE_KEY = "currencies";

    protected CurrencyConversionResponse currencyRates = (CurrencyConversionResponse) getApplication().getSessionStore().getAttribute(RequestCycle.get().getRequest(), CURRENCIES_APP_STORAGE_KEY);

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private static final List<String> SEARCH_ENGINES = Arrays.asList("(UAH) Гривны", "(GBP) Фунты",
            "(USD) Доллары", "(EUR) Евро");

    private String selected = "(UA) Гривны";

    public AdminMenuPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        addHomePageLink();
        addOrdersListLink();
        addUsersListLink();
        addLogOutLink();
        addDropDownChoice();

        if (getApplication().getSessionStore().getAttribute(getRequest(), CURRENCIES_APP_STORAGE_KEY) == null) {
            CurrencyConversionResponse currencyRates = new CurrencyExchangeRatesGetter().getResponse();
            getApplication().getSessionStore().setAttribute(getRequest(), CURRENCIES_APP_STORAGE_KEY, currencyRates);
        }
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

    private void addUsersListLink() {
        add(new Link(USERS_LIST_PAGE_LINK_ID) {
            @Override
            public void onClick() {
                setResponsePage(UsersListPage.class);
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

        currencyRates = (CurrencyConversionResponse) getApplication().getSessionStore().getAttribute(RequestCycle.get().getRequest(), CURRENCIES_APP_STORAGE_KEY);
        logger.info(currencyRates.getQuotes());
        if (noCurrenciesIsSet() || isRatesNoUpToDate()) {
            currencyRates = new CurrencyExchangeRatesGetter().getResponse();
            getApplication().getSessionStore().setAttribute(getRequest(), CURRENCIES_APP_STORAGE_KEY, currencyRates);
        }
    }

    private boolean isRatesNoUpToDate() {
        return currencyRates.getResponseDate().compareTo(LocalDate.now()) != 0;
    }

    private boolean noCurrenciesIsSet() {
        return currencyRates == null;
    }

}
