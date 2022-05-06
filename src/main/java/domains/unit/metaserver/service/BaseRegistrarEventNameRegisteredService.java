package domains.unit.metaserver.service;

import domains.unit.metaserver.model.BaseRegistrarEventNameRegistered;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventNameRegisteredService {

    int getCount();

    BaseRegistrarEventNameRegistered getByPkId(String pkId);

    Page<BaseRegistrarEventNameRegistered> getPage(int pageNo, int pageSize);

}