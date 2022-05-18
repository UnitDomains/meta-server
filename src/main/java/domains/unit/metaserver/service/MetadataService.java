package domains.unit.metaserver.service;

import domains.unit.metaserver.model.metadata.MetaData;
import org.springframework.http.ResponseEntity;

public interface MetadataService {
    ResponseEntity<MetaData> getMetadata(String networkName,
                                         String contractAddress,
                                         String tokenId);

    ResponseEntity<String> getMetaDataImage(String networkName,
                                            String contractAddress,
                                            String tokenId);
}
