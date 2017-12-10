package com.sergey.prykhodko.front.pages.basepage.adminbasepage;

import com.sergey.prykhodko.front.pages.basepage.FooterPanel;
import org.apache.wicket.markup.html.WebPage;

public class AdminBasePage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AdminMenuPanel("menuPanel"));
        add(new FooterPanel("footerPanel"));
    }
}
