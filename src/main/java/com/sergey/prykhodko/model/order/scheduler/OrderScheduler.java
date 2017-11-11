package com.sergey.prykhodko.model.order.scheduler;

import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.util.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;

import static java.lang.Thread.sleep;

public class OrderScheduler implements Serializable{
    private static final Logger LOGER = LoggerFactory.getLogger(ClassName.getCurrentClassName());
    private Order order;
    private LocalDate last;
    private LocalDate current;
    private final int orderLiveTime = 2;


    public OrderScheduler(Order order) {
        this.order = order;
        start();
    }

    public void setLast(LocalDate last) {
        this.last = last;
    }

    public void setCurrent(LocalDate current) {
        this.current = current;
    }

    private void start() {
        while (order.isOpened()) {
            waitAMinute();
            setLast();
            setCurrent();
            if (order.isStarted()) {
                if (twoDaysFromStart()) {
                    order.stop();
                }
            } else {
                if (isNewDay()) {
                    order.start(current);
                }
            }
        }
    }


    private boolean twoDaysFromStart() {
        int startDay = order.getStartDate().getDayOfYear();
        if (current.getDayOfYear() - startDay > orderLiveTime && isSameYear()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNewDay() {
        if (current.getDayOfYear() > last.getDayOfYear() && isSameYear()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSameYear() {
        return current.getYear() == last.getYear();
    }

    private void waitAMinute() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            LOGER.error(e.getMessage());
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private void setLast() {
        if (current != null) {
            last = current;
        } else {
            last = LocalDate.now();
        }
    }

    private void setCurrent() {
        current = LocalDate.now();
    }

    public void getOpenedOrders() {
        //TODO need to implement this work with list of orders
    }

    public Order getOrder() {
        return order;
    }
}
