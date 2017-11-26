package com.sergey.prykhodko.launch;

import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.ShopName;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.model.order.scheduler.OrderScheduler;
import com.sergey.prykhodko.services.OrderService;
import com.sergey.prykhodko.services.ShopService;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;
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
        List<ShopName> shops = getAllShopsNamesFromDB();

        for (ShopName shop : shops
             ) {
            Order order = OrderService.getOrderService(FactoryType.SPRING).
                    getActiveOrdersByShop(shop.getId());

            if (order == null){
                order = new Order(shop);
            }
            OrderScheduler scheduler = new OrderScheduler();
            scheduler.addOrder(order);
            scheduler.start();
        }
    }

    private static List<ShopName> getAllShopsNamesFromDB() {
        return ShopService.getShopService(FactoryType.SPRING).getAllShops();
    }


}
