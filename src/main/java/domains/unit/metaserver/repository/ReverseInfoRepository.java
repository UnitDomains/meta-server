package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.ReverseInfo;

public interface ReverseInfoRepository {


    int getCount(int networkId);

    ReverseInfo getByPkId(String pkId);

    Page<ReverseInfo> getPage(int networkId,
                              int pageNo,
                              int pageSize);

    ReverseInfo getReverseInfoByAddress(int networkId,
                                        String address);
}