package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.BaseRegistrarEventNameRenewed;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventNameRenewedRepository {

    int getCount(int networkId);

    BaseRegistrarEventNameRenewed getByPkId(String pkId);

    Page<BaseRegistrarEventNameRenewed> getPage(int networkId, int pageNo, int pageSize);

}