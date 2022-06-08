package domains.unit.metaserver.service;

import domains.unit.metaserver.model.*;

import java.util.List;

public interface DomainsService {

    Page<DomainInfo> getRegistrantDomainsPage(int networkId,
                                              String address,
                                              int pageNo,
                                              int pageSize);

    Page<DomainInfo> getControllerDomainsPage(int networkId,
                                              String address,
                                              int pageNo,
                                              int pageSize);

    List<DomainInfo> getReverseRecordDomains(int networkId,
                                             String address);

    Page<OwnSubDomainName> getSubdomainsPage(int networkId,
                                             String label,
                                             Integer pageNo,
                                             Integer pageSize);

    List<DomainInfo> getSpecificResultsOfDomains(int networkId,
                                                 String searchText);

    List<DomainInfo> getNotAvailableResultsOfDomains(int networkId,
                                                     String searchText);

    List<SuggestResult> getSuggestResultsOfDomains(int networkId,
                                                   String searchText);

    DomainInfo getDomain(int networkId,
                         String domainName);

    DomainInfo getDomainByTokenId(int networkId,
                                  String tokenId);

    SubdomainInfo getSubdomainInfo(int networkId,
                                   String subDomain,
                                   String subNodeLabel,
                                   String node);
}
