package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.PriceInfo;
import domains.unit.metaserver.model.ReverseInfo;
import domains.unit.metaserver.repository.ReverseInfoRepository;
import domains.unit.metaserver.service.ReverseInfoService;
import org.springframework.stereotype.Service;

@Service
public class ReverseInfoServiceImpl implements ReverseInfoService {
    private final ReverseInfoRepository reverseInfoRepository;

    public ReverseInfoServiceImpl(ReverseInfoRepository reverseInfoRepository) {
        this.reverseInfoRepository = reverseInfoRepository;
    }


    @Override
    public int getCount(int networkId) {
        return reverseInfoRepository.getCount(networkId);
    }

    /**
     * 根据pkId得到ReverseInfo
     *
     * @param pkId
     */
    @Override
    public ReverseInfo getByPkId(String pkId) {
        return reverseInfoRepository.getByPkId(pkId);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<ReverseInfo> getPage(int networkId,
                                     int pageNo,
                                     int pageSize) {
        return reverseInfoRepository.getPage(networkId,
                                             pageNo,
                                             pageSize);
    }

    @Override
    public PriceInfo getReverseInfo(int networkId,
                                    String domainName) {
        return null;
    }

}