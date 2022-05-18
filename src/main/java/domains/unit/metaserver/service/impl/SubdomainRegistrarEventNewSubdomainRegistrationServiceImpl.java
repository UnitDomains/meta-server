package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainRegistrarEventNewSubdomainRegistration;
import domains.unit.metaserver.repository.SubdomainRegistrarEventNewSubdomainRegistrationRepository;
import domains.unit.metaserver.service.SubdomainRegistrarEventNewSubdomainRegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubdomainRegistrarEventNewSubdomainRegistrationServiceImpl implements
                                                                        SubdomainRegistrarEventNewSubdomainRegistrationService {
    private final SubdomainRegistrarEventNewSubdomainRegistrationRepository
            subdomainRegistrarEventNewSubdomainRegistrationRepository;

    public SubdomainRegistrarEventNewSubdomainRegistrationServiceImpl(
            SubdomainRegistrarEventNewSubdomainRegistrationRepository
                    subdomainRegistrarEventNewSubdomainRegistrationRepository) {
        this.subdomainRegistrarEventNewSubdomainRegistrationRepository =
                subdomainRegistrarEventNewSubdomainRegistrationRepository;
    }
    
    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getByPkId(String pkId) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getByPkId(pkId);
    }

    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getByLabel(int networkId,
                                                                      String label) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getByLabel(networkId,
                                                                                    label);
    }

    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getBySubNodeLabel(int networkId,
                                                                             String subNodeLabel) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getBySubNodeLabel(networkId,
                                                                                           subNodeLabel);
    }


    @Override
    public Page<SubdomainRegistrarEventNewSubdomainRegistration> getPage(int networkId,
                                                                         String label,
                                                                         int pageNo,
                                                                         int pageSize) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getPage(networkId,
                                                                                 label,
                                                                                 pageNo,
                                                                                 pageSize);
    }

    @Override
    public List<SubdomainRegistrarEventNewSubdomainRegistration> getListByOwner(int networkId,
                                                                                String owner) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getListByOwner(networkId,
                                                                                        owner);
    }

}