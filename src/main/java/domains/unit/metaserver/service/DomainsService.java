package domains.unit.metaserver.service;

import domains.unit.metaserver.model.OwnSubDomainName;
import domains.unit.metaserver.model.OwnerDomainName;
import domains.unit.metaserver.model.Page;

import java.util.List;

public interface DomainsService {
    Page<OwnerDomainName> getRegistrantDomainsPage(String address, int pageNo, int pageSize);

    Page<OwnerDomainName> getControllerDomainsPage(String address, int pageNo, int pageSize);

    List<OwnerDomainName> getReverseRecordDomains(String address);

    Page<OwnSubDomainName> getSubdomainsPage(String label, Integer pageNo, Integer pageSize);
}
