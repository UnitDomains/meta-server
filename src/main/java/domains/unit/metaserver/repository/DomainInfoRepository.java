package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.DomainInfo;
import domains.unit.metaserver.model.Page;

import java.util.List;

public interface DomainInfoRepository {

    int getCount(int networkId);

    DomainInfo getByPkId(String pkId);

    DomainInfo getDomain(int networkId,
                         String domain,
                         int baseNodeIndex);

    DomainInfo getByLabel(int networkId,
                          String label);


    Page<DomainInfo> getPage(int networkId,
                             int pageNo,
                             int pageSize);

    List<DomainInfo> getList(int networkId,
                             String searchText);

    List<DomainInfo> getList(int networkId,
                             String searchText,
                             int baseNodeIndex);

    List<DomainInfo> getListByEthAddress(int networkId,
                                         String ethAddress);

    Page<DomainInfo> getControllerDomainsPage(int networkId,
                                              String address,
                                              int pageNo,
                                              int pageSize);

    Page<DomainInfo> getRegistrantDomainsPage(int networkId,
                                              String address,
                                              int pageNo,
                                              int pageSize);


}