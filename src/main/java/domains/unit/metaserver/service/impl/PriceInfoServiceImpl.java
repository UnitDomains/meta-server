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
    public String getRentYearsPrice(int networkId,
                                    String domainName,
                                    Integer years) {
        return null;
    }

    @Override
    public String GetRegisterPrice(int networkId,
                                   String domainName) {

        if (domainName == null || domainName.length() == 0)
            return null;

        PriceInfo priceInfo = priceInfoRepository.getByNetworkId(networkId);

        String price = priceInfo.getRegisterPrice();
        if (price == null || price.length() == 0) return null;
        if (price.charAt(0) != '[' || price.charAt(price.length() - 1) != ']') return null;
        price = price.substring(1,
                                price.length() - 1);

        String[] priceArray = price.split(",");
        if (priceArray.length == 0)
            return null;

        if (domainName.length() > priceArray.length)
            return priceArray[priceArray.length - 1];
        return priceArray[domainName.length() - 1];
    }

    @Override
    public PriceInfo getPrice(int networkId) {
        return priceInfoRepository.getByNetworkId(networkId);
    }

}