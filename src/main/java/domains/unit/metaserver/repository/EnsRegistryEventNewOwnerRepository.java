package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EnsRegistryEventNewOwner;
import domains.unit.metaserver.model.Page;

public interface EnsRegistryEventNewOwnerRepository {


    int getCount(String address);

    EnsRegistryEventNewOwner getByPkId(String pkId);

    Page<EnsRegistryEventNewOwner> getPage(String address, int pageNo, int pageSize);

}