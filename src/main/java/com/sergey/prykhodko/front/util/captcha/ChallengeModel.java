package com.sergey.prykhodko.front.util.captcha;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class ChalangeModel extends AbstractReadOnlyModel<String> {

    private String challange;


    @Override
    public String getObject() {
        if (challange == null){
            challange = String.format("%04d", (int) (Math.random() * 10000));
        }
        return null;
    }

    public void reset(){
        challange = null;
    }
}
