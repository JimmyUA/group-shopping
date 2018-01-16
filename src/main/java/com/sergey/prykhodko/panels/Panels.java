package com.sergey.prykhodko.panels;

import com.sergey.prykhodko.panels.databasemock.DataBaseMock;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.Panel;

import java.lang.reflect.Constructor;

public class Panels extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        String panel1Name = DataBaseMock.getPanelName(1);
        System.out.println(panel1Name);
        String panel2Name = DataBaseMock.getPanelName(2);
        System.out.println(panel2Name);
        String panel3Name = DataBaseMock.getPanelName(3);
        System.out.println(panel3Name);



        Class panel1Class;
        Class panel2Class;
        Class panel3Class;

        Constructor constructor1 = null;
        Constructor constructor2 = null;
        Constructor constructor3 = null;

        Panel panel1 = new Panel("panel1") {
            @Override
            protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
                return super.newMarkupSourcingStrategy();
            }
        };
        Panel panel2 = new Panel("panel2") {
            @Override
            protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
                return super.newMarkupSourcingStrategy();
            }
        };
        Panel panel3 =new Panel("panel3") {
            @Override
            protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
                return super.newMarkupSourcingStrategy();
            }
        };
        try {
            String PANELS_PATH = "com.sergey.prykhodko.panels.variants.";
            panel1Class = Class.forName(PANELS_PATH + panel1Name);
            panel2Class = Class.forName(PANELS_PATH + panel2Name);
            panel3Class = Class.forName(PANELS_PATH + panel3Name);


            constructor1 = panel1Class.getConstructor(String.class);
            constructor2 = panel2Class.getConstructor(String.class);
            constructor3 = panel3Class.getConstructor(String.class);

            panel1 = (Panel) constructor1.newInstance("panel1");

            panel2 = (Panel) constructor2.newInstance("panel2");
            panel3 =(Panel) constructor3.newInstance("panel3");

        } catch (Exception e) {
            e.printStackTrace();
        }


        add(panel1);
        add(panel2);
        add(panel3);
    }
}
