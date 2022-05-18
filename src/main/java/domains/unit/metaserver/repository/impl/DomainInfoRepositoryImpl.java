package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.DomainInfo;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.DomainInfoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class DomainInfoRepositoryImpl implements DomainInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    public DomainInfoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public DomainInfo getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM domain_info 
                                                WHERE pk_id=?
                                                """,
                                        new Object[]{pkId},
                                        new int[]{Types.CHAR},
                                        Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM domain_info
                                                   WHERE pk_id=?""",
                                           new Object[]{pkId},
                                           new int[]{Types.CHAR},
                                           new DomainInfoMapper());
    }


    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM domain_info
                                                   WHERE network_id=?
                                                   """,
                                           new Object[]{networkId},
                                           new int[]{Types.CHAR},
                                           Integer.class);
    }


    private List<DomainInfo> getPageQuery(int networkId,
                                          int pageNo,
                                          int pageSize) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM domain_info
                                          WHERE network_id=?
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                                  new DomainInfoMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param pageNo   page number, starting at 1
     * @param pageSize records per page
     */
    @Override
    public Page<DomainInfo> getPage(int networkId,
                                    int pageNo,
                                    int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<DomainInfo> resultData = getPageQuery(networkId,
                                                   pageNo - 1,
                                                   pageSize);
        return new Page<>(0,
                          totalCount,
                          (int) totalCount,
                          resultData);
    }

    @Override
    public List<DomainInfo> getList(int networkId,
                                    String searchText) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM domain_info
                                          WHERE network_id=? AND name=?
                                          """,
                                  new Object[]{networkId, searchText},
                                  new int[]{Types.INTEGER, Types.CHAR},
                                  new DomainInfoMapper());
    }

    @Override
    public List<DomainInfo> getList(int networkId,
                                    String searchText,
                                    int baseNodeIndex) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM domain_info
                                          WHERE network_id=? AND name=? AND base_node_index=?
                                          """,
                                  new Object[]{networkId, searchText, baseNodeIndex},
                                  new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER},
                                  new DomainInfoMapper());
    }

    /**
     * RowMapper
     */
    private static final class DomainInfoMapper implements RowMapper<DomainInfo> {
        @Override
        public DomainInfo mapRow(ResultSet rs,
                                 int rowNum) throws SQLException {
            DomainInfo domainInfo = new DomainInfo();
            domainInfo.setPkId(rs.getString("pk_id"));
            domainInfo.setNetworkId(rs.getInt("network_id"));
            domainInfo.setLabel(rs.getString("label"));
            domainInfo.setName(rs.getString("name"));
            domainInfo.setBaseNodeIndex(rs.getInt("base_node_index"));
            domainInfo.setOwner(rs.getString("owner"));
            domainInfo.setExpires(BigInteger.valueOf(rs.getLong("expires")));
            domainInfo.setReverse(rs.getInt("reverse"));
            domainInfo.setOpTime(rs.getDate("op_time"));
            return domainInfo;
        }
    }

}