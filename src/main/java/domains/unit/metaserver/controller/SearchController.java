package domains.unit.metaserver.controller;

import domains.unit.metaserver.model.DomainInfo;
import domains.unit.metaserver.model.SuggestResult;
import domains.unit.metaserver.service.DomainsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search/v1")
public class SearchController {
    private final DomainsService domainsService;

    public SearchController(DomainsService domainsService) {
        this.domainsService = domainsService;
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