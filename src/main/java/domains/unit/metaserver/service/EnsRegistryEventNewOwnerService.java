package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EnsRegistryEventNewOwner;
import domains.unit.metaserver.model.Page;

public interface EnsRegistryEventNewOwnerService {
 

    int getCount(String address);

    EnsRegistryEventNewOwner getByPkId(String pkId);

    Page<EnsRegistryEventNewOwner> getPage(String address, int pageNo, int pageSize);

}