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
                                                   WHERE network_id=? AND name=?
                                                   """,
                                           new Object[]{pkId},
                                           new int[]{Types.CHAR},
                                           new DomainInfoMapper());
    }

    @Override
    public DomainInfo getDomain(int networkId,
                                String domain,
                                int baseNodeIndex) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM domain_info 
                                                WHERE network_id=? AND name=? AND base_node_index=?
                                                """,
                                        new Object[]{networkId, domain, baseNodeIndex},
                                        new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER},
                                        Integer.class) != 1)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM domain_info
                                                   WHERE network_id=? AND name=? AND base_node_index=?
                                                                                                      
                                                   """,
                                           new Object[]{networkId, domain, baseNodeIndex},
                                           new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER},
                                           new DomainInfoMapper());
    }

    @Override
    public DomainInfo getByLabel(int networkId,
                                 String label) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM domain_info 
                                                WHERE network_id=? AND label=?
                                                """,
                                        new Object[]{networkId, label},
                                        new int[]{Types.INTEGER, Types.CHAR},
                                        Integer.class) != 1)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM domain_info
                                                   WHERE network_id=? AND label=?                                                                                                      
                                                   """,
                                           new Object[]{networkId, label},
                                           new int[]{Types.INTEGER, Types.CHAR},
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


    public int getCountByController(int networkId,
                                    String controller) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM domain_info
                                                   WHERE network_id=? AND controller=?
                                                   """,
                                           new Object[]{networkId, controller},
                                           new int[]{Types.CHAR, Types.CHAR},
                                           Integer.class);
    }

    public int getCountByRegistrant(int networkId,
                                    String registrant) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM domain_info
                                                   WHERE network_id=? AND owner=?
                                                   """,
                                           new Object[]{networkId, registrant},
                                           new int[]{Types.CHAR, Types.CHAR},
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

    @Override
    public List<DomainInfo> getListByEthAddress(int networkId,
                                                String ethAddress) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM domain_info
                                          WHERE network_id=? AND eth_address=?
                                          """,
                                  new Object[]{networkId, ethAddress},
                                  new int[]{Types.INTEGER, Types.CHAR},
                                  new DomainInfoMapper());
    }

    private List<DomainInfo> getControllerDomainsList(int networkId,
                                                      String controllerAddress,
                                                      int pageNo,
                                                      int pageSize) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM domain_info
                                          WHERE network_id=? AND controller=?
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, controllerAddress, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER, Types.INTEGER},
                                  new DomainInfoMapper());
    }

    @Override
    public Page<DomainInfo> getControllerDomainsPage(int networkId,
                                                     String controllerAddress,
                                                     int pageNo,
                                                     int pageSize) {
        long totalCount = getCountByController(networkId,
                                               controllerAddress);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<DomainInfo> resultData = getControllerDomainsList(networkId,
                                                               controllerAddress,
                                                               pageNo - 1,
                                                               pageSize);
        return new Page<>(0,
                          totalCount,
                          (int) totalCount,
                          resultData);
    }

    private List<DomainInfo> getRegistrantDomainsList(int networkId,
                                                      String registrantAddress,
                                                      int pageNo,
                                                      int pageSize) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM domain_info
                                          WHERE network_id=? AND owner=?
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, registrantAddress, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER, Types.INTEGER},
                                  new DomainInfoMapper());
    }

    @Override
    public Page<DomainInfo> getRegistrantDomainsPage(int networkId,
                                                     String registrantAddress,
                                                     int pageNo,
                                                     int pageSize) {
        long totalCount = getCountByRegistrant(networkId,
                                               registrantAddress);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<DomainInfo> resultData = getRegistrantDomainsList(networkId,
                                                               registrantAddress,
                                                               pageNo - 1,
                                                               pageSize);
        return new Page<>(0,
                          totalCount,
                          (int) totalCount,
                          resultData);
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
            domainInfo.setController(rs.getString("controller"));
            domainInfo.setResolver(rs.getString("resolver"));
            domainInfo.setEthAddress(rs.getString("eth_address"));
            domainInfo.setContentHash(rs.getString("content_hash"));
            domainInfo.setRecord(rs.getString("record"));
            domainInfo.setExpires(BigInteger.valueOf(rs.getLong("expires")));

            domainInfo.setOpTime(rs.getDate("op_time"));
            return domainInfo;
        }
    }

}