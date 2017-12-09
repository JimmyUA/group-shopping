package com.sergey.prykhodko.util.queries;

public interface SQLSubOrderCommands {
    String INSERT = "INSERT INTO suborders(id_order, id_user, sum_suborder, is_paid) " +
            "VALUES(?, ?, ?, ?)";
    String GET_LAST_ID = "SELECT MAX(id_suborder) FROM suborders";
    String GET_SUBORDERS_BY_ORDER_ID = "SELECT * FROM suborders WHERE id_order=?";
    String UPDATE = "UPDATE suborders SET sum_suborder=?, is_paid=? WHERE id_suborder=?";
}
