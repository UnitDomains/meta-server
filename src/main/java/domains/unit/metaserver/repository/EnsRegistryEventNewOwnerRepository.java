package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EnsRegistryEventNewOwner;
import domains.unit.metaserver.model.Page;

public interface EnsRegistryEventNewOwnerRepository {
    int getCount(int networkId, String address);

    EnsRegistryEventNewOwner getByPkId(String pkId);

    Page<EnsRegistryEventNewOwner> getPage(int networkId, String address, int pageNo, int pageSize);

}