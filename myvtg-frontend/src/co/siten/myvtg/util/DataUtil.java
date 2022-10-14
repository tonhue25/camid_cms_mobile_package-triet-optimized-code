/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.crypto.Cipher;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author buiquangdai
 */
public class DataUtil {

    public static String SPACE_ELEMENTS = ",,";

    /**
     * get stardard Isdn to Privilege
     *
     * @param isdn 098
     * @return String
     */
    public static String getStardardIsdnToDB(String isdn) {
        if (isdn.startsWith("0")) {
            return isdn.substring(1);
        } else if (isdn.startsWith("84")) {
            return isdn.substring(2);
        }

        return isdn;
    }

    /**
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static Double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;
        int meterConversion = 1609;
        return new Double((dist * meterConversion));
    }

    /**
     * get stardard Isdn to IN
     *
     * @param isdn 098
     * @return String
     */
    public static String getStardardIsdnToIN(String isdn) {
        if (isdn == null) {
            return "";
        }
        if (isdn.startsWith("0")) {
            return "84" + isdn.substring(1);
        } else if (isdn.startsWith("84")) {
            return isdn;
        }

        return "84" + isdn;
    }

    /**
     * fomatIsdn
     *
     * @param isdn
     * @return
     */
    public static String fomatIsdn(String isdn) {
        if (isNullOrEmpty(isdn)) {
            return "";
        }
        String isdnFm = isdn.replaceAll("\\s+", "").trim();
        isdnFm = isdnFm.replace("-", "").trim();
        isdnFm = isdnFm.startsWith("+855") ? isdnFm.substring(4) : isdnFm;
        isdnFm = isdnFm.startsWith("855") ? isdnFm.substring(3) : isdnFm;
        isdnFm = isdnFm.startsWith("0") ? isdnFm.substring(1) : isdnFm;
        return isdnFm;
    }

    /**
     * fomatIsdn
     *
     * @param isdn
     * @return
     */
    public static String fomatIsdn855(String isdn) {
        if (isNullOrEmpty(isdn)) {
            return "";
        }
        String isdnFm = isdn.replaceAll("\\s+", "").trim();
        isdnFm = isdnFm.replaceAll("-", "").trim();
        if (isdnFm.startsWith("+855")) {
            return isdnFm.substring(1);
        } else if (isdnFm.startsWith("855")) {
            return isdnFm;
        } else if (isdnFm.startsWith("0")) {
            return "855" + isdnFm.substring(1);
        } else {
            return "855" + isdnFm;
        }
    }

    public static String getMessage(String msgPattern, Object[] params) {

        if (params != null) {
            msgPattern = MessageFormat.format(msgPattern, params);
        }
        return msgPattern;

    }

    public static String getMessage(String msgPattern, Object param) {

        if (param != null) {
            msgPattern = MessageFormat.format(msgPattern, param.toString());
        }
        return msgPattern;

    }

    /**
     * Ham get chuan hoa thong tin
     *
     * @param phoneNumber
     * @return
     */
    public static String getStardardIsdn(String phoneNumber) {
        String strReturn = "";
        if (phoneNumber == null) {
            return strReturn;
        }
        strReturn = phoneNumber;
        if (strReturn.startsWith("+")) {
            strReturn = strReturn.substring(1);
        }
        if (strReturn.startsWith("0")) {
            strReturn = strReturn.substring(1);
        }
        if (!strReturn.startsWith("84")) {
            strReturn = "84" + strReturn;
        }
        return strReturn;
    }

    /**
     * get stardard Isdn to Privilege
     *
     * @param isdn 098
     * @return String
     */
//    public static String getStardardIsdnToPrivilege(String isdn) {
//        if (isdn.startsWith("0")) {
//            return isdn;
//        } else if (isdn.startsWith("84")) {
//            return "0" + isdn.substring(2);
//        }
//
//        return "0" + isdn;
//    }
    //Lay format so duoi dang: 1.000.000
    public static String getFormatNumberGerman(Long number) {
        if (number != null) {
            NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
            return nf.format(number);
        } else {
            return null;
        }
    }

    /**
     *
     * @param obj1 Object
     * @return int
     */
    public static Long safeToLong(Object obj1) {
        Long result = 0L;
        if (obj1 != null) {
            try {
                result = Long.parseLong(obj1.toString());
            } catch (Exception ex) {
            }
        }

        return result;
    }

    /**
     *
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1) {
        if (obj1 == null) {
            return "";
        }

        return obj1.toString();
    }

    /**
     * safe equal
     *
     * @param obj1 Long
     * @param obj2 Long
     * @return boolean
     */
    public static boolean safeEqual(Long obj1, Long obj2) {
        return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
    }

    /**
     * safe equal
     *
     * @param obj1 String
     * @param obj2 String
     * @return boolean
     */
    public static boolean safeEqual(String obj1, String obj2) {
        return ((obj1 != null) && (obj2 != null) && obj1.equals(obj2));
    }

    /**
     * increase cur no
     *
     * @param obj1 String
     * @param obj2 String
     * @return String
     */
    public static String increaseCurNo(String obj1, int obj2) {
        return String.format("%05d", Integer.parseInt(obj1) + obj2);
    }

    /**
     * create log
     *
     * @param info String
     * @return String
     */
    public static String createLog(String info) {
        //return (DateUtil.dateTime2String(DateUtil.sysDate()) + ": " + info);
        return info;
    }

    /**
     * check null or empty
     *
     * @param obj1 String
     * @return boolean
     */
    public static boolean isNullOrEmpty(String obj1) {
        return (obj1 == null || "".equals(obj1));
    }

    /**
     *
     * @param obj1 Object
     * @return BigDecimal
     */
    public static BigDecimal safeToBigDecimal(Object obj1) {
        BigDecimal result = null;
        if (obj1 == null) {
            return null;
        }
        try {
            result = new BigDecimal(obj1.toString());
        } catch (Exception ex) {
        }

        return result;
    }

    /**
     * add
     *
     * @param obj1 BigDecimal
     * @param obj2 BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal add(BigDecimal obj1, BigDecimal obj2) {
        if (obj1 == null) {
            return obj2;
        } else if (obj2 == null) {
            return obj1;
        }

        return obj1.add(obj2);
    }

    /**
     * @author daibq convertStringToDate
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date convertStringToDate(String dateStr) throws Exception {
        Date date = new SimpleDateFormat("MM/yyyy").parse(dateStr);
        return date;
    }

    /**
     * @author daibq convertStringToDate
     * @param dateStr
     * @param pattern
     * @return
     * @throws Exception
     */
    public static Date convertStringToDate(String dateStr, String pattern) throws Exception {
        Date date = new SimpleDateFormat(pattern).parse(dateStr);
        return date;
    }

    /**
     * @author daibq check null or empty Su dung ma nguon cua thu vien
     * StringUtils trong apache common lang
     *
     * @param cs String
     *
     * @return boolean
     */
    public static boolean isNullOrEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author daibq isNullOrEmpty
     *
     * @param collection
     * @return
     */
    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * @author daibq isNullOrEmpty
     *
     * @param collection
     * @return
     */
    public static boolean isNullOrEmpty(final Object[] collection) {
        return collection == null || collection.length == 0;
    }

    /**
     * @author daibq isNullOrEmpty
     *
     * @param map
     * @return
     */
    public static boolean isNullOrEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * @author daibq isNullObject
     *
     * @param obj1
     * @return
     */
    public static boolean isNullObject(Object obj1) {
        if (obj1 == null) {
            return true;
        }
        if (obj1 instanceof String) {
            return isNullOrEmpty(obj1.toString());
        }
        return false;
    }

    /**
     * parseDouble
     *
     * @param str
     * @return
     * @throws java.lang.Exception
     */
    public static Double parseDouble(String str) throws Exception {
        if (isNullOrEmpty(str)) {
            return null;
        }
        return Double.parseDouble(str);

    }

    /**
     * addTime
     *
     * @param date
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param type
     * @return
     */
    public static Date addTime(Date date, Integer day, Integer hour, Integer minute, Integer second, int type) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        switch (type) {
            case 0:
                c.add(Calendar.DAY_OF_MONTH, day);
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND, 00);
                c.set(Calendar.MILLISECOND, 00);
                break;
            case 1:
                c.add(Calendar.DAY_OF_MONTH, day);
                c.add(Calendar.HOUR_OF_DAY, hour);
                c.add(Calendar.MINUTE, minute);
                c.add(Calendar.SECOND, second);
                c.add(Calendar.MILLISECOND, 00);
                break;
            case 2:
                c.set(Calendar.DAY_OF_MONTH, day);
                c.set(Calendar.HOUR_OF_DAY, hour);
                c.set(Calendar.MINUTE, minute);
                c.set(Calendar.SECOND, minute);
                c.set(Calendar.MILLISECOND, 00);
                break;
            case 3:
                c.set(Calendar.DAY_OF_MONTH, day);
                c.set(Calendar.HOUR_OF_DAY, 00);
                c.set(Calendar.MINUTE, 00);
                c.set(Calendar.SECOND, 00);
                c.set(Calendar.MILLISECOND, 00);
                break;
            case 4:
                c.set(Calendar.HOUR_OF_DAY, 00);
                c.set(Calendar.MINUTE, 00);
                c.set(Calendar.SECOND, 00);
                c.set(Calendar.MILLISECOND, 00);
                break;
            case 5:
                c.add(Calendar.DAY_OF_MONTH, day);
                c.set(Calendar.HOUR_OF_DAY, 00);
                c.set(Calendar.MINUTE, 00);
                c.set(Calendar.SECOND, 00);
                c.set(Calendar.MILLISECOND, 00);
                break;
            default:
                break;
        }
        return c.getTime();
    }

    /**
     * addDate
     *
     * @param date
     * @param addDay
     * @return
     */
    public static Date addDate(Date date, int addDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, addDay);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.MILLISECOND, 00);
        return c.getTime();
    }

    /**
     * addDate
     *
     * @param date
     * @return
     */
    public static Date addDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.MILLISECOND, 00);
        return c.getTime();
    }

    /**
     * addDate
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * minDay
     *
     * @return
     */
    public static int minDay() {
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH, month - 1);
//        cal.set(Calendar.DAY_OF_MONTH, month - 1);
//        cal.set(Calendar.YEAR, year);
        int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        return minDay;
    }

    /**
     * maxDay
     *
     * @return
     */
    public static int maxDay() {
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH, month - 1);
//        cal.set(Calendar.DAY_OF_MONTH, month - 1);
//        cal.set(Calendar.YEAR, year);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    /**
     *
     * @param str_endcode
     * @return
     */
    public static String Encode_Data(String str_endcode) {
        return MD5_Encode(SHA256_Encode(str_endcode));
    }

    /**
     *
     * @param input
     * @return
     */
    private static String MD5_Encode(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32
            // chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param input
     * @return
     */
    private static String SHA256_Encode(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @return
     */
    public static String getSalt() {
        try {
            byte[] salt = new byte[8];
            new Random().nextBytes(salt);
            return Base64.encodeBase64String(salt);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static String rand(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return String.valueOf(randomNum);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param obj1
     * @param defaultValue
     * @return
     */
    public static Short safeToShort(Object obj1, Short defaultValue) {
        Short result = defaultValue;
        if (obj1 != null) {
            try {
                result = Short.parseShort(obj1.toString());
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        return result;
    }

    /**
     *
     * @param obj1
     * @return
     */
    public static Short safeToShort(Object obj1) {
        return safeToShort(obj1, (short) 0);
    }

    /**
     * @param obj1
     * @param defaultValue
     *
     * @return
     *
     * @author daibq
     */
    public static int safeToInt(Object obj1, int defaultValue) {
        int result = defaultValue;
        if (obj1 != null) {
            try {
                result = Integer.parseInt(obj1.toString());
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        return result;
    }

    /**
     * @param obj1 Object
     *
     * @return int
     */
    public static int safeToInt(Object obj1) {
        return safeToInt(obj1, 0);
    }

    /**
     * @param obj1 Object
     *
     * @return String
     */
    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }

        return obj1.toString();
    }

    public static String safeToLower(String obj1) {
        if (obj1 == null) {
            return null;
        }

        return obj1.toLowerCase();
    }

    /**
     * safe equal
     *
     * @param obj1 Long
     * @param obj2 Long
     *
     * @return boolean
     */
    public static boolean safeEqual(BigInteger obj1, BigInteger obj2) {
        if (obj1 == obj2) {
            return true;
        }
        return (obj1 != null) && (obj2 != null) && obj1.equals(obj2);
    }

    /**
     * @param obj1
     * @param obj2
     *
     * @return
     *
     * @date 09-12-2015 17:43:20
     * @author daibq
     * @description
     */
    public static boolean safeEqual(Short obj1, Short obj2) {
        if (obj1 == obj2) {
            return true;
        }
        return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
    }

    /**
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean safeEqual(BigDecimal obj1, BigDecimal obj2) {
        if (obj1 == obj2) {
            return true;
        }
        return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
    }

    public static String[] stringToStringArray(String s) {
        String[] ss = new String[0];
        if (s != null) {
            return s.split(SPACE_ELEMENTS);
        }
        return ss;
    }

    /**
     * generateQrCode
     *
     * @param giftCode
     * @return
     */
    public static String generateQrCode(String giftCode) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(giftCode);
            //     System.out.println("==statusCode======>"+ sb.toString());                                              
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(sb.toString(), BarcodeFormat.QR_CODE, Constants.WIDTH_QR_CODE,
                    Constants.HEIGHT_QR_CODE);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);

            //  System.out.println("========>"+ Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()));
            return java.util.Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (WriterException | IOException e) {
            //logger.error("Error", e);
        }
        return null;
    }

    /**
     * createOrderCode
     *
     * @return
     */
    public static String createOrderCode() {
        String codePromotion = RandomStringUtils.randomAlphanumeric(16).toUpperCase();
        return codePromotion;
    }

    /**
     * randomUUID
     *
     * @return
     */
    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    /**
     *
     * @param username
     * @param password
     * @param from
     * @param to
     * @param host
     * @param port
     * @param subject
     * @param body
     * @param attachmentBase64
     * @param fileName
     * @param isAuthen
     * @param isSsl
     * @param cc
     * @param bcc
     * @param check
     * @param logger
     * @throws Exception
     */
    public static void sendMail(final String username, final String password, String from, String to, String host,
            String port, String subject, String body, String attachmentBase64, String fileName, String isAuthen,
            String isSsl, String cc, Logger logger) throws Exception {
        logger.info("Start sendMail of DataUtil");
        Properties props = new Properties();
        props.put("mail.smtp.auth", isAuthen);
        logger.info("isAuthen:" + isAuthen);
        props.put("mail.smtp.starttls.enable", isSsl);
        logger.info("isSsl : " + isSsl);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.host", host);
        logger.info("host : " + host);
        props.put("mail.smtp.port", port);
        logger.info("port:" + port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                logger.info("username:" + username);
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        if (!isNullOrEmpty(cc)) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        }
        if (!isNullOrEmpty(subject)) {
            message.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
        }

        // Create the message part
        MimeBodyPart messagePart = new MimeBodyPart();
        // Now set content email
        messagePart.setText(body, "UTF-8", "plain");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messagePart);

        if (attachmentBase64 != null && !attachmentBase64.isEmpty()) {
            // Part two is attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            //Convert String Base64 To Image
            byte[] decode = java.util.Base64.getDecoder().decode(attachmentBase64);
            //Add Image to DataSources and send email
            DataSource source = new ByteArrayDataSource(decode, "application/octet-stream");
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);
            multipart.addBodyPart(attachmentPart);
        }
        message.setContent(multipart);

        Transport.send(message);
    }

    /**
     * @author daibq
     * @param query
     * @param prefix
     * @param str
     */
    public static void setParamInQuery(org.hibernate.Query query, String prefix, String str) {
        int idx = 1;
        String[] objs = str.split(",");
        for (String obj : objs) {
            query.setParameter(prefix + String.valueOf(idx++), obj.trim());
        }
    }

    /**
     * @author daibq
     * @param prefix
     * @param str
     * @return
     */
    public static String createInQuery(String prefix, String str) {
        String[] objs = str.split(",");
        String inQuery = " IN (";
        for (int idx = 1; idx < objs.length + 1; idx++) {
            inQuery += "," + ":" + prefix + idx;
        }
        inQuery += ") ";
        inQuery = inQuery.replaceFirst(",", "");

        return inQuery;
    }

    /**
     * encryptRSA
     *
     * @param plainData
     * @param publicKeyFilePath
     * @return
     */
    public static String encryptRSA(String plainData, String publicKeyFilePath) {
        try {
            FileInputStream fis = new FileInputStream(publicKeyFilePath);
            byte[] byteKeyFromFile = new byte[fis.available()];
            fis.read(byteKeyFromFile);
            fis.close();

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(byteKeyFromFile);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(keySpec);

            // Mã hoá dữ liệu
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.ENCRYPT_MODE, pubKey);
            byte encryptedByte[] = c.doFinal(plainData.getBytes());
            String encrypted = Base64.encodeBase64String(encryptedByte);
            return encrypted;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * decryptRSA
     *
     * @param encryptedData
     * @param privateKeyFilePath
     * @return
     */
    public static String decryptRSA(String encryptedData, String privateKeyFilePath) {
        try {
            FileInputStream fis = new FileInputStream(privateKeyFilePath);
            byte[] byteKeyFromFile = new byte[fis.available()];
            fis.read(byteKeyFromFile);
            fis.close();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(byteKeyFromFile);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = factory.generatePrivate(keySpec);
            // Giải mã dữ liệu
            Cipher c2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c2.init(Cipher.DECRYPT_MODE, priKey);
            String decrypted = new String(c2.doFinal(Base64.decodeBase64(encryptedData)));
            return decrypted;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
       String a= DataUtil.decryptRSA("Gjp0zqcCaEt3n+yXBuFVeewjoIEBQ7IZZKsew9wD2BU7IYb/sW7ot1Ps8j2XlAOjP0OAw1ZFq4Okfqml5+JAuRO+pl80EXllEWpFmf7JH6c/w3wQtqXAWDoUY3SExiqAZ2isdjbfk2Rl07CFMJ0gD4c6+/cTBaf5sgypmmw8G6ni4ZccbP/lttZsQza9q1Z9TAtjWz25u74nQK0CCF4uAsMSq6+HahRmrI6s7iXOHrIz2cVqgQXlINyGoads1pJzfya3CT1yYlsyayYSN77+k0FRxvgYJ1mzL0f74xOsIdGzLhMi/NRaq3e43N2bu9pzChHfzE5f7wOolIhPzSmAqA==","E:/daibq/source/ePayTestKey/ePayTest-privatekey.pri");
        System.out.println(a);
        
        String b = rand(111, 999999);
        System.out.println("b : " +b);
    }

}
