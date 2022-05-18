package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRenewed;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventNameRenewedService {

    EthRegistrarControllerEventNameRenewed getByPkId(String pkId);

    Page<EthRegistrarControllerEventNameRenewed> getPage(int networkId,
                                                         int pageNo,
                                                         int pageSize);

}