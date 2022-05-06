package domains.unit.metaserver.controller;

import domains.unit.metaserver.model.metadata.MetaData;
import domains.unit.metaserver.service.MetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MetadataController {
    private final MetadataService metadataService;

    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("{networkName}/{contractAddress:0x[a-fA-F0-9]{40}}")
    public ResponseEntity<MetaData> getContractMetaData(@PathVariable @NonNull final String networkName,
                                                        @PathVariable @NonNull final String contractAddress,
                                                        @PathVariable @NonNull final String tokenId) {
        return metadataService.getMetadata(networkName, contractAddress, tokenId);
    }

    @GetMapping("{networkName}/{contractAddress:0x[a-fA-F0-9]{40}}/{tokenId}")
    public ResponseEntity<MetaData> getMetaData(@PathVariable @NonNull final String networkName,
                                                @PathVariable @NonNull final String contractAddress,
                                                @PathVariable @NonNull final String tokenId) {
        return metadataService.getMetadata(networkName, contractAddress, tokenId);
    }

    @GetMapping("{networkName}/{contractAddress:0x[a-fA-F0-9]{40}}/{tokenId}/image")
    public ResponseEntity<String> getMetaDataImage(@PathVariable @NonNull final String networkName,
                                                   @PathVariable @NonNull final String contractAddress,
                                                   @PathVariable @NonNull final String tokenId) {
        return metadataService.getMetaDataImage(networkName, contractAddress, tokenId);
    }
}