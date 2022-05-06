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
    public int getCount() {
        return ethRegistrarControllerEventOwnershipTransferredRepository.getCount();
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<EthRegistrarControllerEventOwnershipTransferred> getPage(int pageNo, int pageSize) {
        return ethRegistrarControllerEventOwnershipTransferredRepository.getPage(pageNo, pageSize);
    }

}