package com.sergey.prykhodko.front.util.captcha;

import org.apache.wicket.extensions.markup.html.captcha.CaptchaImageResource;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;


public class Captcha extends Panel {
    private ChallengeModel challenge;
    private TextField<String> result;

    public Captcha(String id) {
        super(id);
        challenge = new ChallengeModel();
        setDefaultModel(challenge);
        add(new Image("captchaImage", new CaptchaImageResource(challenge)));
        result = new TextField<String>("resultTextField", new Model<String>(null));
        result.setRequired(true);
        result.add(new CaptchValidator(challenge));
        add(result);
    }

    @Override
    protected void onBeforeRender() {
        result.clearInput();
        result.setModelObject(null);
        challenge.reset();
        super.onBeforeRender();
    }
}
