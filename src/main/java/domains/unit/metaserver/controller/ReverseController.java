package domains.unit.metaserver.controller;

import domains.unit.metaserver.model.PriceInfo;
import domains.unit.metaserver.service.ReverseInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reverse/v1")
public class ReverseController {
    private final ReverseInfoService reverseInfoService;

    public ReverseController(ReverseInfoService reverseInfoService) {
        this.reverseInfoService = reverseInfoService;
    }


    @GetMapping("reverse")
    public PriceInfo getControllerDomainsPage(@RequestParam(value = "networkId") int networkId,
                                              @RequestParam(value = "domainName") String domainName) {

        return reverseInfoService.getReverseInfo(networkId,
                                                 domainName);
    }


}