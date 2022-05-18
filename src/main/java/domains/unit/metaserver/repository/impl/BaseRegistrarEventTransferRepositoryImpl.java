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


    @Override
    public BaseRegistrarEventTransfer getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("SELECT count(*) FROM base_registrar_event_transfer WHERE pk_id=?",
                new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0)
            return null;
        return jdbcTemplate.queryForObject("SELECT * FROM base_registrar_event_transfer WHERE pk_id=?",
                new Object[]{pkId}, new int[]{Types.CHAR}, new BaseRegistrarEventTransferMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM base_registrar_event_transfer WHERE network_id=?",
                new Object[]{networkId}, new int[]{Types.INTEGER},
                Integer.class);
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */

    private List<BaseRegistrarEventTransfer> getPageQuery(int networkId, int pageNo, int pageSize) {
        return jdbcTemplate.query("SELECT * FROM base_registrar_event_transfer WHERE network_id=? limit ?,?",
                new Object[]{networkId, pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                new BaseRegistrarEventTransferMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<BaseRegistrarEventTransfer> getPage(int networkId, int pageNo, int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<BaseRegistrarEventTransfer> resultData = getPageQuery(networkId, pageNo - 1, pageSize);
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
            baseRegistrarEventTransfer.setNetworkId(rs.getInt("network_id"));
            baseRegistrarEventTransfer.setFromAddr(rs.getString("from_addr"));
            baseRegistrarEventTransfer.setToAddr(rs.getString("to_addr"));
            baseRegistrarEventTransfer.setTokenId(rs.getString("tokenId"));
            baseRegistrarEventTransfer.setTimestamp(rs.getDate("timestamp"));
            baseRegistrarEventTransfer.setOpTime(rs.getDate("op_time"));
            return baseRegistrarEventTransfer;
        }
    }

}