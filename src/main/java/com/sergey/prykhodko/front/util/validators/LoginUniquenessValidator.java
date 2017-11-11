package com.sergey.prykhodko.front.util.validators;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.services.UserService;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.List;

public class LoginUniquenessValidator implements IValidator<String> {
    @Override
    public void validate(IValidatable<String> validatable) {
        final String login = validatable.getValue();
        if (isLoginExists(login)){
            error(validatable, "Введенный логин уже зарегистрирован");
        }
    }

    private boolean isLoginExists(String login) {
        List<String> logins = UserService.getUserService(FactoryType.SPRING).getAllLogins();
        for (String loginExisting : logins
             ) {
            if (loginExisting.equals(login)){
                return true;
            }
        }
        return false;
    }

    private void error(IValidatable<String> validatable, String errorMessage) {
        ValidationError error = new ValidationError();
        error.setMessage(errorMessage);
        validatable.error(error);
    }
}
