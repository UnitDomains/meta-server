package domains.unit.metaserver.model.metadata;

import java.math.BigInteger;
import java.util.Date;

public class MetaDataAttribute {
    String trait_type;
    String display_type;
    BigInteger value;

    public String getTrait_type() {
        return trait_type;
    }

    public void setTrait_type(String trait_type) {
        this.trait_type = trait_type;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
