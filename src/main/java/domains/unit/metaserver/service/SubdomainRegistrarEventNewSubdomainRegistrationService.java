package domains.unit.metaserver.service;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainRegistrarEventNewSubdomainRegistration;

import java.util.List;

public interface SubdomainRegistrarEventNewSubdomainRegistrationService {


    int getCount();

    SubdomainRegistrarEventNewSubdomainRegistration getByPkId(String pkId);

    SubdomainRegistrarEventNewSubdomainRegistration getByLabel(String label);

    SubdomainRegistrarEventNewSubdomainRegistration getBySubNodeLabel(String subNodeLabel);

    Page<SubdomainRegistrarEventNewSubdomainRegistration> getPage(int networkId, String label, int pageNo, int pageSize);

    List<SubdomainRegistrarEventNewSubdomainRegistration> getListByOwner(int networkId, String owner);

}