package domains.unit.metaserver.utility;

import java.util.UUID;


public class GUID {
    public static String getGUID() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-", "");
    }
}
