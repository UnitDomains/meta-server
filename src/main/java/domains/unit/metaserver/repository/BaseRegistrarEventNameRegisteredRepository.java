package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.BaseRegistrarEventNameRegistered;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventNameRegisteredRepository {


    int getCount();

    BaseRegistrarEventNameRegistered getByPkId(String pkId);

    BaseRegistrarEventNameRegistered getById(String id);

    Page<BaseRegistrarEventNameRegistered> getPage(int pageNo, int pageSize);

}