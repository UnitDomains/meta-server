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


    /**
     * 根据pkId得到记录
     */
    @Override
    public BaseRegistrarEventNameRenewed getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("select count(*) from base_registrar_event_name_renewed WHERE pk_id=?",
                new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("select * from base_registrar_event_name_renewed WHERE pk_id=?",
                new Object[]{pkId}, new int[]{Types.CHAR}, new BaseRegistrarEventNameRenewedMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from base_registrar_event_name_renewed", Integer.class);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */

    private List<BaseRegistrarEventNameRenewed> getPageQuery(int pageNo, int pageSize) {
        return jdbcTemplate.query("select * from base_registrar_event_name_renewed limit ?,?",
                new Object[]{pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER},
                new BaseRegistrarEventNameRenewedMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BaseRegistrarEventNameRenewed> getPage(int pageNo, int pageSize) {
        long totalCount = getCount();
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<BaseRegistrarEventNameRenewed> resultData = getPageQuery(pageNo - 1, pageSize);
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
            baseRegistrarEventNameRenewed.setId(rs.getString("id"));
            baseRegistrarEventNameRenewed.setExpires(BigInteger.valueOf(rs.getLong("expires")));
            baseRegistrarEventNameRenewed.setTimestamp(rs.getDate("timestamp"));
            baseRegistrarEventNameRenewed.setOpTime(rs.getDate("op_time"));
            return baseRegistrarEventNameRenewed;
        }
    }

}