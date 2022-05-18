package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.BaseRegistrarEventNameRenewed;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BaseRegistrarEventNameRenewedRepository;
import domains.unit.metaserver.service.BaseRegistrarEventNameRenewedService;
import org.springframework.stereotype.Service;

@Service
public class BaseRegistrarEventNameRenewedServiceImpl implements BaseRegistrarEventNameRenewedService {
    private final BaseRegistrarEventNameRenewedRepository baseRegistrarEventNameRenewedRepository;

    public BaseRegistrarEventNameRenewedServiceImpl(
            BaseRegistrarEventNameRenewedRepository baseRegistrarEventNameRenewedRepository) {
        this.baseRegistrarEventNameRenewedRepository = baseRegistrarEventNameRenewedRepository;
    }


    @Override
    public BaseRegistrarEventNameRenewed getByPkId(String pkId) {
        return baseRegistrarEventNameRenewedRepository.getByPkId(pkId);
    }


    @Override
    public Page<BaseRegistrarEventNameRenewed> getPage(int networkId,
                                                       int pageNo,
                                                       int pageSize) {
        return baseRegistrarEventNameRenewedRepository.getPage(networkId,
                                                               pageNo,
                                                               pageSize);
    }

}