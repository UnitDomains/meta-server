package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRegistered;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventNameRegisteredRepository {

    int getCount(int networkId);

    EthRegistrarControllerEventNameRegistered getByPkId(String pkId);

    Page<EthRegistrarControllerEventNameRegistered> getPage(int networkId, int pageNo, int pageSize);

}