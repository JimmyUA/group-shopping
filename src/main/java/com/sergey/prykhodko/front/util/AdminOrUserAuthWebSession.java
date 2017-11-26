package com.sergey.prykhodko.front.util;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.services.UserService;
import com.sergey.prykhodko.util.PasswordEncoder;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminOrUserAuthWebSession extends AuthenticatedWebSession {

    private String login = "nobody";
    private final String ADMIN = "admin";
    private final String ADMIN_PASSWORD = "Vika_Ruban85";

    public AdminOrUserAuthWebSession(Request request) {
        super(request);
    }


    @Override
    public void replaceSession() {
    }

    protected boolean authenticate(String login, String password) {
        this.login = login;
        if (ADMIN.equals(login)) {
            return ADMIN.equals(login) && ADMIN_PASSWORD.equals(password);
        } else if (isRegistered(login)){
            String storedPassword = getPassword(login);
            return storedPassword.equals(PasswordEncoder.encodePassword(password));
        }else {
            return false;
        }
    }

    private String getPassword(String login) {
        return UserService.getUserService(FactoryType.SPRING).getPasswordByLogin(login);  //TODO make FactoryType stored in Properties
    }

    private boolean isRegistered(String login) {
        List<String> logins = UserService.getUserService(FactoryType.SPRING).getAllLogins();
        for (String loginFromDB:logins
             ) {
            if (loginFromDB.equals(login)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() && ADMIN.equals(login)) {
            return new Roles(Roles.ADMIN);
        } else if (isSignedIn()) {
            getApplication().getSessionStore().setAttribute(RequestCycle.get().getRequest(), "userLogin", login);
            return new Roles(Roles.USER);
        }
        return null;
    }

}
