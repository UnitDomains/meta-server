package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.EnsRegistryEventNewOwner;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EnsRegistryEventNewOwnerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class EnsRegistryEventNewOwnerRepositoryImpl implements EnsRegistryEventNewOwnerRepository {
    private final JdbcTemplate jdbcTemplate;

    public EnsRegistryEventNewOwnerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EnsRegistryEventNewOwner getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM ens_registry_event_new_owner 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, Integer.class) == 0)
            return null;
        return jdbcTemplate.queryForObject("""
                        SELECT * 
                        FROM ens_registry_event_new_owner 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId}, new int[]{Types.CHAR}, new EnsRegistryEventNewOwnerMapper());
    }

    /**
     * getCount
     *
     * @param address
     */
    @Override
    public int getCount(int networkId, String address) {
        return jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM ens_registry_event_new_owner 
                        WHERE network_id=? AND owner=?
                        """,
                new Object[]{networkId, address},
                new int[]{Types.INTEGER, Types.CHAR},
                Integer.class);
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */

    private List<EnsRegistryEventNewOwner> getPageQuery(int networkId, String address, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        SELECT * 
                        FROM ens_registry_event_new_owner 
                        WHERE network_id=? 
                        AND owner=? limit ?,?
                        """,
                new Object[]{networkId, address, pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER, Types.INTEGER},
                new EnsRegistryEventNewOwnerMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<EnsRegistryEventNewOwner> getPage(int networkId, String address, int pageNo, int pageSize) {
        long totalCount = getCount(networkId, address);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<EnsRegistryEventNewOwner> resultData = getPageQuery(networkId, address, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class EnsRegistryEventNewOwnerMapper implements RowMapper<EnsRegistryEventNewOwner> {
        @Override
        public EnsRegistryEventNewOwner mapRow(ResultSet rs, int rowNum) throws SQLException {
            EnsRegistryEventNewOwner ensRegistryEventNewOwner = new EnsRegistryEventNewOwner();
            ensRegistryEventNewOwner.setPkId(rs.getString("pk_id"));
            ensRegistryEventNewOwner.setNetworkId(rs.getInt("network_id"));
            ensRegistryEventNewOwner.setNode(rs.getString("node"));
            ensRegistryEventNewOwner.setLabel(rs.getString("label"));
            ensRegistryEventNewOwner.setOwner(rs.getString("owner"));
            ensRegistryEventNewOwner.setTimestamp(rs.getDate("timestamp"));
            ensRegistryEventNewOwner.setOpTime(rs.getDate("op_time"));
            return ensRegistryEventNewOwner;
        }
    }

}