package com.sergey.prykhodko.util.queries;

public interface SQLOrderCommands {
    String GET_ACTIVE_ORDERS = "SELECT * FROM orders WHERE is_started=?";
}
