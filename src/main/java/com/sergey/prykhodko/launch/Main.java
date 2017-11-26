package com.sergey.prykhodko.launch;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.front.util.ShopName;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.scheduler.OrderScheduler;
import com.sergey.prykhodko.services.ShopService;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ServletException, LifecycleException {
        Tomcat tomcat = getTomcat();

        startOrdersScheduling();

        tomcat.start();
        tomcat.getServer().await();

    }

    private static Tomcat getTomcat() throws ServletException {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()){
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/",
                new File(webappDirLocation).getAbsolutePath());

        WebResourceRoot resources = new StandardRoot(ctx);

        File additionWebInfClasses = new File("target/classes");

        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));

        ctx.setResources(resources);
        return tomcat;
    }

    private static void startOrdersScheduling() {
        List<String> shopNames = getAllShopsNamesFromDB();
        System.out.println(shopNames);
        OrderScheduler scheduler = new OrderScheduler();
        scheduler.addOrder(new Order(ShopName.SPORT_DIRECT));
        scheduler.start();
    }

    private static List<String> getAllShopsNamesFromDB() {
        List<String> names = new ArrayList<>(2);
        names = ShopService.getShopService(FactoryType.SPRING).getAllShopsNames();
        return names;
    }
}
