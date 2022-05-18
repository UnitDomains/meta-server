package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EthRegistrarControllerEventOwnershipTransferred;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventOwnershipTransferredRepository {

    int getCount(int networkId);

    Page<EthRegistrarControllerEventOwnershipTransferred> getPage(int networkId, int pageNo, int pageSize);

}