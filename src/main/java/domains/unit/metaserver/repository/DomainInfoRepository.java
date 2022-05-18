package domains.unit.metaserver.repository;

import domains.unit.metaserver.model.DomainInfo;
import domains.unit.metaserver.model.Page;

import java.util.List;

public interface DomainInfoRepository {

    int getCount(int networkId);

    DomainInfo getByPkId(String pkId);

    Page<DomainInfo> getPage(int networkId,
                             int pageNo,
                             int pageSize);

    List<DomainInfo> getList(int networkId,
                             String searchText);

    List<DomainInfo> getList(int networkId,
                             String searchText,
                             int baseNodeIndex);
}