package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventOwnershipTransferred;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventOwnershipTransferredRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class EthRegistrarControllerEventOwnershipTransferredRepositoryImpl implements EthRegistrarControllerEventOwnershipTransferredRepository {
    private final JdbcTemplate jdbcTemplate;

    public EthRegistrarControllerEventOwnershipTransferredRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * getCount
     */
    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("""
                        select count(*)
                        from eth_registrar_controller_event_ownership_transferred
                        """,
                Integer.class);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */

    private List<EthRegistrarControllerEventOwnershipTransferred> getPageQuery(int pageNo, int pageSize) {
        return jdbcTemplate.query("select * from eth_registrar_controller_event_ownership_transferred limit ?,?",
                new Object[]{pageNo * pageSize, pageSize},
                new int[]{Types.INTEGER, Types.INTEGER},
                new EthRegistrarControllerEventOwnershipTransferredMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<EthRegistrarControllerEventOwnershipTransferred> getPage(int pageNo, int pageSize) {
        long totalCount = getCount();
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<EthRegistrarControllerEventOwnershipTransferred> resultData = getPageQuery(pageNo - 1, pageSize);
        return new Page<>(0, totalCount, (int) totalCount, resultData);
    }

    /**
     * RowMapper
     */
    private static final class EthRegistrarControllerEventOwnershipTransferredMapper implements
            RowMapper<EthRegistrarControllerEventOwnershipTransferred> {
        @Override
        public EthRegistrarControllerEventOwnershipTransferred mapRow(ResultSet rs, int rowNum) throws SQLException {
            EthRegistrarControllerEventOwnershipTransferred ethRegistrarControllerEventOwnershipTransferred =
                    new EthRegistrarControllerEventOwnershipTransferred();
            ethRegistrarControllerEventOwnershipTransferred.setPkId(rs.getString("pk_id"));
            ethRegistrarControllerEventOwnershipTransferred.setPreviousOwner(rs.getString("previousOwner"));
            ethRegistrarControllerEventOwnershipTransferred.setNewOwner(rs.getString("newOwner"));
            ethRegistrarControllerEventOwnershipTransferred.setTimestamp(rs.getDate("timestamp"));
            ethRegistrarControllerEventOwnershipTransferred.setOpTime(rs.getDate("op_time"));
            return ethRegistrarControllerEventOwnershipTransferred;
        }
    }

}