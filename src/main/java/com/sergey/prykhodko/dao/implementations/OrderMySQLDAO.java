package com.sergey.prykhodko.dao.implementations;

import com.sergey.prykhodko.dao.OrderDAO;
import com.sergey.prykhodko.front.util.ShopName;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.DataSources;
import com.sergey.prykhodko.util.queries.SQLOrderCommands;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;


public class OrderMySQLDAO implements OrderDAO {
    private static final String ID = "id_order";
    private static final String IS_OPENED = "is_opened";
    private static final String IS_STARTED = "is_started";
    private static final String START_DATE = "start_date";
    private static final String SHOP_ID = "id_shop";
    private static final String SUM_ORDER = "sum_order";

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public OrderMySQLDAO() {
        DataSource dataSource = null;
        try {
            dataSource = DataSources.MY_SQL.getDataSource();
        } catch (SQLException e) {
            logger.error(e);
        }
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Order> rowMapper = (rs, rowNum) -> {
        Order order = new Order();
        order.setOrderId(rs.getInt(ID));
        order.setStartDate(rs.getDate(START_DATE).toLocalDate());
        order.setSumOrder(BigDecimal.valueOf(rs.getInt(SUM_ORDER)));
        order.setOpened(rs.getBoolean(IS_OPENED));
        order.setStarted(rs.getBoolean(IS_STARTED));
        order.setShopName(getShopNameByID(rs.getInt(SHOP_ID)));
        return order;
    };

    private ShopName getShopNameByID(int id) {

        switch (id) {
            case 1:
                return ShopName.SPORT_DIRECT;
            default:
                return ShopName.SPORT_DIRECT;
        }
    }


    @Override
    public List<Order> getActiveOrders() {
        PreparedStatementSetter setter = ps -> {
            ps.setBoolean(1, true);
        };
        return jdbcTemplate.query(SQLOrderCommands.GET_ACTIVE_ORDERS, setter, rowMapper);
    }
}