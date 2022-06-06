package domains.unit.metaserver.utility;

public class DomainName {
    //static final public String[] DOMAIN_NAMES =
    //        "about,area,beyond,book,cat,cell,dream,dog,east,enjoy,enter,everything,earth,focus,foot,friend,girl,go,good,boy,happy,high,hour,home,here,image,item,keep,key,local,lucky,main,meta,moon,nature,nice,north,verse,option,owner,person,player,point,position,power,rain,record,region,right,room,sea,side,spring,station,street,south,time,ten,unit,wind,yeah,west,well,world".split(",");
    public static final String[] DOMAIN_NAMES = "cat,unit".split(",");

    public static boolean validateDomainSuffix(String suffix) {
        for (String v : DOMAIN_NAMES
        ) {
            if (v.equals(suffix))
                return true;
        }
        return false;
    }

    /**
     * Get the domain name suffix
     * e.g. abc.cat =>cat,123.abc.cat=>cat
     *
     * @param domainName full domain name
     * @return
     */
    public static String getDomainSuffix(String domainName) {

        if (domainName == null || domainName.length() == 0) return null;
        int lastIndex = domainName.lastIndexOf('.');
        if (lastIndex < 0) return null;
        String suffix = domainName.substring(lastIndex + 1);
        if (!validateDomainSuffix(suffix)) return null;
        return suffix;
    }

    /**
     * e.g. abc.dog、a.abc.dog、b.a.abc.dog=>abc
     *
     * @param domainName
     * @return
     */
    public static String getDomain(String domainName) {
        if (domainName == null || domainName.trim().length() == 0)
            return null;

        var suffix = getDomainSuffix(domainName);
        if (suffix == null) return domainName;
        var lastIndex = domainName.lastIndexOf(".");
        var pre = domainName.substring(0,
                                       lastIndex);

        lastIndex = pre.lastIndexOf(".");
        var result = pre;
        if (lastIndex > 0) result = pre.substring(lastIndex);

        return result;
    }

    /**
     * e.g. abc.dog、a.abc.dog、b.a.abc.dog=>abc.dog
     *
     * @param domainName
     * @return
     */
    public static String getHostDomain(String domainName) {
        if (domainName == null || domainName.trim().length() == 0)
            return null;

        var pre = getDomain(domainName);
        var suffix = getDomainSuffix(domainName);

        if (pre == null || pre.length() == 0 || suffix == null || suffix.length() == 0)
            return null;
        return pre + "." + suffix;
    }

    public static int getBaseNodeIndex(String domainName) {
        if (domainName == null || domainName.trim().length() == 0)
            return -1;

        var suffix = getDomainSuffix(domainName);
        if (suffix == null) return -1;

        for (var i = 0; i < DOMAIN_NAMES.length; i++) {
            if (DOMAIN_NAMES[i].equals(suffix)) return i;
        }
        return -1;
    }

    public static String getJointName(String name,
                                      int baseNodeIndex) {

        if (name == null || name.trim().length() == 0)
            return null;

        if (
                baseNodeIndex < 0 ||
                        baseNodeIndex >= DOMAIN_NAMES.length
        )
            return null;

        return name + "." + DOMAIN_NAMES[baseNodeIndex];
    }

}
