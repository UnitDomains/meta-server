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
    public int getCount() {
        return baseRegistrarEventNameRenewedRepository.getCount();
    }

    /**
     * 根据pkId得到BaseRegistrarEventNameRenewed
     *
     * @param pkId
     */
    @Override
    public BaseRegistrarEventNameRenewed getByPkId(String pkId) {
        return baseRegistrarEventNameRenewedRepository.getByPkId(pkId);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BaseRegistrarEventNameRenewed> getPage(int pageNo, int pageSize) {
        return baseRegistrarEventNameRenewedRepository.getPage(pageNo, pageSize);
    }

}