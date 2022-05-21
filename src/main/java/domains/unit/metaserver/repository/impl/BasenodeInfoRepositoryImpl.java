package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.BasenodeInfo;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BasenodeInfoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class BasenodeInfoRepositoryImpl implements BasenodeInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    public BasenodeInfoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 根据pkId得到记录
     */
    @Override
    public BasenodeInfo getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM basenode_info 
                                                WHERE pk_id=?
                                                """,
                                        new Object[]{pkId},
                                        new int[]{Types.CHAR},
                                        Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM basenode_info
                                                   WHERE pk_id=?
                                                   """,
                                           new Object[]{pkId},
                                           new int[]{Types.CHAR},
                                           new BasenodeInfoMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM basenode_info
                                                   WHERE networkId=?
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

    private List<BasenodeInfo> getPageQuery(int networkId,
                                            int pageNo,
                                            int pageSize) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM basenode_info
                                          WHERE networkId=?
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                                  new BasenodeInfoMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BasenodeInfo> getPage(int networkId,
                                      int pageNo,
                                      int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<BasenodeInfo> resultData = getPageQuery(networkId,
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
    private static final class BasenodeInfoMapper implements RowMapper<BasenodeInfo> {
        @Override
        public BasenodeInfo mapRow(ResultSet rs,
                                   int rowNum) throws SQLException {
            BasenodeInfo basenodeInfo = new BasenodeInfo();
            basenodeInfo.setPkId(rs.getString("pk_id"));
            basenodeInfo.setNode(rs.getString("node"));
            basenodeInfo.setName(rs.getString("name"));
            basenodeInfo.setNetworkId(rs.getInt("network_id"));
            basenodeInfo.setOpTime(rs.getDate("op_time"));
            return basenodeInfo;
        }
    }

}