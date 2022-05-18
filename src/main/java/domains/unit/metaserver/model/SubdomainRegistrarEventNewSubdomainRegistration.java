package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SubdomainRegistrarEventNewSubdomainRegistration {
    private String pkId;//pk_id

    private int networkId;
    private String label;//label
    private String subDomain;//sub_domain
    private String subNodeLabel;//sub_node_label
    private String owner;//owner
    private Date timestamp;//timestamp
    private Date opTime;//op_time


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
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

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }
}