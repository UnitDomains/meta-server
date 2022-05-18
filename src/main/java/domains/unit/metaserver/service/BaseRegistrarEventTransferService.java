package domains.unit.metaserver.service;

import domains.unit.metaserver.model.BaseRegistrarEventTransfer;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventTransferService {

    BaseRegistrarEventTransfer getByPkId(String pkId);

    Page<BaseRegistrarEventTransfer> getPage(int networkId,
                                             int pageNo,
                                             int pageSize);

}