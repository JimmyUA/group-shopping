package com.sergey.prykhodko.front.util.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.regex.Pattern;

public class EmailValidator implements IValidator<String> {

    private final String EMAIL_PATTERN
            = "^([\\w-.а-яА-Я]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private final Pattern pattern;

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
    @Override
    public void validate(IValidatable<String> validatable) {
        final String mobile = validatable.getValue();
        if (!pattern.matcher(mobile).matches()) {
            error(validatable, "Введенный имейл неверен");
        }
    }


    private void error(IValidatable<String> validatable, String errorMessage) {
        ValidationError error = new ValidationError();
        error.setMessage(errorMessage);
        validatable.error(error);
    }
}
