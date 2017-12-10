package com.sergey.prykhodko.front.util.validators;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;


public class EmailValidatorTest {

    private String markup = "<form method=\"post\" wicket:id=\"registerForm\">\n" +
            "                            <input type=\"text\" wicket:id=\"email\">\n" +
            "                </form>";

    private WicketTester wicketTester;
    private Form<?> form;
    private TextField<String> textField;
    private EmailValidator emailValidator;
    private FormTester formTester;

    @Before
    public void setUp() throws Exception {
        wicketTester =new WicketTester();

        form = new Form<Void>("registerForm");
        textField = new TextField<>("email", new Model<>(""));
        form.add(textField);
        emailValidator = new EmailValidator();
        textField.add(emailValidator);

        form = wicketTester.startComponentInPage(form, Markup.of(markup));
        formTester = wicketTester.newFormTester("registerForm");

    }

    @Test
    public void noErrorOnLatinCorrectInput() throws Exception {
        formTester.setValue("email", "jimmy18_88@mail.ru");
        formTester.submit();
        wicketTester.assertNoErrorMessage();
    }

    @Test
    public void noErrorOnRussianCorrectInput() throws Exception {
        formTester.setValue("email", "Сергей@mail.ru");
        formTester.submit();
        wicketTester.assertNoErrorMessage();
    }

    @Test
    public void errorWithoutDoggy() throws Exception {
        formTester.setValue("email", "jimmy.mail.ru");
        formTester.submit();
        wicketTester.assertErrorMessages("Введенный имейл неверен");
    }
}