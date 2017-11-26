package com.sergey.prykhodko.util.queries;

public interface SQLOrderCommands {
    String GET_ACTIVE_ORDERS = "SELECT * FROM orders WHERE is_started=?";
    String GET_ACTIVE_ORDER_BY_ID = "SELECT * FROM orders WHERE is_started=1 AND id_shop=?";
    String UPDATE = "UPDATE orders SET is_opened=?, is_started=?, sum_order=?";
}
