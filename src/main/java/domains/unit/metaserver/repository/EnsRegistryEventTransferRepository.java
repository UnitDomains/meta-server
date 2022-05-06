package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EnsRegistryEventTransfer;
import domains.unit.metaserver.model.Page;

public interface EnsRegistryEventTransferRepository {


    int getCount();

    EnsRegistryEventTransfer getByPkId(String pkId);

    Page<EnsRegistryEventTransfer> getPage(int pageNo, int pageSize);

}