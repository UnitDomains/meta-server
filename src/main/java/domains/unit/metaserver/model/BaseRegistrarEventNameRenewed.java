package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

@Component
public class BaseRegistrarEventNameRenewed {
    private String pkId;//pk_id,主键

    private int networkId;
    private String id;//id
    private BigInteger expires;//expires
    private Date timestamp;//timestamp
    private Date opTime;//op_time


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getExpires() {
        return expires;
    }

    public void setExpires(BigInteger expires) {
        this.expires = expires;
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