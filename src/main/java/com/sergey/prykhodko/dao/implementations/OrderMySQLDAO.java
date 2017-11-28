package com.sergey.prykhodko.dao.implementations;

import com.sergey.prykhodko.dao.OrderDAO;
import com.sergey.prykhodko.model.ShopName;
import com.sergey.prykhodko.model.order.Order;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.DataSources;
import com.sergey.prykhodko.util.queries.SQLOrderCommands;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.util.queries.SQLOrderCommands.GET_ACTIVE_ORDER_BY_ID;


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
        order.setSumOrder(rs.getInt(SUM_ORDER));
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

    @Override
    public void update(Order order) {
        PreparedStatementSetter setter = ps -> {
            int i = 1;
            ps.setBoolean(i++, order.isOpened());
            ps.setBoolean(i++, order.isStarted());
            ps.setInt(i, order.getSumOrder());
        };

        jdbcTemplate.update(SQLOrderCommands.UPDATE, setter);
    }

    @Override
    public Order getActiveOrderByID(Integer id) {
        try {
            return jdbcTemplate.queryForObject(GET_ACTIVE_ORDER_BY_ID, rowMapper, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

}
