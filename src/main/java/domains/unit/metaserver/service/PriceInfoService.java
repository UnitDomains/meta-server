package domains.unit.metaserver.service;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.PriceInfo;

public interface PriceInfoService {


    int getCount(int networkId);

    PriceInfo getByPkId(String pkId);

    Page<PriceInfo> getPage(int networkId,
                            int pageNo,
                            int pageSize);


    PriceInfo getRentYearsPrice(int networkId,
                                String domainName,
                                Integer years);

    PriceInfo GetRegisterPrice(int networkId,
                               String domainName);
}