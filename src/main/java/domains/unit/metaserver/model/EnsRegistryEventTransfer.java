package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

@Component
public class EnsRegistryEventTransfer {
    private String pkId;//pk_id,主键
    private String node;//node
    private String owner;//owner
    private Date timestamp;//timestamp
    private Date opTime;//op_time


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
}