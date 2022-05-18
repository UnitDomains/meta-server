package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainInfo;
import domains.unit.metaserver.repository.SubdomainInfoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class SubdomainInfoRepositoryImpl implements SubdomainInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    public SubdomainInfoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getCount(int networkId,
                        String label) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM subdomain_registrar_event_new_subdomain_registration
                                                   WHERE network_id=? and label=?
                                                   """,
                                           new Object[]{networkId, label},
                                           new int[]{Types.INTEGER, Types.CHAR},
                                           Integer.class);
    }

    private List<SubdomainInfo> getPageQuery(int networkId,
                                             String label,
                                             int pageNo,
                                             int pageSize) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM subdomain_info
                                          WHERE network_id=? and label=?
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, label, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER, Types.INTEGER},
                                  new SubdomainInfoMapper());
    }

    @Override
    public Page<SubdomainInfo> getPage(int networkId,
                                       String label,
                                       int pageNo,
                                       int pageSize) {
        long totalCount = getCount(networkId,
                                   label);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<SubdomainInfo> resultData = getPageQuery(networkId,
                                                      label,
                                                      pageNo - 1,
                                                      pageSize);
        return new Page<>(0,
                          totalCount,
                          (int) totalCount,
                          resultData);
    }

    @Override
    public List<SubdomainInfo> getListByOwner(int networkId,
                                              String owner) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM subdomain_info
                                          WHERE network_id=? AND owner=?                     
                                          """,
                                  new Object[]{networkId, owner},
                                  new int[]{Types.INTEGER, Types.CHAR},
                                  new SubdomainInfoMapper());
    }

    @Override
    public SubdomainInfo getBySubNodeLabel(int networkId,
                                           String subNodeLabel) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM subdomain_info 
                                                WHERE network_id=? AND sub_node_label=?
                                                """,
                                        new Object[]{networkId, subNodeLabel},
                                        new int[]{Types.INTEGER, Types.CHAR},
                                        Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM subdomain_info
                                                   WHERE network_id=? AND sub_node_label=?
                                                   """,
                                           new Object[]{networkId, subNodeLabel},
                                           new int[]{Types.INTEGER, Types.CHAR},
                                           new SubdomainInfoMapper());
    }

    /**
     * RowMapper
     */
    private static final class SubdomainInfoMapper implements RowMapper<SubdomainInfo> {
        @Override
        public SubdomainInfo mapRow(ResultSet rs,
                                    int rowNum) throws SQLException {
            SubdomainInfo subdomainInfo = new SubdomainInfo();
            subdomainInfo.setPkId(rs.getString("pk_id"));
            subdomainInfo.setNetworkId(rs.getInt("network_id"));
            subdomainInfo.setLabel(rs.getString("label"));
            subdomainInfo.setSubNodeLabel(rs.getString("sub_node_label"));
            subdomainInfo.setOpTime(rs.getDate("op_time"));
            return subdomainInfo;
        }
    }

}