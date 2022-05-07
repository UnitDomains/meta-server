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

    private int getRegistrantDomainsCount(int networkId, String address) {
        return jdbcTemplate.queryForObject("""
                        SELECT count(*)
                        FROM base_registrar_event_transfer AS A 
                        JOIN  eth_registrar_controller_event_name_registered AS B
                        ON A.tokenId=B.label
                        where A.network_id=? AND B.network_id=? AND A.to_addr=?
                        """,
                new Object[]{networkId, networkId, address},
                new int[]{Types.INTEGER, Types.INTEGER, Types.CHAR},
                Integer.class);
    }

    private List<OwnerDomainName> getRegistrantDomainsPageQuery(int networkId, String address, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        SELECT B.name,B.label,B.baseNodeIndex,B.network_id
                        FROM base_registrar_event_transfer AS A 
                        JOIN eth_registrar_controller_event_name_registered AS B
                        ON A.tokenId=B.label
                        where A.network_id=? AND B.network_id=? AND A.to_addr=?
                        limit ?,?
                        """,
                new Object[]{networkId, networkId, address, pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER, Types.CHAR, Types.INTEGER, Types.INTEGER},
                new OwnerDomainNameMapper());
    }

    @Override
    public Page<OwnerDomainName> getRegistrantDomainsPage(int networkId, String address, int pageNo, int pageSize) {

        long totalCount = getRegistrantDomainsCount(networkId, address);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<OwnerDomainName> resultData = getRegistrantDomainsPageQuery(networkId, address, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }


    private int getControllerDomainsCount(int networkId, String address) {
        return jdbcTemplate.queryForObject("""
                        SELECT count(*)
                        FROM ens_registry_event_transfer AS A 
                        JOIN  eth_registrar_controller_event_name_registered AS B
                        ON A.node=B.label
                        where  A.network_id=? AND B.network_id=? AND A.owner=?
                        """,
                new Object[]{networkId, networkId, address},
                new int[]{Types.INTEGER, Types.INTEGER, Types.CHAR},
                Integer.class);
    }

    private List<OwnerDomainName> getControllerDomainsPageQuery(int networkId, String address, int pageNo, int pageSize) {
        return jdbcTemplate.query("""
                        SELECT B.name,B.label,B.baseNodeIndex,B.network_id
                        FROM ens_registry_event_transfer AS A 
                        JOIN eth_registrar_controller_event_name_registered AS B
                        ON A.node=B.label
                        where A.network_id=? AND B.network_id=? AND A.owner=?
                        limit ?,?
                        """,
                new Object[]{networkId, networkId, address, pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER, Types.CHAR, Types.INTEGER, Types.INTEGER},
                new OwnerDomainNameMapper());
    }

    @Override
    public Page<OwnerDomainName> getControllerDomainsPage(int networkId, String address, int pageNo, int pageSize) {
        long totalCount = getControllerDomainsCount(networkId, address);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<OwnerDomainName> resultData = getControllerDomainsPageQuery(networkId, address, pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    @Override
    public List<OwnerDomainName> getReverseRecordDomains(int networkId, String address) {
        return jdbcTemplate.query("""
                        SELECT B.name,B.label,B.baseNodeIndex,B.network_id
                        FROM public_resolver_event_addr_changed AS A 
                        JOIN eth_registrar_controller_event_name_registered AS B
                        ON A.node=B.label
                        where A.network_id=? AND B.network_id=? AND A.addr=?                 
                        """,
                new Object[]{networkId, networkId, address},
                new int[]{Types.INTEGER, Types.INTEGER, Types.CHAR},
                new OwnerDomainNameMapper());
    }

    @Override
    public OwnerDomainName getOwnerDomainNameByLabel(int networkId, String label) {
        if (jdbcTemplate.queryForObject("""
                        SELECT COUNT (*) 
                        FROM eth_registrar_controller_event_name_registered 
                        WHERE network_id=? AND label=?
                        """,
                new Object[]{networkId, label},
                new int[]{Types.INTEGER, Types.CHAR}, Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                        SELECT name,label,baseNodeIndex,network_id 
                        FROM eth_registrar_controller_event_name_registered 
                        WHERE network_id=? AND label=?
                        """,
                new Object[]{networkId, label},
                new int[]{Types.INTEGER, Types.CHAR},
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
            ownerDomainName.setNetworkId(rs.getInt("network_id"));
            ownerDomainName.setLabel(rs.getString("label"));
            ownerDomainName.setNetworkId(rs.getInt("network_id"));

            return ownerDomainName;
        }
    }
}
