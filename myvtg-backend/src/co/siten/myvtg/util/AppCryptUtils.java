/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import sun.misc.BASE64Decoder;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

/**
 *
 * @author phuonghc
 */
public class AppCryptUtils {

    public static String encryptionPass(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String output;
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        sha.reset();
        sha.update(input.getBytes("UTF-8"));
        output = Base64.encodeBase64String(sha.digest());
        return output;
    }

    public static String decrypt(String dataEncrypted, String privateKey)
            throws Exception {
        RSAPrivateKey _privateKey = loadPrivateKey(privateKey);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(2, _privateKey);

        dataEncrypted = dataEncrypted.replace("\r", "");
        dataEncrypted = dataEncrypted.replace("\n", "");
        int dwKeySize = _privateKey.getModulus().bitLength();
        int base64BlockSize = dwKeySize / 8 % 3 != 0 ? dwKeySize / 8 / 3 * 4 + 4 : dwKeySize / 8 / 3 * 4;

        int iterations = dataEncrypted.length() / base64BlockSize;
        ByteBuffer bb = ByteBuffer.allocate(100000);
        for (int i = 0; i < iterations; i++) {
            String sTemp = dataEncrypted.substring(base64BlockSize * i, base64BlockSize * i + base64BlockSize);

            byte[] bTemp = decodeBase64(sTemp);

            bTemp = reverse(bTemp);
            byte[] encryptedBytes = cipher.doFinal(bTemp);
            bb.put(encryptedBytes);
        }
        byte[] bDecrypted = bb.array();
        return new String(bDecrypted).trim();
    }

    private static RSAPrivateKey loadPrivateKey(String key) throws Exception {
        String sReadFile = key.trim();
        if ((sReadFile.startsWith("-----BEGIN PRIVATE KEY-----")) && (sReadFile.endsWith("-----END PRIVATE KEY-----"))) {
            sReadFile = sReadFile.replace("-----BEGIN PRIVATE KEY-----", "");
            sReadFile = sReadFile.replace("-----END PRIVATE KEY-----", "");
            sReadFile = sReadFile.replace("\n", "");
            sReadFile = sReadFile.replace("\r", "");
            sReadFile = sReadFile.replace(" ", "");
        }

        byte[] b = decodeBase64(sReadFile);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);

        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) factory.generatePrivate(spec);
    }

    private static byte[] decodeBase64(String dataToDecode) {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] bDecoded = (byte[]) null;
        try {
            bDecoded = b64d.decodeBuffer(dataToDecode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bDecoded;
    }

    private static byte[] reverse(byte[] b) {
        int left = 0;
        int right = b.length - 1;

        while (left < right) {
            byte temp = b[left];
            b[left] = b[right];
            b[right] = temp;

            left++;
            right--;
        }
        return b;
    }

    public static void main(String[] arStrings) throws Exception {
        String data = encrypt("aavn", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoVkSXM5GoRR7TOKX+lgjlfpUVEB/DI4ftLm3jHtLRVGRCHrnULiLt83npVI0HhbWkDt8ui3oJo6cPSlUY5W63aUjHLc1a5Jsrqt+LodqkGrfrsdCvPHDfyYQNoExPz/4JiyrcNMju2bC/T6mfWj4pmf4bcHl2AHvpW9yRwdIRnNF8FDuSR+LaJm4aFo52wk7uqD/z76hXnWSvI80W9bDj8zbN2+ir3+VZo3Pppnv1w5nidAqiDgLA972znekm0FleKdwe91O4hRGMfhwMoUl1ZgNcwrJJ6rSjRysg8KpnbF1K0hVGSt31uzECoERK/+QTrD8tuT/fmISKPeLcCXMSQIDAQAB");
        System.out.println(data);
    }

    public static String encrypt(String dataToEncrypt, String pubCer)
            throws Exception {
        RSAPublicKey _publicKey = loadPublicKey(pubCer);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(1, _publicKey);

        int keySize = _publicKey.getModulus().bitLength() / 8;
        int maxLength = keySize - 42;

        byte[] bytes = dataToEncrypt.getBytes();

        int dataLength = bytes.length;
        int iterations = dataLength / maxLength;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= iterations; i++) {
            byte[] tempBytes = new byte[dataLength - maxLength * i > maxLength ? maxLength : dataLength - maxLength * i];

            System.arraycopy(bytes, maxLength * i, tempBytes, 0, tempBytes.length);

            byte[] encryptedBytes = cipher.doFinal(tempBytes);

            encryptedBytes = reverse(encryptedBytes);
            sb.append(encodeBase64(encryptedBytes));
        }

        String sEncrypted = sb.toString();
        sEncrypted = sEncrypted.replace("\r", "");
        sEncrypted = sEncrypted.replace("\n", "");
        return sEncrypted;
    }

    private static RSAPublicKey loadPublicKey(String pubCer) throws Exception {
        RSAPublicKey publicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodeBase64(pubCer));
            publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
        }
        return publicKey;
    }

    private static String encodeBase64(byte[] dataToEncode) {
        BASE64Encoder b64e = new BASE64Encoder();
        String strEncoded = "";
        try {
            strEncoded = b64e.encode(dataToEncode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strEncoded;
    }

    public static String getDataEncrypt(String request) {
        String data = request.replace("\r", "");
        data = data.replace("\n", "");
        data = data.replace("\t", "");
        return data;
    }
}
