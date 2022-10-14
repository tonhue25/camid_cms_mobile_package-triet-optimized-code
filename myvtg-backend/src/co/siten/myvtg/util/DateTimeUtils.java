/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vu Thi Thu Huong
 */
public class DateTimeUtils {
        /** Creates a new instance of DateTimeUtils */
    public DateTimeUtils() {
    }
    
    /**
     * luatnc
     * @param orgDate
     * @param numDay
     * @return 
     */
    public static Date addNumDay(Date orgDate, int numDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orgDate);
        calendar.add(Calendar.DATE, numDay);

        return calendar.getTime();
    }
    
    public static Date addNumSecond(Date orgDate, int numSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orgDate);
        calendar.add(Calendar.SECOND, numSecond);

        return calendar.getTime();
    }

    public static Date convertStringToTime(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (date == null || "".equals(date)){
            return null;
        }
        try {
            return dateFormat.parse(date);

        } catch (ParseException e) {
            System.out.println("Date ParseException, string value:" + date);
        }
        return null;
    }

    public static Date convertStringToDate(String date) throws Exception {
        String pattern = "dd/MM/yyyy";
        return convertStringToTime(date, pattern);
    }

    public static String convertDateToString(Date date) throws Exception {
        if (date == null){
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    /*
     *  @author: dungnt
     *  @todo: get sysdate
     *  @return: String sysdate
     */
    public static String getSysdate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        return convertDateToString(calendar.getTime());
    }

    /*
     *  @author: dungnt
     *  @todo: get sysdate detail
     *  @return: String sysdate
     */
    public static String getSysDateTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

    /*
     *  @author: dungnt
     *  @todo: convert from String to DateTime detail
     *  @param: String date
     *  @return: Date
     */
    public static Date convertStringToDateTime(String date) {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        return convertStringToTime(date, pattern);
    }

    
    /**
     * @author: ThangPV
     * @todo: convert from java.util.Date to java.sql.Date
     */
    public static java.sql.Date convertToSqlDate(java.util.Date utilDate)
    {
        return new java.sql.Date(utilDate.getTime());
    }
    
     public static long daysBetween2Dates(Date date1, Date date2) throws Exception{
        try {
            Calendar c1 = Calendar.getInstance(); //new GregorianCalendar();
            Calendar c2 = Calendar.getInstance(); //new GregorianCalendar();
            c1.set(date1.getYear(), date1.getMonth(), date1.getDate());
            c2.set(date2.getYear(), date2.getMonth(), date2.getDate());
            long daysBetween2Dates = (c2.getTime().getTime() - c1.getTime().getTime())/(24*3600*1000);
            return daysBetween2Dates;
        } catch (Exception ex) {
            throw ex;
        }
    }
     
    public static String convertDateTimeToString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static long secondBetween2Dates(Date date1, Date date2) throws Exception{
        try {
            Calendar c1 = Calendar.getInstance(); //new GregorianCalendar();
            Calendar c2 = Calendar.getInstance(); //new GregorianCalendar();
            c1.set(date1.getYear(), date1.getMonth(), date1.getDate(), date1.getHours(), date1.getMinutes(), date1.getSeconds());
            c2.set(date2.getYear(), date2.getMonth(), date2.getDate(), date2.getHours(), date2.getMinutes(), date2.getSeconds());
            long daysBetween2Dates = (c2.getTime().getTime() - c1.getTime().getTime())/(1000);
            return daysBetween2Dates;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
