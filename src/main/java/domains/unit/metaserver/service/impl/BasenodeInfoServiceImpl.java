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


    @Override
    public BasenodeInfo getByPkId(String pkId) {
        return basenodeInfoRepository.getByPkId(pkId);
    }


    @Override
    public Page<BasenodeInfo> getPage(int networkId,
                                      int pageNo,
                                      int pageSize) {
        return basenodeInfoRepository.getPage(networkId,
                                              pageNo,
                                              pageSize);
    }

}