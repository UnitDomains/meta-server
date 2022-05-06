package domains.unit.metaserver.service;

import domains.unit.metaserver.model.BaseRegistrarEventNameRenewed;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventNameRenewedService {

    int getCount();

    BaseRegistrarEventNameRenewed getByPkId(String pkId);

    Page<BaseRegistrarEventNameRenewed> getPage(int pageNo, int pageSize);

}