package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.EnsRegistryEventNewOwner;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EnsRegistryEventNewOwnerRepository;
import domains.unit.metaserver.service.EnsRegistryEventNewOwnerService;
import org.springframework.stereotype.Service;

@Service
public class EnsRegistryEventNewOwnerServiceImpl implements EnsRegistryEventNewOwnerService {
    private final EnsRegistryEventNewOwnerRepository ensRegistryEventNewOwnerRepository;

    public EnsRegistryEventNewOwnerServiceImpl(EnsRegistryEventNewOwnerRepository ensRegistryEventNewOwnerRepository) {
        this.ensRegistryEventNewOwnerRepository = ensRegistryEventNewOwnerRepository;
    }
    
    @Override
    public EnsRegistryEventNewOwner getByPkId(String pkId) {
        return ensRegistryEventNewOwnerRepository.getByPkId(pkId);
    }


    @Override
    public Page<EnsRegistryEventNewOwner> getPage(int networkId,
                                                  String address,
                                                  int pageNo,
                                                  int pageSize) {
        return ensRegistryEventNewOwnerRepository.getPage(networkId,
                                                          address,
                                                          pageNo,
                                                          pageSize);
    }

}