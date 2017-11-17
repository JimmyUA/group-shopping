package com.sergey.prykhodko.util.queries;

public interface SQLLinkCommands {
    String INSERT = "INSERT INTO links(link, item_amount, sum, id_suborder) " +
            "VALUES(?, ?, ?, ?)";
    String GET_LINKS_BY_SUBORDER_ID = "SELECT * FROM links WHERE id_suborder=?";
}
