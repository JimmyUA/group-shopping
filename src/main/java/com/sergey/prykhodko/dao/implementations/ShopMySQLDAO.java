package com.sergey.prykhodko.dao.implementations;

import com.sergey.prykhodko.dao.ShopDAO;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.DataSources;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.util.queries.SQLShopCommands.GET_ALL_SHOPS_NAMES;

public class ShopMySQLDAO implements ShopDAO {
    private static final String ID = "id_shop";
    private static final String NAME = "name";
    private static final String LINK = "link";

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public ShopMySQLDAO() {
        DataSource dataSource = null;
        try {
            dataSource = DataSources.MY_SQL.getDataSource();
        } catch (SQLException e) {
            logger.error(e);
        }
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }




    @Override
    public List<String> getAllShopsNames() {
        return jdbcTemplate.queryForList(GET_ALL_SHOPS_NAMES, String.class);
    }
}
