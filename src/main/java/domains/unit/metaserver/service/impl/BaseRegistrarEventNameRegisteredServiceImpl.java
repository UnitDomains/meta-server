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
    public int getCount() {
        return baseRegistrarEventNameRegisteredRepository.getCount();
    }

    /**
     * 根据pkId得到BaseRegistrarEventNameRegistered
     *
     * @param pkId
     */
    @Override
    public BaseRegistrarEventNameRegistered getByPkId(String pkId) {
        return baseRegistrarEventNameRegisteredRepository.getByPkId(pkId);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BaseRegistrarEventNameRegistered> getPage(int pageNo, int pageSize) {
        return baseRegistrarEventNameRegisteredRepository.getPage(pageNo, pageSize);
    }

}