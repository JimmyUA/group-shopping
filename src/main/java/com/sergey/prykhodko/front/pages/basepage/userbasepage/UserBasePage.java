package com.sergey.prykhodko.front.pages.basepage.userbasepage;

import com.sergey.prykhodko.front.pages.basepage.FooterPanel;
import org.apache.wicket.markup.html.WebPage;

public class UserBasePage extends WebPage {
    public UserBasePage() {
        add(new UserMenuPanel("menuPanel"));
        add(new FooterPanel("footerPanel"));
    }
}
