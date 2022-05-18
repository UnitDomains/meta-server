package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.BaseRegistrarEventNameRenewed;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BaseRegistrarEventNameRenewedRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class BaseRegistrarEventNameRenewedRepositoryImpl implements BaseRegistrarEventNameRenewedRepository {
    private final JdbcTemplate jdbcTemplate;

    public BaseRegistrarEventNameRenewedRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public BaseRegistrarEventNameRenewed getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM base_registrar_event_name_renewed 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId},
                new int[]{Types.CHAR},
                Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                        SELECT * 
                        FROM base_registrar_event_name_renewed 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId},
                new int[]{Types.CHAR},
                new BaseRegistrarEventNameRenewedMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM base_registrar_event_name_renewed 
                        WHERE network_id=?""",
                new Object[]{networkId}, new int[]{Types.INTEGER},
                Integer.class);
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */

    private List<BaseRegistrarEventNameRenewed> getPageQuery(int networkId, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        SELECT * 
                        FROM base_registrar_event_name_renewed 
                        WHERE network_id=? 
                        limit ?,?
                        """,
                new Object[]{networkId, pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                new BaseRegistrarEventNameRenewedMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<BaseRegistrarEventNameRenewed> getPage(int networkId, int pageNo, int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<BaseRegistrarEventNameRenewed> resultData = getPageQuery(networkId, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class BaseRegistrarEventNameRenewedMapper implements RowMapper<BaseRegistrarEventNameRenewed> {
        @Override
        public BaseRegistrarEventNameRenewed mapRow(ResultSet rs, int rowNum) throws SQLException {
            BaseRegistrarEventNameRenewed baseRegistrarEventNameRenewed = new BaseRegistrarEventNameRenewed();
            baseRegistrarEventNameRenewed.setPkId(rs.getString("pk_id"));
            baseRegistrarEventNameRenewed.setNetworkId(rs.getInt("network_id"));
            baseRegistrarEventNameRenewed.setId(rs.getString("id"));
            baseRegistrarEventNameRenewed.setExpires(BigInteger.valueOf(rs.getLong("expires")));
            baseRegistrarEventNameRenewed.setTimestamp(rs.getDate("timestamp"));
            baseRegistrarEventNameRenewed.setOpTime(rs.getDate("op_time"));
            return baseRegistrarEventNameRenewed;
        }
    }

}