package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EnsRegistryEventTransfer;
import domains.unit.metaserver.model.Page;

public interface EnsRegistryEventTransferService {
   

    int getCount();

    EnsRegistryEventTransfer getByPkId(String pkId);

    Page<EnsRegistryEventTransfer> getPage(int pageNo, int pageSize);

}