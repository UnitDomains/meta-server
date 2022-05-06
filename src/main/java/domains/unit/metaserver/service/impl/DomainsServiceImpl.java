package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.*;
import domains.unit.metaserver.repository.OwnerDomainNameRepository;
import domains.unit.metaserver.service.DomainsService;
import domains.unit.metaserver.service.EnsRegistryEventNewOwnerService;
import domains.unit.metaserver.service.SubdomainRegistrarEventNewSubdomainRegistrationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainsServiceImpl implements DomainsService {

    OwnerDomainNameRepository ownerDomainNameRepository;
    EnsRegistryEventNewOwnerService ensRegistryEventNewOwnerService;
    SubdomainRegistrarEventNewSubdomainRegistrationService subdomainRegistrarEventNewSubdomainRegistrationService;

    public DomainsServiceImpl(OwnerDomainNameRepository ownerDomainNameRepository,
                              EnsRegistryEventNewOwnerService ensRegistryEventNewOwnerService,
                              SubdomainRegistrarEventNewSubdomainRegistrationService subdomainRegistrarEventNewSubdomainRegistrationService) {
        this.ownerDomainNameRepository = ownerDomainNameRepository;
        this.ensRegistryEventNewOwnerService = ensRegistryEventNewOwnerService;
        this.subdomainRegistrarEventNewSubdomainRegistrationService = subdomainRegistrarEventNewSubdomainRegistrationService;
    }

    Page<String> convertEnsRegistryEventNewOwner2String(Page<EnsRegistryEventNewOwner> ensRegistryEventNewOwnerPage, int pageNo, int pageSize) {
        if (ensRegistryEventNewOwnerPage == null) return null;

        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);

        List<String> list = new ArrayList<>();


        for (EnsRegistryEventNewOwner ensRegistryEventNewOwner : ensRegistryEventNewOwnerPage.getResult()) {

            if (ensRegistryEventNewOwner != null) list.add(ensRegistryEventNewOwner.getNode());
        }


        return new Page<>(startIndex, ensRegistryEventNewOwnerPage.getTotalCount(), pageSize, list);
    }

    @Override
    public Page<OwnerDomainName> getRegistrantDomainsPage(String address, int pageNo, int pageSize) {
        return ownerDomainNameRepository.getRegistrantDomainsPage(address, pageNo, pageSize);


    }

    @Override
    public Page<OwnerDomainName> getControllerDomainsPage(String address, int pageNo, int pageSize) {

        return ownerDomainNameRepository.getControllerDomainsPage(address, pageNo, pageSize);
    }

    @Override
    public List<OwnerDomainName> getReverseRecordDomains(String address) {
        List<OwnerDomainName> list1 = ownerDomainNameRepository.getReverseRecordDomains(address);

        List<SubdomainRegistrarEventNewSubdomainRegistration> subdomainRegistrarEventNewSubdomainRegistrationList =
                subdomainRegistrarEventNewSubdomainRegistrationService.getListByOwner(address);

        if (subdomainRegistrarEventNewSubdomainRegistrationList != null && subdomainRegistrarEventNewSubdomainRegistrationList.size() > 0) {
            List<OwnerDomainName> list2 = new ArrayList<>();
            for (SubdomainRegistrarEventNewSubdomainRegistration subdomain
                    : subdomainRegistrarEventNewSubdomainRegistrationList) {

                if (subdomain != null) {
                    OwnerDomainName ownerDomainName = new OwnerDomainName();


                    OwnSubDomainName resultOwnSubDomainName = new OwnSubDomainName();
                    OwnSubDomainName subname = getName(subdomain, resultOwnSubDomainName);
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

    Page<OwnSubDomainName> convertSubdomainRegistrarEventNewSubdomainRegistration2OwnSubDomainName(
            Page<SubdomainRegistrarEventNewSubdomainRegistration>
                    subdomainPage,
            int pageNo, int pageSize) {
        if (subdomainPage == null) return null;

        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);

        List<OwnSubDomainName> list = new ArrayList<>();


        for (SubdomainRegistrarEventNewSubdomainRegistration subdomain
                : subdomainPage.getResult()) {

            if (subdomain != null) {
                OwnSubDomainName ownSubDomainName = new OwnSubDomainName();
                ownSubDomainName.setLabel(subdomain.getLabel());
                ownSubDomainName.setSubNodeLabel(subdomain.getSubNodeLabel());
                ownSubDomainName.setSubDomain(subdomain.getSubDomain());

                OwnSubDomainName resultOwnSubDomainName = new OwnSubDomainName();
                OwnSubDomainName subname = getName(subdomain, resultOwnSubDomainName);
                if (subname != null) {
                    ownSubDomainName.setName(subname.getName());
                    ownSubDomainName.setBaseNodeIndex(subname.getBaseNodeIndex());
                }

                list.add(ownSubDomainName);
            }
        }


        return new Page<>(startIndex, subdomainPage.getTotalCount(), pageSize, list);
    }

    private OwnSubDomainName getName(SubdomainRegistrarEventNewSubdomainRegistration subdomain, OwnSubDomainName resultOwnSubDomainName) {
        if (subdomain == null) return null;


        SubdomainRegistrarEventNewSubdomainRegistration parent =
                subdomainRegistrarEventNewSubdomainRegistrationService.getBySubNodeLabel(subdomain.getLabel());


        if (parent == null) {
            OwnerDomainName ownerDomainName = ownerDomainNameRepository.getOwnerDomainNameByLabel(subdomain.getLabel());

            if (ownerDomainName != null) {
                if (resultOwnSubDomainName.getName() != null)
                    resultOwnSubDomainName.setName(resultOwnSubDomainName.getName()
                            + "." + subdomain.getSubDomain()
                            + "." + ownerDomainName.getName());
                else
                    resultOwnSubDomainName.setName(subdomain.getSubDomain() + "." + ownerDomainName.getName());
                resultOwnSubDomainName.setBaseNodeIndex(ownerDomainName.getBaseNodeIndex());
                return resultOwnSubDomainName;
            }
            return null;
        }
        if (resultOwnSubDomainName.getName() != null)
            resultOwnSubDomainName.setName(resultOwnSubDomainName.getName() + "." + subdomain.getSubDomain());
        else
            resultOwnSubDomainName.setName(subdomain.getSubDomain());
        return getName(parent, resultOwnSubDomainName);


    }

    @Override
    public Page<OwnSubDomainName> getSubdomainsPage(String label, Integer pageNo, Integer pageSize) {
        Page<SubdomainRegistrarEventNewSubdomainRegistration> subdomainRegistrarEventNewSubdomainRegistrationPage =
                subdomainRegistrarEventNewSubdomainRegistrationService.getPage(label, pageNo, pageSize);

        return convertSubdomainRegistrarEventNewSubdomainRegistration2OwnSubDomainName(
                subdomainRegistrarEventNewSubdomainRegistrationPage, pageNo, pageSize);
    }
}
