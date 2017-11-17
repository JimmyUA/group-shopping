package com.sergey.prykhodko.util.queries;

public interface SQLLinkCommands {
    String INSERT = "INSERT INTO links(link, item_amount, sum, id_suborder) " +
            "VALUES(?, ?, ?, ?)";
}
