package domains.unit.webserver.utility;

import java.util.UUID;


/**
 * 返回唯一UUID
 */
public class GUID {
    public static String getGUID(){
       return   UUID.randomUUID()
        .toString()
        .replaceAll("-", "");
    }
}
