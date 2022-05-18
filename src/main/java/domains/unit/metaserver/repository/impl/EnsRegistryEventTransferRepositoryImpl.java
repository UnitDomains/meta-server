package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.EnsRegistryEventTransfer;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EnsRegistryEventTransferRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class EnsRegistryEventTransferRepositoryImpl implements EnsRegistryEventTransferRepository {
    private final JdbcTemplate jdbcTemplate;

    public EnsRegistryEventTransferRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EnsRegistryEventTransfer getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM ens_registry_event_transfer 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0)
            return null;
        return jdbcTemplate.queryForObject("""
                        SELECT * 
                        FROM ens_registry_event_transfer 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, new EnsRegistryEventTransferMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM ens_registry_event_transfer 
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

    private List<EnsRegistryEventTransfer> getPageQuery(int networkId, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        SELECT * 
                        FROM ens_registry_event_transfer 
                        WHERE network_id=? limit ?,?
                        """,
                new Object[]{networkId, pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                new EnsRegistryEventTransferMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<EnsRegistryEventTransfer> getPage(int networkId, int pageNo, int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<EnsRegistryEventTransfer> resultData = getPageQuery(networkId, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class EnsRegistryEventTransferMapper implements RowMapper<EnsRegistryEventTransfer> {
        @Override
        public EnsRegistryEventTransfer mapRow(ResultSet rs, int rowNum) throws SQLException {
            EnsRegistryEventTransfer ensRegistryEventTransfer = new EnsRegistryEventTransfer();
            ensRegistryEventTransfer.setPkId(rs.getString("pk_id"));
            ensRegistryEventTransfer.setNetworkId(rs.getInt("network_id"));
            ensRegistryEventTransfer.setNode(rs.getString("node"));
            ensRegistryEventTransfer.setOwner(rs.getString("owner"));
            ensRegistryEventTransfer.setTimestamp(rs.getDate("timestamp"));
            ensRegistryEventTransfer.setOpTime(rs.getDate("op_time"));
            return ensRegistryEventTransfer;
        }
    }

}