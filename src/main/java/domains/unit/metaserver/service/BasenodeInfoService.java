package domains.unit.metaserver.service;

import domains.unit.metaserver.model.BasenodeInfo;
import domains.unit.metaserver.model.Page;

public interface BasenodeInfoService {

    int getCount(int networkId);

    BasenodeInfo getByPkId(String pkId);

    Page<BasenodeInfo> getPage(int networkId,
                               int pageNo,
                               int pageSize);

}