package domains.unit.metaserver.service;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.PriceInfo;

public interface PriceInfoService {


    int getCount(int networkId);

    PriceInfo getByPkId(String pkId);

    Page<PriceInfo> getPage(int networkId,
                            int pageNo,
                            int pageSize);


    String getRentPrice(int networkId,
                        String domainName);

    String GetRegisterPrice(int networkId,
                            String domainName);

    PriceInfo getPrice(int networkId);
}