package com.sergey.prykhodko.dao.implementations;

import com.sergey.prykhodko.dao.SubOrderDAO;
import com.sergey.prykhodko.model.order.suborder.SubOrder;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.DataSources;
import com.sergey.prykhodko.util.queries.SQLOrderCommands;
import com.sergey.prykhodko.util.queries.SQLSubOrderCommands;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static com.sergey.prykhodko.util.queries.SQLSubOrderCommands.GET_LAST_ID;
import static com.sergey.prykhodko.util.queries.SQLSubOrderCommands.GET_SUBORDERS_BY_ORDER_ID;
import static com.sergey.prykhodko.util.queries.SQLSubOrderCommands.INSERT;

public class SubOrderMySQLDAO implements SubOrderDAO {
    private static final String ID = "id_suborder";
    private static final String ID_ORDER = "id_order";
    private static final String ID_USER = "id_user";
    private static final String SUM_SUBORDER = "sum_suborder";
    private static final String IS_PAID = "is_paid";

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public SubOrderMySQLDAO() {
        DataSource dataSource = null;
        try {
            dataSource = DataSources.MY_SQL.getDataSource();
        } catch (SQLException e) {
            logger.error(e);
        }
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<SubOrder> rowMapper = (rs, rowNum) -> {
        SubOrder subOrder = new SubOrder();
        subOrder.setId(rs.getInt(ID));
        subOrder.setOrderId(rs.getInt(ID_ORDER));
        subOrder.setOwnerId((rs.getInt(ID_USER)));
        subOrder.setPaid(rs.getBoolean(IS_PAID));
        subOrder.setSumSubOrder(rs.getInt(SUM_SUBORDER));
        return subOrder;
    };

    @Override
    public void addSubOrder(SubOrder subOrder) {
        jdbcTemplate.update(INSERT, getAddPreparedStatementSetter(subOrder));
        logger.info("SubOrder with links " + subOrder.getLinks() + "is stored in DB");
    }

    @Override
    public Integer getLastId() {
        return jdbcTemplate.queryForObject(GET_LAST_ID, Integer.class);
    }

    @Override
    public List<SubOrder> getSubOrdersByOrderId(Integer orderId) {
        PreparedStatementSetter setter = ps -> {
            ps.setInt(1, orderId);
        };
        return jdbcTemplate.query(GET_SUBORDERS_BY_ORDER_ID, setter, rowMapper);
    }

    @Override
    public void update(SubOrder subOrder) {
        PreparedStatementSetter setter = ps -> {
            int i = 1;
            ps.setInt(i++, subOrder.getSumSubOrder());
            ps.setBoolean(i++, subOrder.isPaid());
            ps.setInt(i, subOrder.getId());
        };

        jdbcTemplate.update(SQLSubOrderCommands.UPDATE, setter);
    }

    private PreparedStatementSetter getAddPreparedStatementSetter(SubOrder subOrder) {
            return ps -> {
                int i = 1;
                ps.setInt(i++, subOrder.getOrderId());
                ps.setInt(i++, subOrder.getOwnerId());
                ps.setInt(i++, subOrder.getSumSubOrder());
                ps.setBoolean(i, subOrder.isPaid());
            };
    }
}
