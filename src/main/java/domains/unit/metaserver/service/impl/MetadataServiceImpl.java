package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.DomainInfo;
import domains.unit.metaserver.model.metadata.MetaData;
import domains.unit.metaserver.model.metadata.MetaDataAttribute;
import domains.unit.metaserver.service.DomainsService;
import domains.unit.metaserver.service.MetadataService;
import domains.unit.metaserver.utility.DomainName;
import domains.unit.metaserver.utility.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Service
public class MetadataServiceImpl implements MetadataService {

    private final static String SVG_TEMPLATE = """
            <svg xmlns='http://www.w3.org/2000/svg' width='1200px' height='1200px' viewBox='0 0 1200 1200'>
                <rect fill='none' width='1200' height='1200' />
                <defs>
                    <radialGradient id='a' cx='0' cy='0' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C0}' />
                        <stop offset='1' stop-color='#{C1}' stop-opacity='0' />
                    </radialGradient>
                    <radialGradient id='b' cx='600' cy='0' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C2}' />
                        <stop offset='1' stop-color='#{C3}' stop-opacity='0' />
                    </radialGradient>
                    <radialGradient id='c' cx='1200' cy='0' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C4}' />
                        <stop offset='1' stop-color='#{C5}' stop-opacity='0' />
                    </radialGradient>
                    <radialGradient id='d' cx='0' cy='600' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C6}' />
                        <stop offset='1' stop-color='#{C7}' stop-opacity='0' />
                    </radialGradient>
                    <radialGradient id='e' cx='1200' cy='600' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C8}' />
                        <stop offset='1' stop-color='#{C9}' stop-opacity='0' />
                    </radialGradient>
                    <radialGradient id='f' cx='0' cy='1200' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C10}' />
                        <stop offset='1' stop-color='#{C11}' stop-opacity='0' />
                    </radialGradient>
                    <radialGradient id='g' cx='600' cy='1200' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C12}' />
                        <stop offset='1' stop-color='#{C13}' stop-opacity='0' />
                    </radialGradient>
                    <radialGradient id='h' cx='1200' cy='1200' r='1000' gradientUnits='userSpaceOnUse'>
                        <stop offset='0' stop-color='#{C14}' />
                        <stop offset='1' stop-color='#{C15}' stop-opacity='0' />
                    </radialGradient>
                    <linearGradient id="rainbow" gradientUnits="userSpaceOnUse">
                        <stop offset="5%" stop-color="#908bca" />
                        <stop offset="95%" stop-color="#0429a3" />
                    </linearGradient>
                </defs>
                <rect fill='url(#a)' width='1200' height='1200' />
                <rect fill='url(#b)' width='1200' height='1200' />
                <rect fill='url(#c)' width='1200' height='1200' />
                <rect fill='url(#d)' width='1200' height='1200' />
                <rect fill='url(#e)' width='1200' height='1200' />
                <rect fill='url(#f)' width='1200' height='1200' />
                <rect fill='url(#g)' width='1200' height='1200' />
                <rect fill='url(#h)' width='1200' height='1200' />
                <style>
                    .domains {
                        display: flex;
                        position: relative;
                        font: bold 100px sans-serif;
                        color: white;
                        word-break: break-all;
                        top: 50%;
                        left: 50%;
                        transform: translate(-50%, -50%);
                        justify-content: center;
                    }
                    .title {
                        font: bold 100px sans-serif;
                        fill: url(#rainbow);
                    }
                </style>
                <text x="250" y="200" class="title">Unit Domains</text>
                <switch>
                    <foreignObject x="100" y="400" width="1000" height="800">
                        <span xmlns="http://www.w3.org/1999/xhtml" class="domains">
                            {DOMAINS}
                        </span>
                    </foreignObject>
                    <text x="20" y="20">Your SVG viewer cannot display html.</text>
                </switch>
            </svg>
            """;


    DomainsService domainsService;

    public MetadataServiceImpl(
            DomainsService domainsService) {

        this.domainsService = domainsService;
    }

    private String getImage(String name,
                            String tokenId) {


        if (tokenId.length() == 66)
            tokenId = tokenId.substring(2);

        if (tokenId.length() != 64)
            return "";


        String image = SVG_TEMPLATE;
        image = image.replace("{C0}",
                              tokenId.substring(0,
                                                4));
        image = image.replace("{C1}",
                              tokenId.substring(4,
                                                8));
        image = image.replace("{C2}",
                              tokenId.substring(8,
                                                12));
        image = image.replace("{C3}",
                              tokenId.substring(12,
                                                16));
        image = image.replace("{C4}",
                              tokenId.substring(16,
                                                20));
        image = image.replace("{C5}",
                              tokenId.substring(20,
                                                24));
        image = image.replace("{C6}",
                              tokenId.substring(24,
                                                28));
        image = image.replace("{C7}",
                              tokenId.substring(28,
                                                32));
        image = image.replace("{C8}",
                              tokenId.substring(32,
                                                36));
        image = image.replace("{C9}",
                              tokenId.substring(36,
                                                40));
        image = image.replace("{C10}",
                              tokenId.substring(40,
                                                44));
        image = image.replace("{C11}",
                              tokenId.substring(44,
                                                48));
        image = image.replace("{C12}",
                              tokenId.substring(48,
                                                52));
        image = image.replace("{C13}",
                              tokenId.substring(52,
                                                56));
        image = image.replace("{C14}",
                              tokenId.substring(56,
                                                60));
        image = image.replace("{C15}",
                              tokenId.substring(60));

        image = image.replace("{DOMAINS}",
                              name);

        return image;
    }


    private int getNetworkId(String networkName) {
        switch (networkName) {
            case "mainnet":
            case "Mainnet":
                return 1;
            case "ropsten":
            case "Ropsten":
                return 3;
            case "rinkeby":
            case "Rinkeby":
                return 4;
        }
        return 0;
    }

    private String getContractAddress(int networkId) {
        switch (networkId) {
            case 1:
                return "";
            case 3:
                return "";
            case 4:
                return "0xE77508ED0a40559717563d6E0DFc74C067AB30c1";
        }
        return null;
    }

    @Override
    public ResponseEntity<MetaData> getMetadata(String networkName,
                                                String contractAddress,
                                                String tokenId) {

        System.out.println(tokenId);


        if (networkName == null || contractAddress == null || tokenId == null ||
                networkName.length() == 0 || contractAddress.length() == 0 || tokenId.length() == 0)
            return ResponseEntityUtils.badRequest(null,
                                                  "null object");

        int networkId = getNetworkId(networkName);


        if (networkId <= 0)
            return ResponseEntityUtils.badRequest(null,
                                                  "error network name");

        if (!contractAddress.equals(getContractAddress(networkId)))
            return ResponseEntityUtils.badRequest(null,
                                                  "error contract address");


        DomainInfo domainInfo = domainsService.getDomainByTokenId(networkId,
                                                                  tokenId);

        if (domainInfo == null)
            return ResponseEntityUtils.badRequest(null,
                                                  "error value of tokenId");

        if (domainInfo.getBaseNodeIndex() >= DomainName.DOMAIN_NAMES.length)
            return ResponseEntityUtils.internalServerError(null,
                                                           "error value of base node index");

        MetaData metaData = new MetaData();
        String domainName =
                domainInfo.getName() + "." + DomainName.DOMAIN_NAMES[domainInfo.getBaseNodeIndex()];
        metaData.setName(domainName);

        metaData.setImage("https://metadata.unit.domains/" + networkName + "/" + contractAddress + "/" + tokenId + "/image");

        metaData.setImage_url("https://metadata.unit.domains/" + networkName + "/" + contractAddress + "/" + tokenId + "/image");

        String str = getImage(domainInfo.getName() +
                                      "." +
                                      DomainName.DOMAIN_NAMES[domainInfo.getBaseNodeIndex()],
                              tokenId);

        metaData.setImage_data(str);

        metaData.setDescription(domainName + ",A distributed, open, and extensible naming system based on the Ethereum blockchain.");
        metaData.setName_length(domainInfo.getName().length());
        metaData.setUrl("https://www.unit.domains/name/" + domainName);


        List<MetaDataAttribute> metaDataAttributeList = new ArrayList<>();


        MetaDataAttribute metaDataAttributeExpiration = new MetaDataAttribute();
        metaDataAttributeExpiration.setTrait_type("Expiration Date");
        metaDataAttributeExpiration.setDisplay_type("date");
        metaDataAttributeExpiration.setValue(domainInfo.getExpires());
        metaDataAttributeList.add(metaDataAttributeExpiration);

        MetaDataAttribute metaDataAttributeRegistration = new MetaDataAttribute();
        metaDataAttributeRegistration.setTrait_type("Registration Date");
        metaDataAttributeRegistration.setDisplay_type("date");
        if (domainInfo.getTimestamp() != null)
            metaDataAttributeRegistration.setValue(BigInteger.valueOf(domainInfo.getTimestamp()
                                                                                .getTime()));
        metaDataAttributeList.add(metaDataAttributeRegistration);

        MetaDataAttribute metaDataAttributeCreated = new MetaDataAttribute();
        metaDataAttributeCreated.setTrait_type("Created Date");
        metaDataAttributeCreated.setDisplay_type("date");
        metaDataAttributeCreated.setValue(null);
        metaDataAttributeList.add(metaDataAttributeCreated);


        if (metaDataAttributeList.size() > 0)
            metaData.setAttributes(metaDataAttributeList);

        return ResponseEntity.ok(metaData);
    }

    @Override
    public ResponseEntity<String> getMetaDataImage(String networkName,
                                                   String contractAddress,
                                                   String tokenId) {


        if (networkName == null || contractAddress == null || tokenId == null ||
                networkName.length() == 0 || contractAddress.length() == 0 || tokenId.length() == 0)
            return ResponseEntityUtils.badRequest(null,
                                                  "null object");


        int networkId = getNetworkId(networkName);


        if (networkId <= 0)
            return ResponseEntityUtils.badRequest(null,
                                                  "error network name");

        if (!contractAddress.equals(getContractAddress(networkId)))
            return ResponseEntityUtils.badRequest(null,
                                                  "error contract address");

        DomainInfo domainInfo = domainsService.getDomainByTokenId(networkId,
                                                                  tokenId);

        if (domainInfo == null)
            return ResponseEntityUtils.badRequest(null,
                                                  "error value of tokenId");

        if (domainInfo.getBaseNodeIndex() >= DomainName.DOMAIN_NAMES.length)
            return ResponseEntityUtils.internalServerError(null,
                                                           "error value of base node index");

        String str = getImage(domainInfo.getName() +
                                      "." +
                                      DomainName.DOMAIN_NAMES[domainInfo.getBaseNodeIndex()],
                              tokenId);

        return ResponseEntity.ok(str);
    }


}
