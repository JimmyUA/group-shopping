package com.sergey.prykhodko.front.util.captcha;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class ChallengeModel extends AbstractReadOnlyModel<String> {

    private String challange;


    @Override
    public String getObject() {
        if (challange == null){
            challange = String.format("%04d", (int) (Math.random() * 10000));
        }
        return challange;
    }

    public void reset(){
        challange = null;
    }
}
