package co.siten.myvtg.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.NodeList;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thomc
 *
 */
public class CommonUtil {

    public static String convertObjectToJsonString(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String r = mapper.writeValueAsString(o);
            return r;
        } catch (Exception e) {
            return o.toString();
        }
    }

    public static boolean isRealNumber(String value) {
        if (value == null) {
            return false;
        }
        try {
            new BigDecimal(value.replace(".", "").replace(",", ""));
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static Date getCurrentTime() {
        return new Date();
    }

    public static List<Integer> getListIntegerFromString(String str, String splitStr) {
        List<String> parentIdsStrList = Arrays.asList(str.split(splitStr));
        List<Integer> parentIdsIntList = new Vector<>();
        for (String id : parentIdsStrList) {
            if (CommonUtil.isInteger(id)) {
                parentIdsIntList.add(Integer.parseInt(id));
            }
        }
        return parentIdsIntList;
    }

    public static boolean isInteger(String s) {
        try {
            int val = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static boolean isEmail(String strEmail) {
        Pattern pattern = Pattern.compile("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})");
        Matcher matcher = pattern.matcher(strEmail.trim());
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Hashtable<?, ?> htInput) {
        if (htInput == null || htInput.size() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(Vector<?> vctInput) {
        if (vctInput == null || vctInput.size() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(String strInput) {
        if (strInput == null || strInput.trim().equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List<?> vctInput) {
        try {
            if (vctInput == null || vctInput.size() == 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        if (map == null || map.size() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(NodeList nodeList) {
        if (nodeList == null || nodeList.getLength() == 0) {
            return true;
        }

        return false;
    }

    public static String capitalizFirstLetter(String strSource) {
        if (CommonUtil.isEmpty(strSource)) {
            return "";
        }

        if (strSource.length() == 1) {
            return strSource.toUpperCase();
        }
        String top = strSource.substring(0, 1).toUpperCase();
        String allow = strSource.substring(1);
        return top + allow;
    }

    public static InputStream getFile(String filePath) {
        try {
            InputStream in = CommonUtil.class.getResourceAsStream(filePath);
            return in;
        } catch (Exception e) {
            return null;
        }
    }

    public static String firstLetterToLowerCase(String str) {
        String firstLetter = str.substring(0, 1).toLowerCase();
        return firstLetter + str.substring(1, str.length());
    }

    public static String firstLetterToUppperCase(String str) {
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1, str.length());
    }

    public static <T extends Object> T getEntityByAttribute(String attribute, Object val, List<T> objList) {
        if (objList == null || val == null || attribute == null) {
            return null;
        }
        Method getter = null;
        try {
            for (T obj : objList) {
                String[] attrs = attribute.split("\\.");
                Object ma = new Object();
                Object loopObj = obj;
                for (String atr : attrs) {
                    getter = loopObj.getClass().getMethod("get" + firstLetterToUppperCase(atr));
                    ma = getter.invoke(loopObj);
                    loopObj = ma;
                }
                if (ma.equals(val)) {
                    return obj;
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public static <T extends Object> List<T> getEntitiesByAttribute(String attribute, Object val, List<T> objList) {
        List<T> rs = new ArrayList<T>();
        if (objList == null || val == null || attribute == null) {
            return null;
        }
        Method getter = null;
        try {
            for (T obj : objList) {
                String[] attrs = attribute.split("\\.");
                Object ma = new Object();
                Object loopObj = obj;
                for (String atr : attrs) {
                    getter = loopObj.getClass().getMethod("get" + firstLetterToUppperCase(atr));
                    ma = getter.invoke(loopObj);
                    loopObj = ma;
                }
                if (ma.equals(val)) {
                    rs.add(obj);
                }
            }
            return rs;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * daibq convertJsonStringToObject
     *
     * @param <T>
     * @param response
     * @param responseClass
     * @return
     */
    public static <T> T convertJsonStringToObject(final String response, final Class<T> responseClass) {
        if (response == null || responseClass == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response, responseClass);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> copyList(List<T> source) {
        if (source == null) {
            return null;
        }
        List<T> rs = new ArrayList<T>();
        for (T t : source) {
            rs.add(t);
        }
        return rs;
    }

    public static void runGarbageCollector() {
        Runtime r = Runtime.getRuntime();
        r.runFinalization();
        r.gc();
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    public static int subtractDays(Date date1, Date date2) {
        GregorianCalendar gc1 = new GregorianCalendar();
        gc1.setTime(date1);
        GregorianCalendar gc2 = new GregorianCalendar();
        gc2.setTime(date2);

        int days1 = 0;
        int days2 = 0;
        int maxYear = Math.max(gc1.get(Calendar.YEAR), gc2.get(Calendar.YEAR));

        GregorianCalendar gctmp = (GregorianCalendar) gc1.clone();
        for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
            days1 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
            gctmp.add(Calendar.YEAR, 1);
        }

        gctmp = (GregorianCalendar) gc2.clone();
        for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
            days2 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
            gctmp.add(Calendar.YEAR, 1);
        }

        days1 += gc1.get(Calendar.DAY_OF_YEAR) - 1;
        days2 += gc2.get(Calendar.DAY_OF_YEAR) - 1;

        return (days1 - days2);
    }

    public static Date getFirstDateOfCurrent() {
        Calendar c = Calendar.getInstance(); // this takes current date
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date truncDatePlus1(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static Date getFirstDateOfPreviousMonth() {
        Calendar aCalendar = Calendar.getInstance();
        // add -1 month to current month
        aCalendar.add(Calendar.MONTH, -1);
        // set DATE to 1, so first date of previous month
        aCalendar.set(Calendar.DATE, 1);
        Date firstDateOfPreviousMonth = aCalendar.getTime();
        return firstDateOfPreviousMonth;
    }

    public static Date getPreviousMonth() {
        Calendar aCalendar = Calendar.getInstance();
        // add -1 month to current month
        aCalendar.add(Calendar.MONTH, -1);

        Date firstDateOfPreviousMonth = aCalendar.getTime();
        return firstDateOfPreviousMonth;
    }

    public static void sendMail(final String username, final String password, String from, String to, String host,
            String port, String subject, String body, String attachmentBase64, String fileName, String isAuthen,
            String isSsl) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", isAuthen);
        props.put("mail.smtp.starttls.enable", isSsl);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //System.out.println("namdh1 says ======= Ha ha send email==========>" + session);
        //System.out.println("namdh1 says ======= to==========>" + to);
        //System.out.println("namdh1 says ======= from==========>" + from);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));

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
            byte[] decode = Base64.getDecoder().decode(attachmentBase64);
            //Add Image to DataSources and send email
            DataSource source = new ByteArrayDataSource(decode, "application/octet-stream");
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);
            multipart.addBodyPart(attachmentPart);
        }
        message.setContent(multipart);

        Transport.send(message);
    }

    public static final String escapeHTML(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                case 'à':
                    sb.append("&agrave;");
                    break;
                case 'À':
                    sb.append("&Agrave;");
                    break;
                case 'â':
                    sb.append("&acirc;");
                    break;
                case 'Â':
                    sb.append("&Acirc;");
                    break;
                case 'ä':
                    sb.append("&auml;");
                    break;
                case 'Ä':
                    sb.append("&Auml;");
                    break;
                case 'å':
                    sb.append("&aring;");
                    break;
                case 'Å':
                    sb.append("&Aring;");
                    break;
                case 'æ':
                    sb.append("&aelig;");
                    break;
                case 'Æ':
                    sb.append("&AElig;");
                    break;
                case 'ç':
                    sb.append("&ccedil;");
                    break;
                case 'Ç':
                    sb.append("&Ccedil;");
                    break;
                case 'é':
                    sb.append("&eacute;");
                    break;
                case 'É':
                    sb.append("&Eacute;");
                    break;
                case 'è':
                    sb.append("&egrave;");
                    break;
                case 'È':
                    sb.append("&Egrave;");
                    break;
                case 'ê':
                    sb.append("&ecirc;");
                    break;
                case 'Ê':
                    sb.append("&Ecirc;");
                    break;
                case 'ë':
                    sb.append("&euml;");
                    break;
                case 'Ë':
                    sb.append("&Euml;");
                    break;
                case 'ï':
                    sb.append("&iuml;");
                    break;
                case 'Ï':
                    sb.append("&Iuml;");
                    break;
                case 'ô':
                    sb.append("&ocirc;");
                    break;
                case 'Ô':
                    sb.append("&Ocirc;");
                    break;
                case 'ö':
                    sb.append("&ouml;");
                    break;
                case 'Ö':
                    sb.append("&Ouml;");
                    break;
                case 'ø':
                    sb.append("&oslash;");
                    break;
                case 'Ø':
                    sb.append("&Oslash;");
                    break;
                case 'ß':
                    sb.append("&szlig;");
                    break;
                case 'ù':
                    sb.append("&ugrave;");
                    break;
                case 'Ù':
                    sb.append("&Ugrave;");
                    break;
                case 'û':
                    sb.append("&ucirc;");
                    break;
                case 'Û':
                    sb.append("&Ucirc;");
                    break;
                case 'ü':
                    sb.append("&uuml;");
                    break;
                case 'Ü':
                    sb.append("&Uuml;");
                    break;
                case '®':
                    sb.append("&reg;");
                    break;
                case '©':
                    sb.append("&copy;");
                    break;
                case '€':
                    sb.append("&euro;");
                    break;
                // be carefull with this one (non-breaking whitee space)
                case ' ':
                    sb.append("&nbsp;");
                    break;

                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static final Date convertFromStringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return dateFormat.parse(date);
        } catch (java.text.ParseException e) {
            System.out.println("Date ParseException, string value:" + date + e.getMessage());
        }
        return new Date();
    }

    public static final Double formatTwoDigital(Double source) {
        try {
            String temp = String.format("%.2f", source);
            Double dest = Double.valueOf(temp);
            return dest;
        } catch (NumberFormatException ne) {
            return source;
        }
    }
}
