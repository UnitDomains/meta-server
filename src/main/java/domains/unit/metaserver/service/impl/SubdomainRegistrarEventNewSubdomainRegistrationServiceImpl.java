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
    public int getCount() {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getCount();
    }

    /**
     * 根据pkId得到SubdomainRegistrarEventNewSubdomainRegistration
     *
     * @param pkId
     */
    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getByPkId(String pkId) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getByPkId(pkId);
    }

    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getByLabel(String label) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getByLabel(label);
    }

    @Override
    public SubdomainRegistrarEventNewSubdomainRegistration getBySubNodeLabel(String subNodeLabel) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getBySubNodeLabel(subNodeLabel);
    }

    /**
     * 获得指定页面数据
     *
     * @param networkId
     * @param pageNo    页号，从1开始
     * @param pageSize  每页的记录数
     */
    @Override
    public Page<SubdomainRegistrarEventNewSubdomainRegistration> getPage(int networkId, String label, int pageNo, int pageSize) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getPage(networkId, label, pageNo, pageSize);
    }

    @Override
    public List<SubdomainRegistrarEventNewSubdomainRegistration> getListByOwner(int networkId, String owner) {
        return subdomainRegistrarEventNewSubdomainRegistrationRepository.getListByOwner(networkId, owner);
    }

}