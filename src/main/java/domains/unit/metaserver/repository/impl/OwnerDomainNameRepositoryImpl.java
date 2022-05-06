package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.OwnerDomainName;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.OwnerDomainNameRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class OwnerDomainNameRepositoryImpl implements OwnerDomainNameRepository {

    private final JdbcTemplate jdbcTemplate;

    public OwnerDomainNameRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private int getRegistrantDomainsCount(String address) {
        return jdbcTemplate.queryForObject("""
                        select count(*)
                        from base_registrar_event_transfer as A 
                        join  eth_registrar_controller_event_name_registered as B
                        on A.tokenId=B.label
                        where A.to_addr=?
                        """,
                new Object[]{address},
                new int[]{Types.CHAR},
                Integer.class);
    }

    private List<OwnerDomainName> getRegistrantDomainsPageQuery(String address, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        select B.name,B.label,B.baseNodeIndex
                        from base_registrar_event_transfer as A 
                        join eth_registrar_controller_event_name_registered as B
                        on A.tokenId=B.label
                        where A.to_addr=?
                        limit ?,?
                        """,
                new Object[]{address, pageNo * pageSize, pageSize},
                new int[]{Types.CHAR, Types.INTEGER, Types.INTEGER},
                new OwnerDomainNameMapper());
    }

    @Override
    public Page<OwnerDomainName> getRegistrantDomainsPage(String address, int pageNo, int pageSize) {

        long totalCount = getRegistrantDomainsCount(address);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<OwnerDomainName> resultData = getRegistrantDomainsPageQuery(address, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }


    private int getControllerDomainsCount(String address) {
        return jdbcTemplate.queryForObject("""
                        select count(*)
                        from ens_registry_event_transfer as A 
                        join  eth_registrar_controller_event_name_registered as B
                        on A.node=B.label
                        where A.owner=?
                        """,
                new Object[]{address},
                new int[]{Types.CHAR},
                Integer.class);
    }

    private List<OwnerDomainName> getControllerDomainsPageQuery(String address, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        select B.name,B.label,B.baseNodeIndex
                        from ens_registry_event_transfer as A 
                        join eth_registrar_controller_event_name_registered as B
                        on A.node=B.label
                        where A.owner=?
                        limit ?,?
                        """,
                new Object[]{address, pageNo * pageSize, pageSize},
                new int[]{Types.CHAR, Types.INTEGER, Types.INTEGER},
                new OwnerDomainNameMapper());
    }

    @Override
    public Page<OwnerDomainName> getControllerDomainsPage(String address, int pageNo, int pageSize) {
        long totalCount = getControllerDomainsCount(address);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<OwnerDomainName> resultData = getControllerDomainsPageQuery(address, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    @Override
    public List<OwnerDomainName> getReverseRecordDomains(String address) {
        return jdbcTemplate.query("""
                        select B.name,B.label,B.baseNodeIndex
                        from public_resolver_event_addr_changed as A 
                        join eth_registrar_controller_event_name_registered as B
                        on A.node=B.label
                        where A.addr=?                 
                        """,
                new Object[]{address},
                new int[]{Types.CHAR},
                new OwnerDomainNameMapper());
    }

    @Override
    public OwnerDomainName getOwnerDomainNameByLabel(String label) {
        if (jdbcTemplate.queryForObject("""
                        select count(*) 
                        from eth_registrar_controller_event_name_registered 
                        WHERE label=?
                        """,
                new Object[]{label},
                new int[]{Types.CHAR}, Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                        select name,label,baseNodeIndex 
                        from eth_registrar_controller_event_name_registered 
                        WHERE label=?
                        """, new Object[]{label},
                new int[]{Types.CHAR},
                new OwnerDomainNameMapper());

    }

    /**
     * RowMapper
     */
    private static final class OwnerDomainNameMapper implements RowMapper<OwnerDomainName> {
        @Override
        public OwnerDomainName mapRow(ResultSet rs, int rowNum) throws SQLException {
            OwnerDomainName ownerDomainName = new OwnerDomainName();
            ownerDomainName.setName(rs.getString("name"));
            ownerDomainName.setBaseNodeIndex(rs.getInt("baseNodeIndex"));
            ownerDomainName.setLabel(rs.getString("label"));

            return ownerDomainName;
        }
    }
}
