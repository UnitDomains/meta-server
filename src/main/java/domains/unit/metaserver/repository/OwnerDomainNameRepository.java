package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.OwnerDomainName;
import domains.unit.metaserver.model.Page;

import java.util.List;

public interface OwnerDomainNameRepository {
    Page<OwnerDomainName> getRegistrantDomainsPage(int networkId, String address, int pageNo, int pageSize);

    Page<OwnerDomainName> getControllerDomainsPage(int networkId, String address, int pageNo, int pageSize);

    List<OwnerDomainName> getReverseRecordDomains(int networkId, String address);

    OwnerDomainName getOwnerDomainNameByLabel(int networkId, String label);
}
