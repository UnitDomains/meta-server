package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EthRegistrarControllerEventOwnershipTransferred {
    private String pkId;//pk_id

    private int networkId;
    private String previousOwner;//previousOwner
    private String newOwner;//newOwner
    private Date timestamp;//timestamp
    private Date opTime;//op_time


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(String previousOwner) {
        this.previousOwner = previousOwner;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner;
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