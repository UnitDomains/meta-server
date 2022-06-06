package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.ReverseInfo;
import domains.unit.metaserver.repository.ReverseInfoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class ReverseInfoRepositoryImpl implements ReverseInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReverseInfoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 根据pkId得到记录
     */
    @Override
    public ReverseInfo getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM reverse_info 
                                                WHERE pk_id=?
                                                """,
                                        new Object[]{pkId},
                                        new int[]{Types.CHAR},
                                        Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM reverse_info
                                                   WHERE pk_id=?""",
                                           new Object[]{pkId},
                                           new int[]{Types.CHAR},
                                           new ReverseInfoMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM reverse_info
                                                   WHERE network_id=?
                                                   """,
                                           new Object[]{networkId},
                                           new int[]{Types.INTEGER},
                                           Integer.class);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */

    private List<ReverseInfo> getPageQuery(int networkId,
                                           int pageNo,
                                           int pageSize) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM reverse_info
                                          WHERE network_id=?
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                                  new ReverseInfoMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<ReverseInfo> getPage(int networkId,
                                     int pageNo,
                                     int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<ReverseInfo> resultData = getPageQuery(networkId,
                                                    pageNo - 1,
                                                    pageSize);
        return new Page<>(0,
                          totalCount,
                          (int) totalCount,
                          resultData);
    }

    @Override
    public ReverseInfo getReverseInfoByAddress(int networkId,
                                               String address) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM reverse_info 
                                                WHERE network_id=? AND addr=?
                                                """,
                                        new Object[]{networkId, address},
                                        new int[]{Types.CHAR, Types.CHAR},
                                        Integer.class) != 1)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM reverse_info
                                                   WHERE network_id=? AND addr=?
                                                   """,
                                           new Object[]{networkId, address},
                                           new int[]{Types.CHAR, Types.CHAR},
                                           new ReverseInfoMapper());
    }

    /**
     * RowMapper
     */
    private static final class ReverseInfoMapper implements RowMapper<ReverseInfo> {
        @Override
        public ReverseInfo mapRow(ResultSet rs,
                                  int rowNum) throws SQLException {
            ReverseInfo reverseInfo = new ReverseInfo();
            reverseInfo.setPkId(rs.getString("pk_id"));
            reverseInfo.setNetworkId(rs.getInt("network_id"));
            reverseInfo.setAddr(rs.getString("addr"));
            reverseInfo.setNode(rs.getString("node"));
            reverseInfo.setName(rs.getString("name"));
            reverseInfo.setOpTime(rs.getDate("op_time"));
            return reverseInfo;
        }
    }

}