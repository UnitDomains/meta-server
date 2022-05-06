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


    /**
     * 根据pkId得到记录
     */
    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM subdomain_registrar_event_new_subdomain_registration 
                        WHERE pk_id=?
                        """,
                new Object[]{pkId},
                new int[]{Types.CHAR}, Integer.class) != 1)
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
    public SubdomainRegistrarEventNewSubdomainRegistration getByLabel(String label) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM subdomain_registrar_event_new_subdomain_registration 
                        WHERE label=?
                        """,
                new Object[]{label},
                new int[]{Types.CHAR}, Integer.class) != 1)
            return null;

        return jdbcTemplate.queryForObject("""
                        SELECT * 
                        FROM subdomain_registrar_event_new_subdomain_registration
                        WHERE label=?""",
                new Object[]{label},
                new int[]{Types.CHAR},
                new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getBySubNodeLabel(String subNodeLabel) {
        if (jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM subdomain_registrar_event_new_subdomain_registration 
                        WHERE sub_node_label=?
                        """,
                new Object[]{subNodeLabel},
                new int[]{Types.CHAR}, Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                        SELECT * 
                        FROM subdomain_registrar_event_new_subdomain_registration
                        WHERE sub_node_label=?""",
                new Object[]{subNodeLabel},
                new int[]{Types.CHAR},
                new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("""
                        SELECT count(*) 
                        FROM subdomain_registrar_event_new_subdomain_registration
                        """,
                Integer.class);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */

    private List<SubdomainRegistrarEventNewSubdomainRegistration> getPageQuery(String label, int pageNo, int pageSize) {

        return jdbcTemplate.query("""
                        SELECT * 
                        FROM subdomain_registrar_event_new_subdomain_registration
                        WHERE label=?
                        limit ?,?
                        """,
                new Object[]{label, pageNo * pageSize, pageSize},
                new int[]{Types.CHAR, Types.INTEGER, Types.INTEGER},
                new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<SubdomainRegistrarEventNewSubdomainRegistration> getPage(String label, int pageNo, int pageSize) {
        long totalCount = getCount();
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<SubdomainRegistrarEventNewSubdomainRegistration> resultData = getPageQuery(label, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    @Override
    public List<SubdomainRegistrarEventNewSubdomainRegistration> getListByOwner(String owner) {
        return jdbcTemplate.query("""
                        SELECT * 
                        FROM subdomain_registrar_event_new_subdomain_registration
                        WHERE owner=?                     
                        """,
                new Object[]{owner},
                new int[]{Types.CHAR},
                new SubdomainRegistrarEventNewSubdomainRegistrationMapper());
    }

    /**
     * RowMapper
     */
    private static final class SubdomainRegistrarEventNewSubdomainRegistrationMapper implements RowMapper<SubdomainRegistrarEventNewSubdomainRegistration> {
        @Override
        public SubdomainRegistrarEventNewSubdomainRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
            SubdomainRegistrarEventNewSubdomainRegistration subdomainRegistrarEventNewSubdomainRegistration = new SubdomainRegistrarEventNewSubdomainRegistration();
            subdomainRegistrarEventNewSubdomainRegistration.setPkId(rs.getString("pk_id"));
            subdomainRegistrarEventNewSubdomainRegistration.setLabel(rs.getString("label"));
            subdomainRegistrarEventNewSubdomainRegistration.setSubDomain(rs.getString("sub_domain"));
            subdomainRegistrarEventNewSubdomainRegistration.setSubNodeLabel(rs.getString("sub_node_label"));
            subdomainRegistrarEventNewSubdomainRegistration.setOwner(rs.getString("owner"));
            subdomainRegistrarEventNewSubdomainRegistration.setTimestamp(rs.getDate("timestamp"));
            subdomainRegistrarEventNewSubdomainRegistration.setOpTime(rs.getDate("op_time"));
            return subdomainRegistrarEventNewSubdomainRegistration;
        }
    }

}