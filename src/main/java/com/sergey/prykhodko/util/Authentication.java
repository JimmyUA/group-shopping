package com.sergey.prykhodko.util;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;

public interface Authentication {
    static void auth(){
        AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
        if (!AuthenticatedWebSession.get().isSignedIn()){
            app.restartResponseAtSignInPage();
        }
    }
}
