package domains.unit.metaserver.service;

import domains.unit.metaserver.model.BaseRegistrarEventTransfer;
import domains.unit.metaserver.model.Page;

public interface BaseRegistrarEventTransferService {

	int getCount();

	BaseRegistrarEventTransfer getByPkId(String pkId);

	Page<BaseRegistrarEventTransfer> getPage(int pageNo, int pageSize);

}