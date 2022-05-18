package domains.unit.webserver.service.impl;

import domains.unit.metaserver.model.EthRegistrarControllerEventNameRegistered;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EthRegistrarControllerEventNameRegisteredRepository;
import domains.unit.metaserver.service.EthRegistrarControllerEventNameRegisteredService;
import org.springframework.stereotype.Service;

@Service
public class EthRegistrarControllerEventNameRegisteredServiceImpl
        implements EthRegistrarControllerEventNameRegisteredService {
    private final EthRegistrarControllerEventNameRegisteredRepository
            ethRegistrarControllerEventNameRegisteredRepository;

    public EthRegistrarControllerEventNameRegisteredServiceImpl(EthRegistrarControllerEventNameRegisteredRepository ethRegistrarControllerEventNameRegisteredRepository) {
        this.ethRegistrarControllerEventNameRegisteredRepository = ethRegistrarControllerEventNameRegisteredRepository;
    }
    
    @Override
    public EthRegistrarControllerEventNameRegistered getByPkId(String pkId) {
        return ethRegistrarControllerEventNameRegisteredRepository.getByPkId(pkId);
    }

    @Override
    public Page<EthRegistrarControllerEventNameRegistered> getPage(int networkId,
                                                                   int pageNo,
                                                                   int pageSize) {
        return ethRegistrarControllerEventNameRegisteredRepository.getPage(networkId,
                                                                           pageNo,
                                                                           pageSize);
    }

}