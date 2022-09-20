package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.repository.BaseRegistrarEventNameRegisteredRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;

@Repository
public class BaseRegistrarEventNameRegisteredRepositoryImpl implements BaseRegistrarEventNameRegisteredRepository {
    private final JdbcTemplate jdbcTemplate;

    public BaseRegistrarEventNameRegisteredRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int getDomainNamesCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM base_registrar_event_name_registered
                                                   WHERE network_id=?
                                                   """,
                                           new Object[]{networkId},
                                           new int[]{Types.INTEGER},
                                           Integer.class);
    }

    @Override
    public int getOwnersCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT COUNT(DISTINCT owner)
                                                   FROM base_registrar_event_name_registered
                                                   WHERE network_id=?
                                                   """,
                                           new Object[]{networkId},
                                           new int[]{Types.INTEGER},
                                           Integer.class);
    }


}