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
    public int getCount() {
        return baseRegistrarEventTransferRepository.getCount();
    }

    /**
     * 根据pkId得到BaseRegistrarEventTransfer
     *
     * @param pkId
     */
    @Override
    public BaseRegistrarEventTransfer getByPkId(String pkId) {
        return baseRegistrarEventTransferRepository.getByPkId(pkId);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BaseRegistrarEventTransfer> getPage(int pageNo, int pageSize) {
        return baseRegistrarEventTransferRepository.getPage(pageNo, pageSize);
    }

}