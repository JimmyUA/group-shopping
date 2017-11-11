package com.sergey.prykhodko;

import com.sergey.prykhodko.front.pages.home.HomePage;
import com.sergey.prykhodko.front.pages.login.LogInPage;
import com.sergey.prykhodko.front.pages.user.UsersList;
import com.sergey.prykhodko.front.util.AdminAuthWebSession;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;


public class WicketApplication extends AuthenticatedWebApplication
{

	public WicketApplication() {
	}

	@Override
	public void init()
	{
		super.init();
//		getSessionStore().setAttribute(null, "scheduler",
//				new OrderScheduler(new Order(ShopName.SPORT_DIRECT)));
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
