package com.sergey.prykhodko.front.pages.basepage;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {
    public BasePage() {
        add(new MenuPanel("menuPanel"));
        add(new FooterPanel("footerPanel"));
    }
}
