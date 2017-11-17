package com.sergey.prykhodko.dao.implementations;

import com.sergey.prykhodko.dao.LinkDAO;
import com.sergey.prykhodko.model.order.suborder.Link;
import com.sergey.prykhodko.util.ClassName;
import com.sergey.prykhodko.util.DataSources;
import com.sergey.prykhodko.util.PasswordEncoder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

import static com.sergey.prykhodko.util.queries.SQLLinkCommands.INSERT;

public class LinkMySQLDAO implements LinkDAO {
    private static final String ID = "id_link";
    private static final String LINK = "link";
    private static final String ITEM_AMOUNT = "item_amount";
    private static final String SUM = "sum";
    private static final String ID_SUBORDER = "id_suborder";

    private final static Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public LinkMySQLDAO() {
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
    public void add(Link link) {
        jdbcTemplate.update(INSERT, getAddPreparedStatementSetter(link));
        logger.info("Link '" + link.getLinkString() + "' is stored in DB");
    }

    private PreparedStatementSetter getAddPreparedStatementSetter(Link link) {
        return ps -> {
            int i = 1;
            ps.setString(i++, link.getLinkString());
            ps.setInt(i++,link.getItemAmount());
            ps.setInt(i++, link.getItemPrice());
            ps.setInt(i, link.getSubOrderId());
        };
    }
}
