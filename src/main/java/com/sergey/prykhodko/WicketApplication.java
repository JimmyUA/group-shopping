package com.sergey.prykhodko;

import com.sergey.prykhodko.front.pages.home.HomePage;
import com.sergey.prykhodko.front.pages.login.LogInPage;
import com.sergey.prykhodko.front.util.AdminOrUserAuthWebSession;
import com.sergey.prykhodko.panels.Panels;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;


public class WicketApplication extends AuthenticatedWebApplication {

	private static final String CURRENCIES_APP_STORAGE_KEY = "currencies";

	public WicketApplication() {
	}

	@Override
	public void init() {
		super.init();
		mountPage("/home", HomePage.class);
		mountPage("/panels", Panels.class);

	}



	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return AdminOrUserAuthWebSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LogInPage.class;
	}

	public Class getHomePage()
	{
		return HomePage.class;
	}



}
