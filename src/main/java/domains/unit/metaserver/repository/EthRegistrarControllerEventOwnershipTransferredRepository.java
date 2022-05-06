package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.EthRegistrarControllerEventOwnershipTransferred;
import domains.unit.metaserver.model.Page;

public interface EthRegistrarControllerEventOwnershipTransferredRepository {

    int getCount();

    Page<EthRegistrarControllerEventOwnershipTransferred> getPage(int pageNo, int pageSize);

}