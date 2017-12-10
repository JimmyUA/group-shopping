package com.sergey.prykhodko.front.pages.userbasepage;

import org.apache.wicket.markup.html.WebPage;

public class UserBasePage extends WebPage {
    public UserBasePage() {
        add(new MenuPanel("menuPanel"));
        add(new FooterPanel("footerPanel"));
    }
}
