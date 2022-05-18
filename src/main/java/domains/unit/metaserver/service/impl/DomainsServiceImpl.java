package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.*;
import domains.unit.metaserver.repository.DomainInfoRepository;
import domains.unit.metaserver.repository.OwnerDomainNameRepository;
import domains.unit.metaserver.repository.SubdomainInfoRepository;
import domains.unit.metaserver.service.DomainsService;
import domains.unit.metaserver.service.EnsRegistryEventNewOwnerService;
import domains.unit.metaserver.utility.DomainName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainsServiceImpl implements DomainsService {

    private final DomainInfoRepository domainInfoRepository;
    OwnerDomainNameRepository ownerDomainNameRepository;
    EnsRegistryEventNewOwnerService ensRegistryEventNewOwnerService;
    SubdomainInfoRepository subdomainInfoRepository;


    public DomainsServiceImpl(OwnerDomainNameRepository ownerDomainNameRepository,
                              EnsRegistryEventNewOwnerService ensRegistryEventNewOwnerService,
                              SubdomainInfoRepository subdomainInfoRepository,
                              DomainInfoRepository domainInfoRepository) {
        this.ownerDomainNameRepository = ownerDomainNameRepository;
        this.ensRegistryEventNewOwnerService = ensRegistryEventNewOwnerService;
        this.subdomainInfoRepository = subdomainInfoRepository;
        this.domainInfoRepository = domainInfoRepository;
    }


    @Override
    public Page<OwnerDomainName> getRegistrantDomainsPage(int networkId,
                                                          String address,
                                                          int pageNo,
                                                          int pageSize) {
        return ownerDomainNameRepository.getRegistrantDomainsPage(networkId,
                                                                  address,
                                                                  pageNo,
                                                                  pageSize);


    }

    @Override
    public Page<OwnerDomainName> getControllerDomainsPage(int networkId,
                                                          String address,
                                                          int pageNo,
                                                          int pageSize) {

        return ownerDomainNameRepository.getControllerDomainsPage(networkId,
                                                                  address,
                                                                  pageNo,
                                                                  pageSize);
    }

    @Override
    public List<OwnerDomainName> getReverseRecordDomains(int networkId,
                                                         String address) {
        List<OwnerDomainName> list1 = ownerDomainNameRepository.getReverseRecordDomains(networkId,
                                                                                        address);

        List<SubdomainInfo> subdomainInfoList =
                subdomainInfoRepository.getListByOwner(networkId,
                                                       address);

        if (subdomainInfoList != null && subdomainInfoList.size() > 0) {
            List<OwnerDomainName> list2 = new ArrayList<>();
            for (SubdomainInfo subdomainInfo : subdomainInfoList) {

                if (subdomainInfo != null) {
                    OwnerDomainName ownerDomainName = new OwnerDomainName();


                    OwnSubDomainName resultOwnSubDomainName = new OwnSubDomainName();
                    OwnSubDomainName subname = getName(networkId,
                                                       subdomainInfo,
                                                       resultOwnSubDomainName);

                    if (subname != null) {

                        ownerDomainName.setName(subname.getName());
                        ownerDomainName.setBaseNodeIndex(subname.getBaseNodeIndex());
                    }

                    list2.add(ownerDomainName);
                }

            }
            list1.addAll(list2);
        }
        return list1;

    }

    Page<OwnSubDomainName> convert(
            int networkId,
            Page<SubdomainInfo> subdomainInfoPage,
            int pageNo,
            int pageSize) {
        if (subdomainInfoPage == null) return null;


        int startIndex = Page.getStartOfPage(pageNo,
                                             pageSize);

        List<OwnSubDomainName> list = new ArrayList<>();


        for (SubdomainInfo subdomainInfo
                : subdomainInfoPage.getResult()) {

            if (subdomainInfo != null) {
                OwnSubDomainName ownSubDomainName = new OwnSubDomainName();
                ownSubDomainName.setLabel(subdomainInfo.getLabel());
                ownSubDomainName.setNetworkId(networkId);
                ownSubDomainName.setSubNodeLabel(subdomainInfo.getSubNodeLabel());
                ownSubDomainName.setSubDomain(subdomainInfo.getSubDomain());

                OwnSubDomainName resultOwnSubDomainName = new OwnSubDomainName();
                resultOwnSubDomainName.setNetworkId(networkId);
                OwnSubDomainName subname = getName(networkId,
                                                   subdomainInfo,
                                                   resultOwnSubDomainName);
                if (subname != null) {
                    ownSubDomainName.setName(subname.getName());
                    ownSubDomainName.setBaseNodeIndex(subname.getBaseNodeIndex());
                }

                list.add(ownSubDomainName);
            }
        }


        return new Page<>(startIndex,
                          subdomainInfoPage.getTotalCount(),
                          pageSize,
                          list);
    }

    private OwnSubDomainName getName(int networkId,
                                     SubdomainInfo subdomainInfo,
                                     OwnSubDomainName resultOwnSubDomainName) {
        if (subdomainInfo == null) return null;


        SubdomainInfo parent =
                subdomainInfoRepository.getBySubNodeLabel(networkId,
                                                          subdomainInfo.getLabel());


        if (parent == null) {
            OwnerDomainName ownerDomainName =
                    ownerDomainNameRepository.getOwnerDomainNameByLabel(
                            networkId,
                            subdomainInfo.getLabel());


            if (ownerDomainName != null) {
                if (resultOwnSubDomainName.getName() != null)
                    resultOwnSubDomainName.setName(resultOwnSubDomainName.getName()
                                                           + "." + subdomainInfo.getSubDomain()
                                                           + "." + ownerDomainName.getName());
                else
                    resultOwnSubDomainName.setName(subdomainInfo.getSubDomain() + "." + ownerDomainName.getName());
                resultOwnSubDomainName.setBaseNodeIndex(ownerDomainName.getBaseNodeIndex());
                return resultOwnSubDomainName;
            }
            return null;
        }
        if (resultOwnSubDomainName.getName() != null)
            resultOwnSubDomainName.setName(resultOwnSubDomainName.getName() + "." + subdomainInfo.getSubDomain());
        else
            resultOwnSubDomainName.setName(subdomainInfo.getSubDomain());
        return getName(networkId,
                       parent,
                       resultOwnSubDomainName);


    }

    @Override
    public Page<OwnSubDomainName> getSubdomainsPage(int networkId,
                                                    String label,
                                                    Integer pageNo,
                                                    Integer pageSize) {
        Page<SubdomainInfo> subdomainInfoPage =
                subdomainInfoRepository.getPage(networkId,
                                                label,
                                                pageNo,
                                                pageSize);

        return convert(networkId,
                       subdomainInfoPage,
                       pageNo,
                       pageSize);
    }


    /**
     * Get domain search results
     *
     * @param networkId
     * @param searchText
     * @return
     */
    @Override
    public List<DomainInfo> getSpecificResultsOfDomains(int networkId,
                                                        String searchText) {
        if (searchText == null || searchText.trim().length() == 0)
            return null;

        String domain = DomainName.getDomain(searchText);//e.g. abc.cat=>abc
        int baseNodeIndex = DomainName.getBaseNodeIndex(searchText);

        if (baseNodeIndex >= 0) {
            return domainInfoRepository.getList(networkId,
                                                domain,
                                                baseNodeIndex);
        }
        return null;
    }

    @Override
    public List<DomainInfo> getNotAvailableResultsOfDomains(int networkId,
                                                            String searchText) {
        if (searchText == null || searchText.trim().length() == 0)
            return null;

        String domain = DomainName.getDomain(searchText);//e.g. abc.cat=>abc


        return domainInfoRepository.getList(networkId,
                                            domain);

    }

    @Override
    public List<SuggestResult> getSuggestResultsOfDomains(int networkId,
                                                          String searchText) {
        if (searchText == null || searchText.trim().length() == 0)
            return null;


        String domain = DomainName.getDomain(searchText);//e.g. abc.cat=>abc

        List<DomainInfo> domainInfoList = domainInfoRepository.getList(networkId,
                                                                       domain);


        List<SuggestResult> suggestResultList = new ArrayList<>();

        for (String str : DomainName.DOMAIN_NAMES) {
            if (!hasRegister(str,
                             domainInfoList)) {
                SuggestResult suggestResult = new SuggestResult();
                suggestResult.setName(domain + "." + str);
                suggestResult.setBaseNodeIndex(DomainName.getBaseNodeIndex(str));
                suggestResultList.add(suggestResult);
            }
        }

        return suggestResultList;
    }

    private boolean hasRegister(String str,
                                List<DomainInfo> domainInfoList) {
        for (DomainInfo domainInfo : domainInfoList) {
            if (DomainName.DOMAIN_NAMES[domainInfo.getBaseNodeIndex()].equals(str)) return true;
        }
        return false;
    }


}
