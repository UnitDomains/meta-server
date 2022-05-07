package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRenewed;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventNameRenewedRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class EthRegistrarControllerEventNameRenewedRepositoryImpl implements EthRegistrarControllerEventNameRenewedRepository {
    private final JdbcTemplate jdbcTemplate;

    public EthRegistrarControllerEventNameRenewedRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EthRegistrarControllerEventNameRenewed getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                        select count(*) 
                        from eth_registrar_controller_event_name_renewed
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0)
            return null;
        return jdbcTemplate.queryForObject("""
                        select * 
                        from eth_registrar_controller_event_name_renewed 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, new EthRegistrarControllerEventNameRenewedMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from eth_registrar_controller_event_name_renewed",
                Integer.class);
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */

    private List<EthRegistrarControllerEventNameRenewed> getPageQuery(int pageNo, int pageSize) {
        return jdbcTemplate.query("select * from eth_registrar_controller_event_name_renewed limit ?,?",
                new Object[]{pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER},
                new EthRegistrarControllerEventNameRenewedMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<EthRegistrarControllerEventNameRenewed> getPage(int pageNo, int pageSize) {
        long totalCount = getCount();
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<EthRegistrarControllerEventNameRenewed> resultData = getPageQuery(pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class EthRegistrarControllerEventNameRenewedMapper implements
            RowMapper<EthRegistrarControllerEventNameRenewed> {
        @Override
        public EthRegistrarControllerEventNameRenewed mapRow(ResultSet rs, int rowNum) throws SQLException {
            EthRegistrarControllerEventNameRenewed ethRegistrarControllerEventNameRenewed =
                    new EthRegistrarControllerEventNameRenewed();
            ethRegistrarControllerEventNameRenewed.setPkId(rs.getString("pk_id"));
            ethRegistrarControllerEventNameRenewed.setNetworkId(rs.getInt("network_id"));
            ethRegistrarControllerEventNameRenewed.setLabel(rs.getString("label"));
            ethRegistrarControllerEventNameRenewed.setCost(BigInteger.valueOf(rs.getLong("cost")));
            ethRegistrarControllerEventNameRenewed.setExpires(BigInteger.valueOf(rs.getLong("expires")));
            ethRegistrarControllerEventNameRenewed.setBaseNodeIndex(rs.getInt("baseNodeIndex"));
            ethRegistrarControllerEventNameRenewed.setTimestamp(rs.getDate("timestamp"));
            ethRegistrarControllerEventNameRenewed.setOpTime(rs.getDate("op_time"));
            return ethRegistrarControllerEventNameRenewed;
        }
    }

}