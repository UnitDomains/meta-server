package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

@Component
public class OwnerDomainName {
    String name;
    int baseNodeIndex;
    String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseNodeIndex() {
        return baseNodeIndex;
    }

    public void setBaseNodeIndex(int baseNodeIndex) {
        this.baseNodeIndex = baseNodeIndex;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
