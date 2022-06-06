package domains.unit.metaserver.controller;

import domains.unit.metaserver.model.DomainInfo;
import domains.unit.metaserver.model.ReverseInfo;
import domains.unit.metaserver.service.DomainsService;
import domains.unit.metaserver.service.ReverseInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reverse/v1")
public class ReverseController {
    private final ReverseInfoService reverseInfoService;
    private final DomainsService domainsService;

    public ReverseController(ReverseInfoService reverseInfoService,
                             DomainsService domainsService) {
        this.reverseInfoService = reverseInfoService;
        this.domainsService = domainsService;
    }

    @GetMapping("reverse")
    public ReverseInfo getReverseRecord(@RequestParam(value = "networkId") int networkId,
                                        @RequestParam(value = "address") String address) {

        return reverseInfoService.getReverseRecord(networkId,
                                                   address);
    }

    @GetMapping("reverseList")
    public List<DomainInfo> getReverseRecordDomains(@RequestParam(value = "networkId") int networkId,
                                                    @RequestParam(value = "address") String address) {

        return domainsService.getReverseRecordDomains(networkId,
                                                      address);
    }


}