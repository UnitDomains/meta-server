package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRegistered;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventNameRegisteredService {


    int getCount();

    EthRegistrarControllerEventNameRegistered getByPkId(String pkId);

    Page<EthRegistrarControllerEventNameRegistered> getPage(int pageNo, int pageSize);

}