package com.sergey.prykhodko.model.order.scheduler;

import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.util.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class OrderScheduler implements Serializable{
    private static final Logger LOGER = LoggerFactory.getLogger(ClassName.getCurrentClassName());
    private List<Order> orders;

    public OrderScheduler() {
        orders = new ArrayList<>();
    }


    public void start(){
        for (Order order: orders
             ) {
            startSchedule(order);
        }
    }

    private void startSchedule(Order order){
        Thread orderHandler = new Thread(new OrderHandler(order));
        orderHandler.setDaemon(true);
        orderHandler.start();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }


    private class OrderHandler implements Runnable{

        private LocalDate last;
        private LocalDate current;
        private final int orderLiveTime = 2;
        private Order order;

        public OrderHandler(Order order) {
            this.order = order;
        }

        @Override
        public void run() {
            while (order.isOpened()) {
                waitAMinute();
                LOGER.info("New lap starts ");
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
        public void setCurrent(LocalDate current) {
            this.current = current;
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
                sleep(60*1000);
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

        public Order getOrder() {
            return order;
        }

        public void setLast(LocalDate last) {
            this.last = last;
        }
    }


}
