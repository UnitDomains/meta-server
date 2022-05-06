package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRegistered;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventNameRegisteredRepository {

    int getCount();

    EthRegistrarControllerEventNameRegistered getByPkId(String pkId);

    Page<EthRegistrarControllerEventNameRegistered> getPage(int pageNo, int pageSize);

}