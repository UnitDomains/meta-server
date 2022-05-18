package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

@Component
public class DomainInfo {
    private String pkId;//pk_id
    private int networkId;//network_id
    private String label;//label
    private String name;//name
    private int baseNodeIndex;//base_node_index
    private String owner;//owner
    private BigInteger expires;//expires
    private int reverse;//reverse
    private Date opTime;//op_time


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigInteger getExpires() {
        return expires;
    }

    public void setExpires(BigInteger expires) {
        this.expires = expires;
    }

    public int getReverse() {
        return reverse;
    }

    public void setReverse(int reverse) {
        this.reverse = reverse;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}