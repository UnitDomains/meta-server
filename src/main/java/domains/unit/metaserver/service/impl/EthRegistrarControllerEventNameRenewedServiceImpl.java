package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRenewed;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventNameRenewedRepository;
import domains.unit.metaserver.service.EthRegistrarControllerEventNameRenewedService;
import org.springframework.stereotype.Service;

@Service
public class EthRegistrarControllerEventNameRenewedServiceImpl
        implements EthRegistrarControllerEventNameRenewedService {
    private final EthRegistrarControllerEventNameRenewedRepository ethRegistrarControllerEventNameRenewedRepository;

    public EthRegistrarControllerEventNameRenewedServiceImpl(EthRegistrarControllerEventNameRenewedRepository ethRegistrarControllerEventNameRenewedRepository) {
        this.ethRegistrarControllerEventNameRenewedRepository = ethRegistrarControllerEventNameRenewedRepository;
    }
    
    @Override
    public EthRegistrarControllerEventNameRenewed getByPkId(String pkId) {
        return ethRegistrarControllerEventNameRenewedRepository.getByPkId(pkId);
    }


    @Override
    public Page<EthRegistrarControllerEventNameRenewed> getPage(int networkId,
                                                                int pageNo,
                                                                int pageSize) {
        return ethRegistrarControllerEventNameRenewedRepository.getPage(networkId,
                                                                        pageNo,
                                                                        pageSize);
    }

}