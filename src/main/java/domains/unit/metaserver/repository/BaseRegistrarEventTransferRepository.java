package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.BaseRegistrarEventTransfer;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventTransferRepository {
    int getCount();

    BaseRegistrarEventTransfer getByPkId(String pkId);

    Page<BaseRegistrarEventTransfer> getPage(int pageNo, int pageSize);

}