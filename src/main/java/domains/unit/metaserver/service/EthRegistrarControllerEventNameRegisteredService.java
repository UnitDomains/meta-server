package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRegistered;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventNameRegisteredService {

    EthRegistrarControllerEventNameRegistered getByPkId(String pkId);

    Page<EthRegistrarControllerEventNameRegistered> getPage(int networkId,
                                                            int pageNo,
                                                            int pageSize);

}