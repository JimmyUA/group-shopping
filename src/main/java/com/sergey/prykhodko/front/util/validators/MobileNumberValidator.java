package com.sergey.prykhodko.front.util.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.regex.Pattern;

public class MobileNumberValidator implements IValidator<String> {
    private final String MOBILE_NUMBER_PATTERN
            = "\\d{3}-\\d{3}-\\d{2}-\\d{2}";
    private final Pattern pattern;

    public MobileNumberValidator() {
        pattern = Pattern.compile(MOBILE_NUMBER_PATTERN);
    }

    @Override
    public void validate(IValidatable<String> validatable) {
        final String mobile = validatable.getValue();
        if (!pattern.matcher(mobile).matches()) {
            error(validatable, "Номер должен быть в формате 0xx-xxx-xx-xx");
        }
    }

    private void error(IValidatable<String> validatable, String errorMessage) {
        ValidationError error = new ValidationError();
        error.setMessage(errorMessage);
        validatable.error(error);
    }
}
