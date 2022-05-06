package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.BaseRegistrarEventTransfer;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BaseRegistrarEventTransferRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class BaseRegistrarEventTransferRepositoryImpl implements BaseRegistrarEventTransferRepository {
    private final JdbcTemplate jdbcTemplate;

    public BaseRegistrarEventTransferRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 根据pkId得到记录
     */
    @Override
    public BaseRegistrarEventTransfer getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("select count(*) from base_registrar_event_transfer WHERE pk_id=?",
                new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0)
            return null;
        return jdbcTemplate.queryForObject("select * from base_registrar_event_transfer WHERE pk_id=?",
                new Object[]{pkId}, new int[]{Types.CHAR}, new BaseRegistrarEventTransferMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from base_registrar_event_transfer", Integer.class);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */

    private List<BaseRegistrarEventTransfer> getPageQuery(int pageNo, int pageSize) {
        return jdbcTemplate.query("select * from base_registrar_event_transfer limit ?,?",
                new Object[]{pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER},
                new BaseRegistrarEventTransferMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BaseRegistrarEventTransfer> getPage(int pageNo, int pageSize) {
        long totalCount = getCount();
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<BaseRegistrarEventTransfer> resultData = getPageQuery(pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class BaseRegistrarEventTransferMapper implements RowMapper<BaseRegistrarEventTransfer> {
        @Override
        public BaseRegistrarEventTransfer mapRow(ResultSet rs, int rowNum) throws SQLException {
            BaseRegistrarEventTransfer baseRegistrarEventTransfer = new BaseRegistrarEventTransfer();
            baseRegistrarEventTransfer.setPkId(rs.getString("pk_id"));
            baseRegistrarEventTransfer.setFromAddr(rs.getString("from_addr"));
            baseRegistrarEventTransfer.setToAddr(rs.getString("to_addr"));
            baseRegistrarEventTransfer.setTokenId(rs.getString("tokenId"));
            baseRegistrarEventTransfer.setTimestamp(rs.getDate("timestamp"));
            baseRegistrarEventTransfer.setOpTime(rs.getDate("op_time"));
            return baseRegistrarEventTransfer;
        }
    }

}