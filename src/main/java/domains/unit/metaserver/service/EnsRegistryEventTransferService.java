package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EnsRegistryEventTransfer;
import domains.unit.metaserver.model.Page;

public interface EnsRegistryEventTransferService {
    EnsRegistryEventTransfer getByPkId(String pkId);

    Page<EnsRegistryEventTransfer> getPage(int networkId,
                                           int pageNo,
                                           int pageSize);
}