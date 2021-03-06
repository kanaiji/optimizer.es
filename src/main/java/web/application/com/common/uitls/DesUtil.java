package web.application.com.common.uitls;

import java.security.Security;

/**
 * union
 * version 1.0
 * auto:linxj
 * data:2011-11-18
 * 本程序主要是des及3des的加解密
 * UnionDesEncrypt及UnionDesDecrypt为des加解密
 * Union3DesEncrypt及Union3DesDecrypt为3des加解密
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * TODO... Ees加密工具   无注释   注释中存在乱码
 */
public class DesUtil {

    public static void main(String[] args) throws Exception {
        byte[] key = "JUNNET_123456_123456_COM".getBytes("GB2312");
        byte[] data="123456".getBytes("GB2312");

        System.out.println("3EDS加密:");
        String hexString = encryptToHex(data,key);
        System.out.println(hexString);
        String hexString2 = new String(decrypt(hexStringToBytes(hexString), key),"GB2312");

        System.out.println(hexString2);
    }

    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/ECB/PKCS5Padding";

    /**
     * @param data
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, sec);
        return cipher.doFinal(data);
    }
    /**
     * @param data
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, sec);
        return cipher.doFinal(data);
    }


    /**
     * Convert byte[] string to hex
     * @param arrB
     * @return
     */
    public static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        // Ã¿¸öbyteÓÃÁ½¸ö×Ö·û²ÅÄÜ±íÊ¾£¬ËùÒÔ×Ö·û´®µÄ³¤¶ÈÊÇÊý×é³¤¶ÈµÄÁ½±¶
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // °Ñ¸ºÊý×ª»»ÎªÕýÊý
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // Ð¡ÓÚ0FµÄÊýÐèÒªÔÚÇ°Ãæ²¹0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        // ×î´ó128Î»
        String result = sb.toString();
        return result;
    }
    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    /**
     * ¼ÓÃÜ
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptToHex(byte[] data, byte[] key) throws Exception {
        return byteArr2HexStr(encrypt(data, key));
    }
    /**
     * ½âÃÜ
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptToString(byte[] data, byte[] key) throws Exception {
        return new String(decrypt(data, key));
    }
}
