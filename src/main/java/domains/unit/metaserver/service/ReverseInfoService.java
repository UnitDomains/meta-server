package domains.unit.metaserver.service;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.ReverseInfo;

public interface ReverseInfoService {


    int getCount(int networkId);

    ReverseInfo getByPkId(String pkId);

    Page<ReverseInfo> getPage(int networkId,
                              int pageNo,
                              int pageSize);

    ReverseInfo getReverseInfo(int networkId,
                               String domainName);

    ReverseInfo getReverseRecord(int networkId,
                                 String address);
}