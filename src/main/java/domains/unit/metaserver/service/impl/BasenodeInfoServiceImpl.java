package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.BasenodeInfo;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.repository.BasenodeInfoRepository;
import domains.unit.metaserver.service.BasenodeInfoService;
import org.springframework.stereotype.Service;

@Service
public class BasenodeInfoServiceImpl implements BasenodeInfoService {
    private final BasenodeInfoRepository basenodeInfoRepository;

    public BasenodeInfoServiceImpl(BasenodeInfoRepository basenodeInfoRepository) {
        this.basenodeInfoRepository = basenodeInfoRepository;
    }


    @Override
    public int getCount(int networkId) {
        return basenodeInfoRepository.getCount(networkId);
    }

    /**
     * 根据pkId得到BasenodeInfo
     *
     * @param pkId
     */
    @Override
    public BasenodeInfo getByPkId(String pkId) {
        return basenodeInfoRepository.getByPkId(pkId);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<BasenodeInfo> getPage(int networkId,
                                      int pageNo,
                                      int pageSize) {
        return basenodeInfoRepository.getPage(networkId,
                                              pageNo,
                                              pageSize);
    }

}