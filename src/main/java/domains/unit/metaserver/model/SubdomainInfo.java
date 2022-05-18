package domains.unit.metaserver.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SubdomainInfo {
    private String pkId;//pk_id
    private int networkId;//network_id
    private String label;//label,Parent node
    private String subNodeLabel;//sub_node_label,Child node
    private String subDomain;//sub_domain
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

    public String getSubNodeLabel() {
        return subNodeLabel;
    }

    public void setSubNodeLabel(String subNodeLabel) {
        this.subNodeLabel = subNodeLabel;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}