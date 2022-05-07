package domains.unit.metaserver.service;

import domains.unit.metaserver.model.OwnSubDomainName;
import domains.unit.metaserver.model.OwnerDomainName;
import domains.unit.metaserver.model.Page;

import java.util.List;

public interface DomainsService {
    Page<OwnerDomainName> getRegistrantDomainsPage(int networkId, String address, int pageNo, int pageSize);

    Page<OwnerDomainName> getControllerDomainsPage(int networkId, String address, int pageNo, int pageSize);

    List<OwnerDomainName> getReverseRecordDomains(int networkId, String address);

    Page<OwnSubDomainName> getSubdomainsPage(int networkId, String label, Integer pageNo, Integer pageSize);
}
