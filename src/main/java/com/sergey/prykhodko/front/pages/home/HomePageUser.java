package com.sergey.prykhodko.front.pages.home;

import com.sergey.prykhodko.front.pages.userbasepage.UserBasePage;
import com.sergey.prykhodko.front.pages.admin.UsersList;
import com.sergey.prykhodko.util.Authentication;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

/**
 * Homepage
 */
public class HomePageUser extends UserBasePage {

    private final static String WELCOME = "Welcome to Foreign Shops!";
    private final static String LABEL_ID = "message";
    private final static String USERS_LIST_LINK_ID = "goToUsersList";
    private final static String LOG_OUT_LINK_ID = "logOut";

    private static final long serialVersionUID = 1L;

    public HomePageUser() {

        add(new Label(LABEL_ID, WELCOME));

    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        Authentication.auth();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setComponents();
    }

    private void setComponents() {
        addUserListLink();

    }


    private void addUserListLink() {
        add(new Link(USERS_LIST_LINK_ID) {
            @Override
            public void onClick() {
                setResponsePage(getApplication().getPageFactory().newPage(UsersList.class));
            }
        }.setAutoEnable(true));
    }
}
