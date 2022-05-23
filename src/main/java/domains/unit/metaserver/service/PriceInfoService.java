package domains.unit.metaserver.service;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.PriceInfo;

public interface PriceInfoService {


    int getCount(int networkId);

    PriceInfo getByPkId(String pkId);

    Page<PriceInfo> getPage(int networkId,
                            int pageNo,
                            int pageSize);


    String getRentYearsPrice(int networkId,
                             String domainName,
                             Integer years);

    String GetRegisterPrice(int networkId,
                            String domainName);

    PriceInfo getPrice(int networkId);
}