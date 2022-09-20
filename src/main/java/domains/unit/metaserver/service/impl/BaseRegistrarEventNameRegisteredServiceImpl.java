package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.repository.BaseRegistrarEventNameRegisteredRepository;
import domains.unit.metaserver.service.BaseRegistrarEventNameRegisteredService;
import org.springframework.stereotype.Service;

@Service
public class BaseRegistrarEventNameRegisteredServiceImpl implements BaseRegistrarEventNameRegisteredService {
    private final BaseRegistrarEventNameRegisteredRepository baseRegistrarEventNameRegisteredRepository;


    public BaseRegistrarEventNameRegisteredServiceImpl(BaseRegistrarEventNameRegisteredRepository baseRegistrarEventNameRegisteredRepository) {
        this.baseRegistrarEventNameRegisteredRepository = baseRegistrarEventNameRegisteredRepository;
    }


    @Override
    public int getDomainNamesCount(int networkId) {
        return baseRegistrarEventNameRegisteredRepository.getDomainNamesCount(networkId);
    }

    @Override
    public int getOwnersCount(int networkId) {
        return baseRegistrarEventNameRegisteredRepository.getOwnersCount(networkId);
    }
}