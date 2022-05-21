package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.PriceInfo;
import domains.unit.metaserver.repository.PriceInfoRepository;
import domains.unit.metaserver.service.PriceInfoService;
import org.springframework.stereotype.Service;

@Service
public class PriceInfoServiceImpl implements PriceInfoService {
    private final PriceInfoRepository priceInfoRepository;

    public PriceInfoServiceImpl(PriceInfoRepository priceInfoRepository) {
        this.priceInfoRepository = priceInfoRepository;
    }


    @Override
    public int getCount(int networkId) {
        return priceInfoRepository.getCount(networkId);
    }

    /**
     * 根据pkId得到PriceInfo
     *
     * @param pkId
     */
    @Override
    public PriceInfo getByPkId(String pkId) {
        return priceInfoRepository.getByPkId(pkId);
    }

    /**
     * 获得指定页面数据
     *
     * @param pageNo   页号，从1开始
     * @param pageSize 每页的记录数
     */
    @Override
    public Page<PriceInfo> getPage(int networkId,
                                   int pageNo,
                                   int pageSize) {
        return priceInfoRepository.getPage(networkId,
                                           pageNo,
                                           pageSize);
    }


    @Override
    public PriceInfo getRentYearsPrice(int networkId,
                                       String domainName,
                                       Integer years) {
        return null;
    }

    @Override
    public PriceInfo GetRegisterPrice(int networkId,
                                      String domainName) {
        return null;
    }

}