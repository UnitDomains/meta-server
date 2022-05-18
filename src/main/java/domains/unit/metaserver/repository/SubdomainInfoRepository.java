package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainInfo;

import java.util.List;

public interface SubdomainInfoRepository {


    Page<SubdomainInfo> getPage(int networkId,
                                String label,
                                int pageNo,
                                int pageSize);

    List<SubdomainInfo> getListByOwner(int networkId,
                                       String owner);

    SubdomainInfo getBySubNodeLabel(int networkId,
                                    String subNodeLabel);
}