package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRegistered;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventNameRegisteredRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class EthRegistrarControllerEventNameRegisteredRepositoryImpl implements
        EthRegistrarControllerEventNameRegisteredRepository {
    private final JdbcTemplate jdbcTemplate;

    public EthRegistrarControllerEventNameRegisteredRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EthRegistrarControllerEventNameRegistered getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                        select count(*) 
                        from eth_registrar_controller_event_name_registered 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId},
                new int[]{Types.CHAR},
                Integer.class) == 0)
            return null;
        return jdbcTemplate.queryForObject("""
                        select * from eth_registrar_controller_event_name_registered 
                        WHERE pk_id=?""",
                new Object[]{pkId},
                new int[]{Types.CHAR},
                new EthRegistrarControllerEventNameRegisteredMapper());
    }


    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("""
                        select count(*) 
                        from eth_registrar_controller_event_name_registered
                        """,
                Integer.class);
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */

    private List<EthRegistrarControllerEventNameRegistered> getPageQuery(int pageNo, int pageSize) {
        return jdbcTemplate.query("select * from eth_registrar_controller_event_name_registered limit ?,?",
                new Object[]{pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER},
                new EthRegistrarControllerEventNameRegisteredMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<EthRegistrarControllerEventNameRegistered> getPage(int pageNo, int pageSize) {
        long totalCount = getCount();
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<EthRegistrarControllerEventNameRegistered> resultData = getPageQuery(pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class EthRegistrarControllerEventNameRegisteredMapper implements
            RowMapper<EthRegistrarControllerEventNameRegistered> {
        @Override
        public EthRegistrarControllerEventNameRegistered mapRow(ResultSet rs, int rowNum) throws SQLException {
            EthRegistrarControllerEventNameRegistered ethRegistrarControllerEventNameRegistered =
                    new EthRegistrarControllerEventNameRegistered();
            ethRegistrarControllerEventNameRegistered.setPkId(rs.getString("pk_id"));
            ethRegistrarControllerEventNameRegistered.setNetworkId(rs.getInt("network_id"));
            ethRegistrarControllerEventNameRegistered.setLabel(rs.getString("label"));
            ethRegistrarControllerEventNameRegistered.setOwner(rs.getString("owner"));
            ethRegistrarControllerEventNameRegistered.setCost(BigInteger.valueOf(rs.getLong("cost")));
            ethRegistrarControllerEventNameRegistered.setExpires(BigInteger.valueOf(rs.getLong("expires")));
            ethRegistrarControllerEventNameRegistered.setBaseNodeIndex(rs.getInt("baseNodeIndex"));
            ethRegistrarControllerEventNameRegistered.setTimestamp(rs.getDate("timestamp"));
            ethRegistrarControllerEventNameRegistered.setOpTime(rs.getDate("op_time"));
            return ethRegistrarControllerEventNameRegistered;
        }
    }

}