package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.BaseRegistrarEventTransfer;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventTransferRepository {
    int getCount(int networkId);

    BaseRegistrarEventTransfer getByPkId(String pkId);

    Page<BaseRegistrarEventTransfer> getPage(int networkId, int pageNo, int pageSize);

}