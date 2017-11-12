package com.sergey.prykhodko.front.pages.user.cabinet;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.pages.basepage.BasePage;
import com.sergey.prykhodko.model.user.User;
import com.sergey.prykhodko.services.UserService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.cycle.RequestCycle;

public class UserCabinet extends BasePage {
    private User user;


    @Override
    protected void onInitialize() {
        super.onInitialize();
        String login = (String) getApplication().getSessionStore().getAttribute(RequestCycle.get().getRequest(), "userLogin");
        user = UserService.getUserService(FactoryType.SPRING).getUserByLogin(login);
        String greeting = "Приветствую, " + user.getName();
        add(new Label("greeting", greeting));
    }
}
