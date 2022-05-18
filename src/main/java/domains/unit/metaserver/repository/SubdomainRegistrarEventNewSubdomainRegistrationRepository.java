package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainRegistrarEventNewSubdomainRegistration;

import java.util.List;

public interface SubdomainRegistrarEventNewSubdomainRegistrationRepository {

    int getCount(int networkId);

    SubdomainRegistrarEventNewSubdomainRegistration getByPkId(String pkId);

    SubdomainRegistrarEventNewSubdomainRegistration getByLabel(int networkId, String label);

    SubdomainRegistrarEventNewSubdomainRegistration getBySubNodeLabel(int networkId, String subNodeLabel);

    Page<SubdomainRegistrarEventNewSubdomainRegistration> getPage(int networkId, String label, int pageNo, int pageSize);

    List<SubdomainRegistrarEventNewSubdomainRegistration> getListByOwner(int networkId, String owner);

}