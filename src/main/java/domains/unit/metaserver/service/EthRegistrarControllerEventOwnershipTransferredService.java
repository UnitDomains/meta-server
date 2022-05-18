package domains.unit.metaserver.service;

import domains.unit.metaserver.model.EthRegistrarControllerEventOwnershipTransferred;
import domains.unit.metaserver.model.Page;


public interface EthRegistrarControllerEventOwnershipTransferredService {

    Page<EthRegistrarControllerEventOwnershipTransferred> getPage(int networkId,
                                                                  int pageNo,
                                                                  int pageSize);

}