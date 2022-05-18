package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.BaseRegistrarEventTransfer;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BaseRegistrarEventTransferRepository;
import domains.unit.metaserver.service.BaseRegistrarEventTransferService;
import org.springframework.stereotype.Service;

@Service
public class BaseRegistrarEventTransferServiceImpl implements BaseRegistrarEventTransferService {
    private final BaseRegistrarEventTransferRepository baseRegistrarEventTransferRepository;

    public BaseRegistrarEventTransferServiceImpl(BaseRegistrarEventTransferRepository baseRegistrarEventTransferRepository) {
        this.baseRegistrarEventTransferRepository = baseRegistrarEventTransferRepository;
    }


    @Override
    public BaseRegistrarEventTransfer getByPkId(String pkId) {
        return baseRegistrarEventTransferRepository.getByPkId(pkId);
    }


    @Override
    public Page<BaseRegistrarEventTransfer> getPage(int networkId,
                                                    int pageNo,
                                                    int pageSize) {
        return baseRegistrarEventTransferRepository.getPage(networkId,
                                                            pageNo,
                                                            pageSize);
    }

}