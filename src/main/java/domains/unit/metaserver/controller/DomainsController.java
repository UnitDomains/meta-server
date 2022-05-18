package domains.unit.metaserver.controller;

import domains.unit.metaserver.model.*;
import domains.unit.metaserver.service.DomainsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("domains/v1")
public class DomainsController {
    private final DomainsService domainsService;

    public DomainsController(DomainsService domainsService) {
        this.domainsService = domainsService;
    }


    @GetMapping("controller")
    public Page<OwnerDomainName> getControllerDomainsPage(@RequestParam(value = "networkId") int networkId,
                                                          @RequestParam(value = "address") String address,
                                                          @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? Page.DEFAULT_PAGE_SIZE : (pageSize > 10 ? pageSize : Page.DEFAULT_PAGE_SIZE);
        return domainsService.getControllerDomainsPage(networkId,
                                                       address,
                                                       pageNo,
                                                       pageSize);
    }

    @GetMapping("registrant")
    public Page<OwnerDomainName> getRegistrantDomainsPage(@RequestParam(value = "networkId") int networkId,
                                                          @RequestParam(value = "address") String address,
                                                          @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? Page.DEFAULT_PAGE_SIZE : (pageSize > 10 ? pageSize : Page.DEFAULT_PAGE_SIZE);
        return domainsService.getRegistrantDomainsPage(networkId,
                                                       address,
                                                       pageNo,
                                                       pageSize);
    }

    @GetMapping("subdomains")
    public Page<OwnSubDomainName> getSubdomainsPage(@RequestParam(value = "networkId") int networkId,
                                                    @RequestParam(value = "label") String label,
                                                    @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                    @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? Page.DEFAULT_PAGE_SIZE : (pageSize > 10 ? pageSize : Page.DEFAULT_PAGE_SIZE);
        return domainsService.getSubdomainsPage(networkId,
                                                label,
                                                pageNo,
                                                pageSize);
    }

    /**
     * 获得指定地址能够设置的反向记录域名
     *
     * @param address
     * @return
     */
    @GetMapping("reverse")
    public List<OwnerDomainName> getReverseRecordDomains(@RequestParam(value = "networkId") int networkId,
                                                         @RequestParam(value = "address") String address) {

        return domainsService.getReverseRecordDomains(networkId,
                                                      address);
    }

    @GetMapping("specific")
    public List<DomainInfo> getSpecificResultsOfDomains(@RequestParam(value = "networkId") int networkId,
                                                        @RequestParam(value = "searchText") String searchText) {

        return domainsService.getSpecificResultsOfDomains(networkId,
                                                          searchText);
    }

    @GetMapping("notavailable")
    public List<DomainInfo> getNotAvailableResultsOfDomains(@RequestParam(value = "networkId") int networkId,
                                                            @RequestParam(value = "searchText") String searchText) {

        return domainsService.getNotAvailableResultsOfDomains(networkId,
                                                              searchText);
    }

    @GetMapping("suggest")
    public List<SuggestResult> getSuggestResultsOfDomains(@RequestParam(value = "networkId") int networkId,
                                                          @RequestParam(value = "searchText") String searchText) {

        return domainsService.getSuggestResultsOfDomains(networkId,
                                                         searchText);
    }


}