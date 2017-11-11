package com.sergey.prykhodko.front.util;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class AdminAuthWebSession extends AuthenticatedWebSession {
    public AdminAuthWebSession(Request request) {
        super(request);
    }

    protected boolean authenticate(String login, String password) {
        final String ADMIN = "admin";
        return ADMIN.equals(login) && ADMIN.equals(password);
    }

    public Roles getRoles() {
        if (isSignedIn()){
            return new Roles(Roles.ADMIN);
        }
        return null;
    }
}
