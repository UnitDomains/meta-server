package domains.unit.metaserver.controller;

import domains.unit.metaserver.model.PriceInfo;
import domains.unit.metaserver.service.PriceInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("price/v1")
public class PriceController {
    private final PriceInfoService priceInfoService;

    public PriceController(PriceInfoService priceInfoService) {
        this.priceInfoService = priceInfoService;
    }

    @GetMapping("price")
    public PriceInfo getControllerDomainsPage(@RequestParam(value = "networkId") int networkId) {

        return priceInfoService.getPrice(networkId);
    }

    @GetMapping("rent")
    public String getControllerDomainsPage(@RequestParam(value = "networkId") int networkId,
                                           @RequestParam(value = "domainName") String domainName) {

        return priceInfoService.getRentYearsPrice(networkId,
                                                  domainName,
                                                  1);
    }


    @GetMapping("rentyears")
    public String getControllerDomainsPage(@RequestParam(value = "networkId") int networkId,
                                           @RequestParam(value = "domainName") String domainName,
                                           @RequestParam(value = "years") Integer years) {

        return priceInfoService.getRentYearsPrice(networkId,
                                                  domainName,
                                                  years);
    }

    @GetMapping("register")
    public String getRegistrantDomainsPage(@RequestParam(value = "networkId") int networkId,
                                           @RequestParam(value = "domainName") String domainName) {

        return priceInfoService.GetRegisterPrice(networkId,
                                                 domainName);
    }


}