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


    /**
     * 根据pkId得到记录
     */
    @Override
    public BaseRegistrarEventNameRegistered getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                select count(*) 
                FROM base_registrar_event_name_registered 
                WHERE pk_id=?
                """, new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0) return null;
        return jdbcTemplate.queryForObject("""
                        select * 
                        FROM base_registrar_event_name_registered 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, new BaseRegistrarEventNameRegisteredMapper());
    }

    @Override
    public BaseRegistrarEventNameRegistered getById(String id) {
        if (jdbcTemplate.queryForObject("""
                select count(*) 
                from base_registrar_event_name_registered 
                WHERE id=?
                """, new Object[]{id}, new int[]{Types.CHAR}, Integer.class) == 0) return null;
        return jdbcTemplate.queryForObject("""
                        select * 
                        FROM base_registrar_event_name_registered 
                        WHERE id=?
                        """, new Object[]{id}, new int[]{Types.CHAR},
                new BaseRegistrarEventNameRegisteredMapper());

    }

    /**
     * getCount
     */
    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) FROM base_registrar_event_name_registered",
                Integer.class);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */

    private List<BaseRegistrarEventNameRegistered> getPageQuery(int pageNo, int pageSize) {
        return jdbcTemplate.query("select * FROM base_registrar_event_name_registered limit ?,?",
                new Object[]{pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER},
                new BaseRegistrarEventNameRegisteredMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BaseRegistrarEventNameRegistered> getPage(int pageNo, int pageSize) {
        long totalCount = getCount();
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<BaseRegistrarEventNameRegistered> resultData = getPageQuery(pageNo - 1, pageSize);
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
            baseRegistrarEventNameRegistered.setId(rs.getString("id"));
            baseRegistrarEventNameRegistered.setOwner(rs.getString("owner"));
            baseRegistrarEventNameRegistered.setExpires(BigInteger.valueOf(rs.getLong("expires")));
            baseRegistrarEventNameRegistered.setTimestamp(rs.getDate("timestamp"));
            baseRegistrarEventNameRegistered.setOpTime(rs.getDate("op_time"));
            return baseRegistrarEventNameRegistered;
        }
    }

}