package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventOwnershipTransferred;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventOwnershipTransferredRepository;
import domains.unit.metaserver.service.EthRegistrarControllerEventOwnershipTransferredService;
import org.springframework.stereotype.Service;

@Service
public class EthRegistrarControllerEventOwnershipTransferredServiceImpl implements
                                                                        EthRegistrarControllerEventOwnershipTransferredService {
    private final EthRegistrarControllerEventOwnershipTransferredRepository
            ethRegistrarControllerEventOwnershipTransferredRepository;

    public EthRegistrarControllerEventOwnershipTransferredServiceImpl(
            EthRegistrarControllerEventOwnershipTransferredRepository
                    ethRegistrarControllerEventOwnershipTransferredRepository) {
        this.ethRegistrarControllerEventOwnershipTransferredRepository =
                ethRegistrarControllerEventOwnershipTransferredRepository;
    }
    
    @Override
    public Page<EthRegistrarControllerEventOwnershipTransferred> getPage(int networkId,
                                                                         int pageNo,
                                                                         int pageSize) {
        return ethRegistrarControllerEventOwnershipTransferredRepository.getPage(networkId,
                                                                                 pageNo,
                                                                                 pageSize);
    }

}