package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

@Component
public class EthRegistrarControllerEventNameRegistered {
    private String pkId;//pk_id,主键

    private int networkId;
    private String name;//name
    private String label;//label
    private String owner;//owner
    private BigInteger cost;//cost
    private BigInteger expires;//expires
    private int baseNodeIndex;//baseNodeIndex
    private Date timestamp;//timestamp
    private Date opTime;//op_time


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigInteger getCost() {
        return cost;
    }

    public void setCost(BigInteger cost) {
        this.cost = cost;
    }

    public BigInteger getExpires() {
        return expires;
    }

    public void setExpires(BigInteger expires) {
        this.expires = expires;
    }

    public int getBaseNodeIndex() {
        return baseNodeIndex;
    }

    public void setBaseNodeIndex(int baseNodeIndex) {
        this.baseNodeIndex = baseNodeIndex;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }
}