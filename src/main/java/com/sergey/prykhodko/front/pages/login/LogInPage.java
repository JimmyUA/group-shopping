package com.sergey.prykhodko.front.pages.login;

import com.sergey.prykhodko.front.pages.basepage.BasePage;
import com.sergey.prykhodko.front.pages.home.HomePage;
import com.sergey.prykhodko.front.pages.user.cabinet.UserCabinetPage;
import com.sergey.prykhodko.front.pages.user.registration.RegistrationPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.value.ValueMap;

public class LogInPage extends BasePage {
    private static final long serialVersionUID = 1L;

    private static final String FORM = "form";
    private static final String FEEDBACK = "feedback";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REGISTRATION = "registration";

    private static final String MISSED_LOGIN = "Необходимо ввести логин";
    private static final String MISSED_PASSWORD = "Необходимо ввести пароль";
    private static final String INVALID_CREDENTIALS = "Пароль или логин неверен";



    private String login;
    private String password;

    private final ValueMap properties = new ValueMap();

    @Override
    protected void onInitialize() {
        super.onInitialize();

        StatelessForm form = new StatelessForm(FORM){

            @Override
            protected void onSubmit(){
                login = properties.getString(LOGIN);
                password = properties.getString(PASSWORD);
                checkIfAuthenticateDone();


            }

            private void checkIfAuthenticateDone() {
                if (AuthenticatedWebSession.get().signIn(login, password)){
                    AuthenticatedWebSession session = (AuthenticatedWebSession) getSession();
                    Roles role = session.getRoles();
                    if (role.equals(new Roles(Roles.USER))){
                        setResponsePage(UserCabinetPage.class);
                    } else {
                        setResponsePage(HomePage.class);
                    }
                } else {
                    if (Strings.isEmpty(login)){
                        error(MISSED_LOGIN);
                    }
                    if (Strings.isEmpty(password)){
                        error(MISSED_PASSWORD);
                    } else {
                        error(INVALID_CREDENTIALS);
                    }
                }
            }

        };

        setDefaultModel(form);
        setComponents(form);
        add(form);

        addRegistrationLink();
    }

    private void addRegistrationLink() {
        add(new Link(REGISTRATION) {
            @Override
            public void onClick() {
                setResponsePage(RegistrationPage.class);
            }
        });
    }

    private void setDefaultModel(StatelessForm form) {
        form.setDefaultModel(new CompoundPropertyModel<>(this));
    }

    private void setComponents(StatelessForm form) {
        form.add(new TextField<>(LOGIN, new PropertyModel<>(properties, LOGIN)));
        form.add(new PasswordTextField(PASSWORD, new PropertyModel<>(properties, PASSWORD)));
        form.add(new FeedbackPanel(FEEDBACK));
    }
}
