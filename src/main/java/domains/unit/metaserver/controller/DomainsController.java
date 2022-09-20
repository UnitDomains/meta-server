package domains.unit.metaserver.controller;

import domains.unit.metaserver.model.DomainInfo;
import domains.unit.metaserver.model.OwnSubDomainName;
import domains.unit.metaserver.model.Page;
import domains.unit.metaserver.model.SubdomainInfo;
import domains.unit.metaserver.service.DomainsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("domains/v1")
public class DomainsController {
    private final DomainsService domainsService;


    public DomainsController(DomainsService domainsService) {
        this.domainsService = domainsService;
    }

    @GetMapping("domain")
    public DomainInfo getDomain(@RequestParam(value = "networkId") int networkId,
                                @RequestParam(value = "domainName") String domainName) {

        return domainsService.getDomain(networkId,
                                        domainName);
    }


    @GetMapping("controller")
    public Page<DomainInfo> getControllerDomainsPage(@RequestParam(value = "networkId") int networkId,
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
    public Page<DomainInfo> getRegistrantDomainsPage(@RequestParam(value = "networkId") int networkId,
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

    @GetMapping("subdomain")
    public SubdomainInfo getSubdomainInfo(@RequestParam(value = "networkId") int networkId,
                                          @RequestParam(value = "subDomain") String subDomain,
                                          @RequestParam(value = "subNodeLabel") String subNodeLabel,
                                          @RequestParam(value = "node") String node) {


        return domainsService.getSubdomainInfo(networkId,
                                               subDomain,
                                               subNodeLabel,
                                               node);
    }

    @GetMapping("domainnamescount")
    public int getDomainNamesCount(@RequestParam(value = "networkId") int networkId) {


        return domainsService.getDomainNamesCount(networkId);
    }

    @GetMapping("domainownerscount")
    public int getDomainOwnersCount(@RequestParam(value = "networkId") int networkId) {


        return domainsService.getDomainOwnersCount(networkId);
    }

}