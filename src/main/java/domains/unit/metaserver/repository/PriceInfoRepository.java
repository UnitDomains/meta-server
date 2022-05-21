package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.PriceInfo;

public interface PriceInfoRepository {


    int getCount(int networkId);

    PriceInfo getByPkId(String pkId);

    PriceInfo getByNetworkId(int networkId);

    Page<PriceInfo> getPage(int networkId,
                            int pageNo,
                            int pageSize);

}