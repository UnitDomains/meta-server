package domains.unit.metaserver.repository.impl;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.PriceInfo;
import domains.unit.metaserver.repository.PriceInfoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class PriceInfoRepositoryImpl implements PriceInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    public PriceInfoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据pkId得到记录
     */
    @Override
    public PriceInfo getByPkId(String pkId) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM price_info 
                                                WHERE pk_id=?
                                                """,
                                        new Object[]{pkId},
                                        new int[]{Types.CHAR},
                                        Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM price_info
                                                   WHERE pk_id=?""",
                                           new Object[]{pkId},
                                           new int[]{Types.CHAR},
                                           new PriceInfoMapper());
    }

    @Override
    public PriceInfo getByNetworkId(int networkId) {
        if (jdbcTemplate.queryForObject("""
                                                SELECT count(*) 
                                                FROM price_info 
                                                WHERE network_id=?
                                                """,
                                        new Object[]{networkId},
                                        new int[]{Types.INTEGER},
                                        Integer.class) == 0)
            return null;

        return jdbcTemplate.queryForObject("""
                                                   SELECT * 
                                                   FROM price_info
                                                   WHERE network_id=?""",
                                           new Object[]{networkId},
                                           new int[]{Types.INTEGER},
                                           new PriceInfoMapper());
    }

    /**
     * getCount
     */
    @Override
    public int getCount(int networkId) {
        return jdbcTemplate.queryForObject("""
                                                   SELECT count(*) 
                                                   FROM price_info
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

    private List<PriceInfo> getPageQuery(int networkId,
                                         int pageNo,
                                         int pageSize) {
        return jdbcTemplate.query("""
                                          SELECT * 
                                          FROM price_info
                                          WHERE networkId=?
                                          limit ?,?
                                          """,
                                  new Object[]{networkId, pageNo * pageSize, pageSize},
                                  new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER},
                                  new PriceInfoMapper());
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<PriceInfo> getPage(int networkId,
                                   int pageNo,
                                   int pageSize) {
        long totalCount = getCount(networkId);
        if (totalCount < 1) return new Page<>();
        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);
        List<PriceInfo> resultData = getPageQuery(networkId,
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
    private static final class PriceInfoMapper implements RowMapper<PriceInfo> {
        @Override
        public PriceInfo mapRow(ResultSet rs,
                                int rowNum) throws SQLException {
            PriceInfo priceInfo = new PriceInfo();
            priceInfo.setPkId(rs.getString("pk_id"));
            priceInfo.setNetworkId(rs.getInt("network_id"));
            priceInfo.setPaymentType(rs.getInt("payment_type"));
            priceInfo.setRegisterPrice(rs.getString("register_price"));
            priceInfo.setRentPrice(rs.getString("rent_price"));
            return priceInfo;
        }
    }

}