package com.sergey.prykhodko;

import com.sergey.prykhodko.front.pages.home.HomePage;
import com.sergey.prykhodko.front.pages.login.LogInPage;
import com.sergey.prykhodko.front.util.AdminAuthWebSession;
import com.sergey.prykhodko.front.util.ShopName;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.scheduler.OrderScheduler;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;


public class WicketApplication extends AuthenticatedWebApplication
{

	public WicketApplication() {
	}

	@Override
	public void init() {
		super.init();
		mountPage("/home", HomePage.class);

	}


	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return AdminAuthWebSession.class;
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
