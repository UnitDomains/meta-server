package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.BaseRegistrarEventNameRegistered;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BaseRegistrarEventNameRegisteredRepository;
import domains.unit.metaserver.service.BaseRegistrarEventNameRegisteredService;
import org.springframework.stereotype.Service;

@Service
public class BaseRegistrarEventNameRegisteredServiceImpl implements BaseRegistrarEventNameRegisteredService {
    private final BaseRegistrarEventNameRegisteredRepository baseRegistrarEventNameRegisteredRepository;

    public BaseRegistrarEventNameRegisteredServiceImpl(
            BaseRegistrarEventNameRegisteredRepository baseRegistrarEventNameRegisteredRepository) {
        this.baseRegistrarEventNameRegisteredRepository = baseRegistrarEventNameRegisteredRepository;
    }


    @Override
    public BaseRegistrarEventNameRegistered getByPkId(String pkId) {
        return baseRegistrarEventNameRegisteredRepository.getByPkId(pkId);
    }


    @Override
    public Page<BaseRegistrarEventNameRegistered> getPage(int networkId,
                                                          int pageNo,
                                                          int pageSize) {
        return baseRegistrarEventNameRegisteredRepository.getPage(networkId,
                                                                  pageNo,
                                                                  pageSize);
    }

}