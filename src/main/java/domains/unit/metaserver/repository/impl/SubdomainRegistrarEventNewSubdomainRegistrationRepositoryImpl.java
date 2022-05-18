package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainRegistrarEventNewSubdomainRegistration;
import domains.unit.metaserver.repository.SubdomainRegistrarEventNewSubdomainRegistrationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class SubdomainRegistrarEventNewSubdomainRegistrationRepositoryImpl implements
                                                                           SubdomainRegistrarEventNewSubdomainRegistrationRepository {
    private final JdbcTemplate jdbcTemplate;

    public SubdomainRegistrarEventNewSubdomainRegistrationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM subdomain_registrar_event_new_subdomain_registration 
                                                WHERE pk_id=?
                                                """,
                                        new Object[]{pkId},
                                        new int[]{Types.CHAR},
                                        Integer.class) != 1)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM subdomain_registrar_event_new_subdomain_registration
                                                   WHERE pk_id=?""",
                                           new Object[]{pkId},
                                           new int[]{Types.CHAR},
                                           new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getByLabel(int networkId,
                                                                      String label) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM subdomain_registrar_event_new_subdomain_registration 
                                                WHERE network_id=? AND label=?
                                                """,
                                        new Object[]{networkId, label},
                                        new int[]{Types.INTEGER, Types.CHAR},
                                        Integer.class) != 1)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM subdomain_registrar_event_new_subdomain_registration
                                                   WHERE network_id=? AND label=? 
                                                   """,
                                           new Object[]{networkId, label},
                                           new int[]{Types.INTEGER, Types.CHAR},
                                           new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getBySubNodeLabel(int networkId,
                                                                             String subNodeLabel) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM subdomain_registrar_event_new_subdomain_registration 
                                                WHERE network_id=? AND sub_node_label=?
                                                """,
                                        new Object[]{networkId, subNodeLabel},
                                        new int[]{Types.INTEGER, Types.CHAR},
                                        Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM subdomain_registrar_event_new_subdomain_registration
                                                   WHERE network_id=? AND sub_node_label=?
                                                   """,
                                           new Object[]{networkId, subNodeLabel},
                                           new int[]{Types.INTEGER, Types.CHAR},
                                           new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }


    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM subdomain_registrar_event_new_subdomain_registration
                                                   WHERE network_id=?
                                                   """,
                                           new Object[]{networkId},
                                           new int[]{Types.INTEGER},
                                           Integer.class);
    }

    /**
     * Gets the specified page data
     *
     * @param networkId
     * @param pageNo    page number, starting at 1
     * @param pageSize  records per page
     */
    private List<SubdomainRegistrarEventNewSubdomainRegistration> getPageQuery(int networkId,
                                                                               String label,
                                                                               int pageNo,
                                                                               int pageSize) {

        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM subdomain_registrar_event_new_subdomain_registration
                                          WHERE network_id=? AND label=? 
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, label, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.CHAR, Types.INTEGER, Types.INTEGER},
                                  new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    /**
     * Gets the specified page data
     *
     * @param networkId
     * @param pageNo    page number, starting at 1
     * @param pageSize  records per page
     */
    @Override
    public Page<SubdomainRegistrarEventNewSubdomainRegistration> getPage(int networkId,
                                                                         String label,
                                                                         int pageNo,
                                                                         int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<SubdomainRegistrarEventNewSubdomainRegistration> resultData = getPageQuery(networkId,
                                                                                        label,
                                                                                        pageNo - 1,
                                                                                        pageSize);
        return new Page<>(0,
                          totalCount,
                          (int) totalCount,
                          resultData);
    }

    @Override
    public List<SubdomainRegistrarEventNewSubdomainRegistration> getListByOwner(int networkId,
                                                                                String owner) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM subdomain_registrar_event_new_subdomain_registration
                                          WHERE network_id=? AND owner=?                     
                                          """,
                                  new Object[]{networkId, owner},
                                  new int[]{Types.INTEGER, Types.CHAR},
                                  new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    /**
     * RowMapper
     */
    private static final class SubdomainRegistrarEventNewSubdomainRegistrationMapper
            implements RowMapper<SubdomainRegistrarEventNewSubdomainRegistration> {
        @Override
        public SubdomainRegistrarEventNewSubdomainRegistration mapRow(ResultSet rs,
                                                                      int rowNum) throws SQLException {
            SubdomainRegistrarEventNewSubdomainRegistration subdomainRegistrarEventNewSubdomainRegistration =
                    new SubdomainRegistrarEventNewSubdomainRegistration();
            subdomainRegistrarEventNewSubdomainRegistration.setPkId(rs.getString("pk_id"));
            subdomainRegistrarEventNewSubdomainRegistration.setLabel(rs.getString("label"));
            subdomainRegistrarEventNewSubdomainRegistration.setSubDomain(rs.getString("sub_domain"));
            subdomainRegistrarEventNewSubdomainRegistration.setSubNodeLabel(rs.getString("sub_node_label"));
            subdomainRegistrarEventNewSubdomainRegistration.setOwner(rs.getString("owner"));
            subdomainRegistrarEventNewSubdomainRegistration.setTimestamp(rs.getDate("timestamp"));
            subdomainRegistrarEventNewSubdomainRegistration.setOpTime(rs.getDate("op_time"));
            subdomainRegistrarEventNewSubdomainRegistration.setNetworkId(rs.getInt("network_id"));
            return subdomainRegistrarEventNewSubdomainRegistration;
        }
    }

}