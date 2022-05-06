package domains.unit.metaserver.service.impl;

import domains.unit.metaserver.model.BaseRegistrarEventNameRegistered;
import domains.unit.metaserver.model.OwnerDomainName;
import domains.unit.metaserver.model.metadata.MetaData;
import domains.unit.metaserver.model.metadata.MetaDataAttribute;
import domains.unit.metaserver.repository.BaseRegistrarEventNameRegisteredRepository;
import domains.unit.metaserver.repository.OwnerDomainNameRepository;
import domains.unit.metaserver.service.MetadataService;
import domains.unit.webserver.utility.ResponseEntityUtils;
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
    private static final String[] DOMAIN_NAMES = "about,area,beyond,book,cat,cell,dream,dog,east,enjoy,enter,everything,earth,focus,foot,friend,girl,go,good,boy,happy,high,hour,home,here,image,item,keep,key,local,lucky,main,meta,moon,nature,nice,north,verse,option,owner,person,player,point,position,power,rain,record,region,right,room,sea,side,spring,station,street,south,time,ten,unit,wind,yeah,west,well,world".split(",");
    //private static final String[] DOMAIN_NAMES = "cat,dog,unit".split(",");

    OwnerDomainNameRepository ownerDomainNameRepository;
    BaseRegistrarEventNameRegisteredRepository baseRegistrarEventNameRegisteredRepository;

    public MetadataServiceImpl(OwnerDomainNameRepository ownerDomainNameRepository,
                               BaseRegistrarEventNameRegisteredRepository baseRegistrarEventNameRegisteredRepository) {
        this.ownerDomainNameRepository = ownerDomainNameRepository;
        this.baseRegistrarEventNameRegisteredRepository = baseRegistrarEventNameRegisteredRepository;
    }

    private String getImage(String name, String tokenId) {

        System.out.println(tokenId);
        if (tokenId.length() == 66)
            tokenId = tokenId.substring(2);

        if (tokenId.length() != 64)
            return "";


        String image = SVG_TEMPLATE;
        image = image.replace("{C0}", tokenId.substring(0, 4));
        image = image.replace("{C1}", tokenId.substring(4, 8));
        image = image.replace("{C2}", tokenId.substring(8, 12));
        image = image.replace("{C3}", tokenId.substring(12, 16));
        image = image.replace("{C4}", tokenId.substring(16, 20));
        image = image.replace("{C5}", tokenId.substring(20, 24));
        image = image.replace("{C6}", tokenId.substring(24, 28));
        image = image.replace("{C7}", tokenId.substring(28, 32));
        image = image.replace("{C8}", tokenId.substring(32, 36));
        image = image.replace("{C9}", tokenId.substring(36, 40));
        image = image.replace("{C10}", tokenId.substring(40, 44));
        image = image.replace("{C11}", tokenId.substring(44, 48));
        image = image.replace("{C12}", tokenId.substring(48, 52));
        image = image.replace("{C13}", tokenId.substring(52, 56));
        image = image.replace("{C14}", tokenId.substring(56, 60));
        image = image.replace("{C15}", tokenId.substring(60));

        image = image.replace("{DOMAINS}", name);

        return image;
    }

    @Override
    public ResponseEntity<MetaData> getMetadata(String networkName, String contractAddress, String tokenId) {


        if (networkName == null || contractAddress == null || tokenId == null ||
                networkName.length() == 0 || contractAddress.length() == 0 || tokenId.length() == 0)
            return ResponseEntityUtils.badRequest(null, "null object");


        if (!networkName.equals("mainnet") && !networkName.equals("rinkeby"))
            return ResponseEntityUtils.badRequest(null, "error network name");

        if (!contractAddress.equals("0x91f4859da91F5935d5A7694202e218661c824A53"))
            return ResponseEntityUtils.badRequest(null, "error contract address");


        OwnerDomainName ownerDomainName = ownerDomainNameRepository.getOwnerDomainNameByLabel(tokenId);

        if (ownerDomainName == null)
            return ResponseEntityUtils.badRequest(null, "error value of tokenId");

        if (ownerDomainName.getBaseNodeIndex() >= DOMAIN_NAMES.length)
            return ResponseEntityUtils.internalServerError(null, "error value of base node index");

        MetaData metaData = new MetaData();
        String domainName = ownerDomainName.getName() + "." + DOMAIN_NAMES[ownerDomainName.getBaseNodeIndex()];
        metaData.setName(domainName);

        metaData.setImage("https://metadata.unit.domains/" + networkName + "/" + contractAddress + "/" + tokenId + "/image");

        metaData.setImage_url("https://metadata.unit.domains/" + networkName + "/" + contractAddress + "/" + tokenId + "/image");

        String str = getImage(ownerDomainName.getName() +
                "." +
                DOMAIN_NAMES[ownerDomainName.getBaseNodeIndex()], tokenId);

        metaData.setImage_data(str);

        metaData.setDescription(domainName + ",A distributed, open, and extensible naming system based on the Ethereum blockchain.");
        metaData.setName_length(ownerDomainName.getName().length());
        metaData.setUrl("https://www.unit.domains/name/" + domainName);


        List<MetaDataAttribute> metaDataAttributeList = new ArrayList<>();

        BaseRegistrarEventNameRegistered baseRegistrarEventNameRegistered =
                baseRegistrarEventNameRegisteredRepository.getById(tokenId);


        if (baseRegistrarEventNameRegistered != null) {
            MetaDataAttribute metaDataAttributeExpiration = new MetaDataAttribute();
            metaDataAttributeExpiration.setTrait_type("Expiration Date");
            metaDataAttributeExpiration.setDisplay_type("date");
            metaDataAttributeExpiration.setValue(baseRegistrarEventNameRegistered.getExpires());
            metaDataAttributeList.add(metaDataAttributeExpiration);

            MetaDataAttribute metaDataAttributeRegistration = new MetaDataAttribute();
            metaDataAttributeRegistration.setTrait_type("Registration Date");
            metaDataAttributeRegistration.setDisplay_type("date");
            metaDataAttributeRegistration.setValue(BigInteger.valueOf(baseRegistrarEventNameRegistered.getTimestamp().getTime()));
            metaDataAttributeList.add(metaDataAttributeRegistration);

            MetaDataAttribute metaDataAttributeCreated = new MetaDataAttribute();
            metaDataAttributeCreated.setTrait_type("Created Date");
            metaDataAttributeCreated.setDisplay_type("date");
            metaDataAttributeCreated.setValue(null);
            metaDataAttributeList.add(metaDataAttributeCreated);


        }

        if (metaDataAttributeList.size() > 0)
            metaData.setAttributes(metaDataAttributeList);

        return ResponseEntity.ok(metaData);

    }

    @Override
    public ResponseEntity<String> getMetaDataImage(String networkName, String contractAddress, String tokenId) {


        if (networkName == null || contractAddress == null || tokenId == null ||
                networkName.length() == 0 || contractAddress.length() == 0 || tokenId.length() == 0)
            return ResponseEntityUtils.badRequest(null, "null object");


        if (!networkName.equals("mainnet") && !networkName.equals("rinkeby"))
            return ResponseEntityUtils.badRequest(null, "error network name");

        if (!contractAddress.equals("0x91A04e5Cc77e36400BfD48eC5C7c64162619e9c8"))
            return ResponseEntityUtils.badRequest(null, "error contract address");


        OwnerDomainName ownerDomainName = ownerDomainNameRepository.getOwnerDomainNameByLabel(tokenId);

        if (ownerDomainName == null)
            return ResponseEntityUtils.badRequest(null, "error value of tokenId");

        if (ownerDomainName.getBaseNodeIndex() >= DOMAIN_NAMES.length)
            return ResponseEntityUtils.internalServerError(null, "error value of base node index");

        String str = getImage(ownerDomainName.getName() +
                "." +
                DOMAIN_NAMES[ownerDomainName.getBaseNodeIndex()], tokenId);

        return ResponseEntity.ok(str);
    }


}
