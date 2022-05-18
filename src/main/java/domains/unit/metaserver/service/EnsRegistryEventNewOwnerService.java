package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EnsRegistryEventNewOwner;
import domains.unit.metaserver.model.Page;

public interface EnsRegistryEventNewOwnerService {
    EnsRegistryEventNewOwner getByPkId(String pkId);

    Page<EnsRegistryEventNewOwner> getPage(int networkId,
                                           String address,
                                           int pageNo,
                                           int pageSize);
}