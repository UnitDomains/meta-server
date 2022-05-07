package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

@Component
public class OwnSubDomainName {
    String name;
    String label;
    String subDomain;
    String subNodeLabel;

    int baseNodeIndex;

    private int networkId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public String getSubNodeLabel() {
        return subNodeLabel;
    }

    public void setSubNodeLabel(String subNodeLabel) {
        this.subNodeLabel = subNodeLabel;
    }

    public int getBaseNodeIndex() {
        return baseNodeIndex;
    }

    public void setBaseNodeIndex(int baseNodeIndex) {
        this.baseNodeIndex = baseNodeIndex;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }
}
