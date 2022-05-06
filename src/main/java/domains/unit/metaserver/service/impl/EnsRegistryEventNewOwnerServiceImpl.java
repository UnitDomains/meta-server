package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.EnsRegistryEventNewOwner;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.EnsRegistryEventNewOwnerRepository;
import domains.unit.metaserver.service.EnsRegistryEventNewOwnerService;
import org.springframework.stereotype.Service;

@Service
public class EnsRegistryEventNewOwnerServiceImpl implements EnsRegistryEventNewOwnerService {
    private final EnsRegistryEventNewOwnerRepository ensRegistryEventNewOwnerRepository;

    public EnsRegistryEventNewOwnerServiceImpl(EnsRegistryEventNewOwnerRepository ensRegistryEventNewOwnerRepository) {
        this.ensRegistryEventNewOwnerRepository = ensRegistryEventNewOwnerRepository;
    }


    @Override
    public int getCount(String address) {
        return ensRegistryEventNewOwnerRepository.getCount(address);
    }

    /**
     * 根据pkId得到EnsRegistryEventNewOwner
     *
     * @param pkId
     */
    @Override
    public EnsRegistryEventNewOwner getByPkId(String pkId) {
        return ensRegistryEventNewOwnerRepository.getByPkId(pkId);
    }

    /**
     * 获得指定页面数据
     *
     * @param address
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<EnsRegistryEventNewOwner> getPage(String address, int pageNo, int pageSize) {
        return ensRegistryEventNewOwnerRepository.getPage(address, pageNo, pageSize);
    }

}