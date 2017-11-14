package com.sergey.prykhodko.front.util.captcha;

import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class CaptchValidator implements IValidator<String>{
    private IModel<String> challenge;

    public CaptchValidator(IModel<String> challenge) {
        this.challenge = challenge;
    }

    @Override
    public void validate(IValidatable<String> validatable) {
        if (!challenge.getObject().equals(validatable.getValue())){
            ValidationError error = new ValidationError();
            error.setMessage("Вы ввели неверное число!");
            validatable.error(error);
        }
    }
}
