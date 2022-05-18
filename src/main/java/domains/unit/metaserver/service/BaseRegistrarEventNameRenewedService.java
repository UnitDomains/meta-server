package domains.unit.metaserver.service;

import domains.unit.metaserver.model.BaseRegistrarEventNameRenewed;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventNameRenewedService {

    BaseRegistrarEventNameRenewed getByPkId(String pkId);

    Page<BaseRegistrarEventNameRenewed> getPage(int networkId,
                                                int pageNo,
                                                int pageSize);

}