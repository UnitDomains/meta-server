package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.EnsRegistryEventTransfer;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EnsRegistryEventTransferRepository;
import domains.unit.metaserver.service.EnsRegistryEventTransferService;
import org.springframework.stereotype.Service;

@Service
public class EnsRegistryEventTransferServiceImpl implements EnsRegistryEventTransferService {
    private final EnsRegistryEventTransferRepository ensRegistryEventTransferRepository;

    public EnsRegistryEventTransferServiceImpl(EnsRegistryEventTransferRepository ensRegistryEventTransferRepository) {
        this.ensRegistryEventTransferRepository = ensRegistryEventTransferRepository;
    }
    
    @Override
    public EnsRegistryEventTransfer getByPkId(String pkId) {
        return ensRegistryEventTransferRepository.getByPkId(pkId);
    }


    @Override
    public Page<EnsRegistryEventTransfer> getPage(int networkId,
                                                  int pageNo,
                                                  int pageSize) {
        return ensRegistryEventTransferRepository.getPage(networkId,
                                                          pageNo,
                                                          pageSize);
    }

}