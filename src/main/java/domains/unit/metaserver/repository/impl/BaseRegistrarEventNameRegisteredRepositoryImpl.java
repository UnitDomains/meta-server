package domains.unit.metaserver.repository.impl;


import domains.unit.metaserver.model.BaseRegistrarEventNameRegistered;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BaseRegistrarEventNameRegisteredRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class BaseRegistrarEventNameRegisteredRepositoryImpl implements BaseRegistrarEventNameRegisteredRepository {
    private final JdbcTemplate jdbcTemplate;

    public BaseRegistrarEventNameRegisteredRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public BaseRegistrarEventNameRegistered getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM base_registrar_event_name_registered 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0) return null;

        return jdbcTemplate.queryForObject("""
                        SELECT * 
                        FROM base_registrar_event_name_registered 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, new BaseRegistrarEventNameRegisteredMapper());
    }

    @Override
    public BaseRegistrarEventNameRegistered getById(int networkId, String id) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM base_registrar_event_name_registered 
                        WHERE networkId=? AND id=?
                        """,
                new Object[]{networkId, id},
                new int[]{Types.INTEGER, Types.CHAR}, Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                        SELECT * 
                        FROM base_registrar_event_name_registered 
                        WHERE network_id=? AND id=?
                        """,
                new Object[]{networkId, id},
                new int[]{Types.INTEGER, Types.CHAR},
                new BaseRegistrarEventNameRegisteredMapper());

    }

    /**
     * getCount
     */
    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM base_registrar_event_name_registered 
                        WHERE network_id=?
                        """,
                new Object[]{networkId}, new int[]{Types.INTEGER},
                Integer.class);
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */

    private List<BaseRegistrarEventNameRegistered> getPageQuery(int networkId, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        SELECT * 
                        FROM base_registrar_event_name_registered 
                        WHERE network_id=? limit ?,?
                        """,
                new Object[]{networkId, pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                new BaseRegistrarEventNameRegisteredMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<BaseRegistrarEventNameRegistered> getPage(int networkId, int pageNo, int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<BaseRegistrarEventNameRegistered> resultData = getPageQuery(networkId, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class BaseRegistrarEventNameRegisteredMapper implements RowMapper<BaseRegistrarEventNameRegistered> {
        @Override
        public BaseRegistrarEventNameRegistered mapRow(ResultSet rs, int rowNum) throws SQLException {
            BaseRegistrarEventNameRegistered baseRegistrarEventNameRegistered = new BaseRegistrarEventNameRegistered();
            baseRegistrarEventNameRegistered.setPkId(rs.getString("pk_id"));
            baseRegistrarEventNameRegistered.setNetworkId(rs.getInt("network_id"));
            baseRegistrarEventNameRegistered.setId(rs.getString("id"));
            baseRegistrarEventNameRegistered.setOwner(rs.getString("owner"));
            baseRegistrarEventNameRegistered.setExpires(BigInteger.valueOf(rs.getLong("expires")));
            baseRegistrarEventNameRegistered.setTimestamp(rs.getDate("timestamp"));
            baseRegistrarEventNameRegistered.setOpTime(rs.getDate("op_time"));
            return baseRegistrarEventNameRegistered;
        }
    }

}