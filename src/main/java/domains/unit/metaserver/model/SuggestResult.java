package domains.unit.metaserver.model;

public class SuggestResult {
    String name;
    int baseNodeIndex;//base_node_index

    public int getBaseNodeIndex() {
        return baseNodeIndex;
    }

    public void setBaseNodeIndex(int baseNodeIndex) {
        this.baseNodeIndex = baseNodeIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
