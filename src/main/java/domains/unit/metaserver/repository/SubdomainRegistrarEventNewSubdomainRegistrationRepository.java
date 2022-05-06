package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainRegistrarEventNewSubdomainRegistration;

import java.util.List;

public interface SubdomainRegistrarEventNewSubdomainRegistrationRepository {

    int getCount();

    SubdomainRegistrarEventNewSubdomainRegistration getByPkId(String pkId);

    SubdomainRegistrarEventNewSubdomainRegistration getByLabel(String label);

    SubdomainRegistrarEventNewSubdomainRegistration getBySubNodeLabel(String subNodeLabel);

    Page<SubdomainRegistrarEventNewSubdomainRegistration> getPage(String label, int pageNo, int pageSize);

    List<SubdomainRegistrarEventNewSubdomainRegistration> getListByOwner(String owner);

}