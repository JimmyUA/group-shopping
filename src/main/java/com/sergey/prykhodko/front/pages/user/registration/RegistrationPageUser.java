package com.sergey.prykhodko.front.pages.user.registration;

import com.sergey.prykhodko.front.pages.userbasepage.UserBasePage;
import com.sergey.prykhodko.front.pages.user.cabinet.UserCabinetPageUser;
import com.sergey.prykhodko.front.util.captcha.Captcha;
import com.sergey.prykhodko.front.util.validators.EmailValidator;
import com.sergey.prykhodko.front.util.validators.LoginUniquenessValidator;
import com.sergey.prykhodko.front.util.validators.MobileNumberValidator;
import com.sergey.prykhodko.model.user.User;
import com.sergey.prykhodko.services.UserService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import static com.sergey.prykhodko.dao.factory.FactoryType.SPRING;

public class RegistrationPageUser extends UserBasePage {

    private static final String FORM = "registerForm";
    private static final String FEEDBACK = "feedback";
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String LAST_NAME = "lastName";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String MOBILE = "mobile";
    private static final String PASSWORD_CONFIRMATION = "passwordConfirmation";


    private String password;

    private User user = new User();
    private final ValueMap properties = new ValueMap();

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form form = new StatelessForm(FORM) {

            @Override
            protected void onInitialize() {
                super.onInitialize();

                CompoundPropertyModel<User> model = new CompoundPropertyModel<>(user);
                TextField<String> nameTextField = new TextField<>(NAME, model.bind(NAME));
                TextField<String> lastNameTextField = new TextField<>(LAST_NAME, model.bind(LAST_NAME));
                TextField<String> loginTextField = new TextField<>(LOGIN, model.bind(LOGIN));
                loginTextField.add(new LoginUniquenessValidator());
                TextField<String> emailTextField = new TextField<>(EMAIL, model.bind(EMAIL));
                emailTextField.add(new EmailValidator());
                TextField<String> mobileTextField = new TextField<>(MOBILE, model.bind(MOBILE));
                mobileTextField.add(new MobileNumberValidator());
                PasswordTextField passwordTextField = new PasswordTextField(PASSWORD, model.bind(PASSWORD));
                PasswordTextField passwordConfTextField = new PasswordTextField(PASSWORD_CONFIRMATION, new PropertyModel<>(properties, PASSWORD_CONFIRMATION));

                add(new EqualPasswordInputValidator(passwordTextField, passwordConfTextField));
                add(new FeedbackPanel(FEEDBACK));
                add(nameTextField);
                add(lastNameTextField);
                add(loginTextField);
                add(emailTextField);
                add(mobileTextField);
                add(passwordTextField);
                add(passwordConfTextField);

                add(new Captcha("captcha"));

            }

            @Override
            protected void onSubmit() {
                super.onSubmit();

                password = properties.getString(PASSWORD_CONFIRMATION);
                user.setPassword(password);
                UserService userService = UserService.getUserService(SPRING);
                userService.addUserToDB(user);
               setResponsePage(UserCabinetPageUser.class);
             }
        };

        add(form);

    }
}
