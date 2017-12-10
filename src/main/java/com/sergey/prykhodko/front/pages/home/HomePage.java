package com.sergey.prykhodko.front.pages.home;

import com.sergey.prykhodko.front.pages.basepage.BasePage;
import com.sergey.prykhodko.front.pages.user.cabinet.UserCabinetPage;
import com.sergey.prykhodko.util.Authentication;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

/**
 * Homepage
 */
public class HomePage extends BasePage {

    private final static String WELCOME = "Приветствуем в Group Shopping!";
    private final static String LABEL_ID = "message";
    private final static String USER_CABINEt_LINK_ID = "goToUserCabinet";

    private static final long serialVersionUID = 1L;

    @Override
    protected void onConfigure() {
        super.onConfigure();
        Authentication.auth();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label(LABEL_ID, WELCOME));
        setComponents();
    }

    private void setComponents() {
        addUserListLink();
    }


    private void addUserListLink() {
        add(new Link(USER_CABINEt_LINK_ID) {
            @Override
            public void onClick() {
                setResponsePage(getApplication().getPageFactory().newPage(UserCabinetPage.class));
            }
        }.setAutoEnable(true));
    }
}
