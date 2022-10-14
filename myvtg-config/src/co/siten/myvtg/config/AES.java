package co.siten.myvtg.config;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES {

    private static final Logger LOGGER = Logger.getLogger(AES.class);

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private enum EncryptMode {

        ENCRYPT, DECRYPT;
    }

    static String key = "myvtg";
    static String iv = "151e7b7548668ea2";

    static Cipher _cx;
    static byte[] _key, _iv;

    public AES() {
        init();
    }

    synchronized private static void init() {
        try {
            if (_cx == null) {
                _cx = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            Security.addProvider(new BouncyCastleProvider());
            if (_key == null) {
                _key = new byte[16]; // 256 bit key space
            }
            if (_iv == null) {
                _iv = new byte[16]; // 128 bit IV
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Sai thuat toan ma hoa md5:", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error("Sai thuat toan ma hoa md5:", e);
        }
    }

    public static final String md5(final String inputString) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(inputString.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Sai thuat toan ma hoa md5:", e);
        }
        return "";
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * thuc hien ma hoa du lieu dau vao
     *
     * @param _inputText
     * @param _encryptionKey
     * @param _mode
     * @param _initVector
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encryptDecrypt(String _inputText, String _encryptionKey, EncryptMode _mode,
            String _initVector) throws UnsupportedEncodingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        String _out = "";
        int len = _encryptionKey.getBytes("UTF-8").length; // length of the key
        // provided

        if (_encryptionKey.getBytes("UTF-8").length > _key.length) {
            len = _key.length;
        }

        int ivlen = _initVector.getBytes("UTF-8").length;

        if (_initVector.getBytes("UTF-8").length > _iv.length) {
            ivlen = _iv.length;
        }

        System.arraycopy(_encryptionKey.getBytes("UTF-8"), 0, _key, 0, len);
        System.arraycopy(_initVector.getBytes("UTF-8"), 0, _iv, 0, ivlen);
        // KeyGenerator _keyGen = KeyGenerator.getInstance("AES");
        // _keyGen.init(128);

        SecretKeySpec keySpec = new SecretKeySpec(_key, "AES"); // Create a new
        // SecretKeySpec
        // for the
        // specified key
        // data and
        // algorithm
        // name.

        IvParameterSpec ivSpec = new IvParameterSpec(_iv); // Create a new
        // IvParameterSpec
        // instance with the
        // bytes from the
        // specified buffer
        // iv used as
        // initialization
        // vector.

        // encryption
        if (_mode.equals(EncryptMode.ENCRYPT)) {
            // Potentially insecure random numbers on Android 4.3 and older.
            // Read
            // https://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html
            // for more info.
            _cx.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);// Initialize this
            // cipher instance
            byte[] results = _cx.doFinal(_inputText.getBytes("UTF-8")); // Finish
            // multi-part
            // transformation
            // (encryption)
            _out = bytesToHex(results); // ciphertext
            // output
        }

        // decryption
        if (_mode.equals(EncryptMode.DECRYPT)) {
            _cx.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);// Initialize this
            // ipher instance

            byte[] decodedValue = hexStringToByteArray(_inputText);
            byte[] decryptedVal = _cx.doFinal(decodedValue); // Finish
            // multi-part
            // transformation
            // (decryption)
            _out = new String(decryptedVal);
        }
        // System.out.println(_out);
        return _out; // return encrypted/decrypted string
    }

    /**
     * * This function computes the SHA256 hash of input string
     *
     * @param text input text whose SHA256 hash has to be computed
     * @param length length of the text to be returned
     * @return returns SHA256 hash of input text
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String SHA256(String text, int length) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String resultStr;
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(text.getBytes("UTF-8"));
        byte[] digest = md.digest();

        StringBuffer result = new StringBuffer();
        for (byte b : digest) {
            result.append(String.format("%02x", b)); // convert to hex
        }

        if (length > result.toString().length()) {
            resultStr = result.toString();
        } else {
            resultStr = result.toString().substring(0, length);
        }

        return resultStr;

    }

    /**
     * * This function encrypts the plain text to cipher text using the key
     * provided. You'll have to use the same key for decryption
     *
     * @param _plainText Plain text to be encrypted
     * @param _key Encryption Key. You'll have to use the same key for
     * decryption
     * @param _iv initialization Vector
     * @return returns encrypted (cipher) text
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public String encrypt(String _plainText, String _key, String _iv) {
        String strResult = "";
        try {
            strResult = encryptDecrypt(_plainText, _key, EncryptMode.ENCRYPT, _iv);
        } catch (InvalidKeyException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (UnsupportedEncodingException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (InvalidAlgorithmParameterException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (IllegalBlockSizeException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (BadPaddingException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        }
        return strResult;
    }

    public static String encrypt(String _plainText) {
        init();
        String strResult = "";
        try {
            String key = AES.SHA256(AES.key, 16); // 32 bytes = 256 bit
            String iv = AES.iv;// CryptLib.generateRandomIV(16);
            strResult = encryptDecrypt(_plainText, key, EncryptMode.ENCRYPT, iv);
        } catch (InvalidKeyException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (UnsupportedEncodingException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (InvalidAlgorithmParameterException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (IllegalBlockSizeException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (BadPaddingException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        }
        return strResult;
    }

    /**
     * * This funtion decrypts the encrypted text to plain text using the key
     * provided. You'll have to use the same key which you used during
     * encryprtion
     *
     * @param _encryptedText Encrypted/Cipher text to be decrypted
     * @param _key Encryption key which you used during encryption
     * @param _iv initialization Vector
     * @return encrypted value
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(String _encryptedText) {
        init();
        String strResult = "";
        try {
            String key = AES.SHA256(AES.key, 16); // 32 bytes = 256 bit
            String iv = AES.iv;// CryptLib.generateRandomIV(16);
            strResult = encryptDecrypt(_encryptedText, key, EncryptMode.DECRYPT, iv);
        } catch (InvalidKeyException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh giai ma", e);
        } catch (UnsupportedEncodingException e) {
            strResult = null;
        } catch (InvalidAlgorithmParameterException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh giai ma", e);
        } catch (IllegalBlockSizeException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh giai ma", e);
        } catch (BadPaddingException e) {
            strResult = null;
            LOGGER.error("Loi trong qua trinh giai ma", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        }
        return strResult;
    }

    /**
     * this function generates random string for given length
     *
     * @param length Desired length * @return
     */
    public static String generateRandomIV(int length) {
        SecureRandom ranGen = new SecureRandom();
        byte[] aesKey = new byte[16];
        ranGen.nextBytes(aesKey);
        StringBuffer result = new StringBuffer();
        for (byte b : aesKey) {
            result.append(String.format("%02x", b)); // convert to hex
        }
        if (length > result.toString().length()) {
            return result.toString();
        } else {
            return result.toString().substring(0, length);
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            long aaaaab = (long) (0.15 *100);
            System.out.println("ax" + aaaaab*10001);
            
            
            String aaaa = "aaaaa{cvd}vvvvv";
            String[] ab = aaaa.replace("{cvd}", ",").split(",");
            System.out.println("ab" + ab.length);
            
            String output = "";
            // String ip= "125.212.204.243";
            // String ip = "10.30.165.150";
            // String service=":1521/bccs_vcr";

            String decoded = "jdbc:oracle:thin:@(DESCRIPTION_LIST=(LOAD_BALANCE=off)(FAILOVER=on)(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS=(PROTOCOL=tcp)(HOST=10.79.121.182)(PORT=1521))(ADDRESS=(PROTOCOL=tcp)(HOST=10.79.121.184)(PORT=1521))(CONNECT_DATA=(SERVER=SHARED)(SERVICE_NAME= bankogg_vtc)))(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS=(PROTOCOL=tcp)(HOST=10.79.121.46)(PORT=1521))(ADDRESS=(PROTOCOL=tcp)(HOST=10.79.121.48)(PORT=1521))(CONNECT_DATA=(SERVER=SHARED)(SERVICE_NAME= bankogg_vtc))))";
            String b = "PROMO_REPORT_APP";
            String c = "TS4E$42e";
            System.out.println(AES.encrypt(decoded));
            System.out.println("b :" + AES.encrypt(b));
            System.out.println(AES.encrypt(c));

            String a = "813821FE1280B47E9C14B5C6429406B2E3107E14681B4C9CEFCCD566E66065AD85246AD5568E9D53F1D9E641A5082A5E7F80E02CC258E0F946E1FC4ED4222FE614E2CDAEB7754E33A87365780D1D3734C0FC8FAC681C7D765073AE3E63A6D21ABF9AE6850DDAD207CA21E761268EA9572F69779C2A1BDFFDD6723C53B8DC4584B55396A27B526B79FFA80D4FDDF03CA739CF01DA675E51E7F0470B03F653BA82C6CAF1E79E01D37A0098CD470B306271D50E9B5A4835EB95D0521F2F36E917B2B39FE2A9F8FEB3BAD93AED3DDCCFBBBAB2F1D6B7D4CE11137DC50018C00047DE36F1078DE2372A5740FA328623E6CB78D6B2843FAB6FDF95B6D76E8B8379DEEBEA3754D6848B3FD3F0080CEE3E8801A4ABDAEE95F9044CB17CB6365C265DF3355032DD98AD377CAC8464794902156D56D18B4D15B3C17A82C3C4854917DDB7347B787D6D738A5CDF542E8F949C2693A0";
            System.out.println("a:" + AES.decrypt(a));
            String e = "05A07518B71D539F484D3CB89601DBB4B09487487F18D1E7505DC772C10DD0AA";
            String f = "05A07518B71D539F484D3CB89601DBB4B09487487F18D1E7505DC772C10DD0AA";
            System.out.println("e" + AES.decrypt(e));
            System.out.println(AES.decrypt(f));
//
//			String ipSale = "(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.34)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.39)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = sales) ) )";
//			String ipPrecus = "(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.54)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.46)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = precus) ) )";
//			String ipPoscus = "(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.54)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.46)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = poscus) ) ) ";
//			String ipPayment="(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.34)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.39)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = payment) ) )";
//
//			String ip = "10.120.8.2";
//			String service = ":1521/uniteldeejay";
//
//			String[][] schemaList = { { "myvtg", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ip + service },
//					{ "cmpos", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipPoscus },
//					{ "cmpre", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipPrecus },
//					{ "data", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipPrecus },
//					{ "precall", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipPrecus },
//					{ "payment", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipPayment },
//					{ "billing", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipSale },
//					{ "sm", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipSale },
//					{ "product", "my_vtg", "myvtg2017StL", "jdbc:oracle:thin:@" + ipPrecus } };
//
//			// //16 bytes = 128 bit
//
//			// myvtg.jdbc.url =
//			// 813821FE1280B47E9C14B5C6429406B25160CF9CD0877339944763FB0F980A6E3BC5C5AC89C35AEDB614DF80BBCCB45D
//			// myvtg.jdbc.username = F75892A9DAD5840B8C2EDB52F199EB1B
//			// myvtg.jdbc.password = 54DE4A3FF036F7EF0D41A1505255A73E
//			for (String[] schema : schemaList) {
//				String schemaName = schema[0];
//				String user = AES.encrypt(schema[1]);
//				String password = AES.encrypt(schema[2]);
//				String url = AES.encrypt(schema[3]);
//				System.out.println("#============" + schemaName + "=============");
//				System.out.println(schemaName + ".jdbc.url =" + url);
//				System.out.println(schemaName + ".jdbc.username =" + user);
//				System.out.println(schemaName + ".jdbc.password =" + password);
//				System.out.println("#==============================");
//				// System.out.println(decrypt(url)+" "+decrypt(user)+"
//				// "+decrypt(password));
//
//			}

            // List<Class<?>> classes =
            // ClassFinder.find("co.siten.myvtg.controller");
            //
            // for (Class<?> cl : classes) {
            // //
            // System.out.println("============"+cl.getSimpleName()+"================");
            //
            // Object controller = cl.newInstance();
            // Method[] methods = controller.getClass().getMethods();
            // for (Method method : methods) {
            // if (method.getAnnotation(RequestMapping.class) != null) {
            // for (String str :
            // method.getAnnotation(RequestMapping.class).value()) {
            // output = AES.encrypt(str); // encrypt
            // System.out.println(str + " " + output);
            // }
            //
            // }
            // }
            // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error("Loi trong qua trinh ma hoa", e);
        }
    }
}
