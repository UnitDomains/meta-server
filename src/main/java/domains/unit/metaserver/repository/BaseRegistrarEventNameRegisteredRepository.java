package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.BaseRegistrarEventNameRegistered;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventNameRegisteredRepository {

    int getCount(int networkId);

    BaseRegistrarEventNameRegistered getByPkId(String pkId);

    BaseRegistrarEventNameRegistered getById(int networkId, String id);

    Page<BaseRegistrarEventNameRegistered> getPage(int networkId, int pageNo, int pageSize);

}