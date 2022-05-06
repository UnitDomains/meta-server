package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EthRegistrarControllerEventOwnershipTransferred;
import domains.unit.metaserver.model.Page;


public interface EthRegistrarControllerEventOwnershipTransferredService {

    int getCount();

    Page<EthRegistrarControllerEventOwnershipTransferred> getPage(int pageNo, int pageSize);

}