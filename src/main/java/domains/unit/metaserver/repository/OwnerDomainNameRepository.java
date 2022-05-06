package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.OwnerDomainName;
import domains.unit.metaserver.model.Page;

import java.util.List;

public interface OwnerDomainNameRepository {
    Page<OwnerDomainName> getRegistrantDomainsPage(String address, int pageNo, int pageSize);

    Page<OwnerDomainName> getControllerDomainsPage(String address, int pageNo, int pageSize);

    List<OwnerDomainName> getReverseRecordDomains(String address);

    OwnerDomainName getOwnerDomainNameByLabel(String label);
}
