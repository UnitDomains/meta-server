package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRenewed;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventNameRenewedRepository {

    int getCount();

    EthRegistrarControllerEventNameRenewed getByPkId(String pkId);

    Page<EthRegistrarControllerEventNameRenewed> getPage(int pageNo, int pageSize);

}