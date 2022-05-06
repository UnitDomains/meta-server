package domains.unit.webserver.utility;

import java.security.MessageDigest;


/**
 * 摘要
 */
public class EncoderHandler {

    private final static String SALT="BLOCKCHAINPROJECT";

    public enum ALGORITHM {
        MD5("MD5"), SHA_1("SHA-1"), SHA_256("SHA-256"), SHA_384("SHA-384"), SHA_512("SHA-512");
        private String algorithmName;

        private ALGORITHM(String algorithmName) {
            this.algorithmName = algorithmName;
        }

        public String getAlgorithmName() {
            return algorithmName;
        }
    }


    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;


        return String.valueOf(HEX_DIGITS[iD1]) + String.valueOf(HEX_DIGITS[iD2]);
    }


    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    private static String encode(String str, ALGORITHM type) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type.getAlgorithmName());
            messageDigest.update(str.getBytes());
            return byteToString(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String encode(String str) {
        str="CRYPTO"+str+SALT;
        return encode(str, ALGORITHM.SHA_512);
    }


}
