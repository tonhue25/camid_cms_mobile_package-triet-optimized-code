package co.siten.myvtg.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.viettel.common.ViettelService;

public class AppUtil {
	private static final Logger logger = Logger.getLogger(AppUtil.class.getName());

	public static String normIsdn(String isdn, String countryCode) {
		if (isdn == null)
			return "";
		String result = isdn;
		if (isdn.startsWith("0") && isdn.length() > 0)
			result = isdn.substring(1);
		if (isdn.startsWith(countryCode) && isdn.length() >= countryCode.length())
			result = isdn.substring(countryCode.length());
		return result;
	}


	public static SimpleDateFormat getDateFormat(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
		// df.setTimeZone(TimeZone.getTimeZone("UTC"));
		// df.setLenient(false);
		return df;
	}

	public static Date getDateFromString(String date, String format) {
		try {
			return AppUtil.getDateFormat(format).parse(date);
		} catch (Exception e) {
			logger.error("error", e);
			try {
				String tmpDate = date.toLowerCase();
				if (tmpDate.endsWith("am") || tmpDate.endsWith("pm")) {
					return AppUtil.getDateFormat("MM/dd/yyyy hh:mm:ss a").parse(date);
				}
			} catch (Exception ex) {
				logger.error("error", ex);
			}
		}

		return null;
	}

	public static boolean checkResponse(ViettelService changeZoneResponse) {
		return (changeZoneResponse != null && !"".equals(changeZoneResponse.toString().trim())
				&& changeZoneResponse.get("responseCode") != null
				&& "0".equals(changeZoneResponse.get("responseCode".toString().trim())));
	}

	public static Date getTimeStartOfDay(long time) {
		Date startDate = new Date(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getTimeEndOfDay(long time) {
		Date endDate = new Date(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	//
	public static void main(String[] args) {
		try {
			// String nu= "null";
			//
			// BigDecimal b= new BigDecimal(nu);
			String output = "";
			String[] plainText = { "ftp-myvtg.demo.siten.vn", "vn.siten.demo.myvtg.ftp", "sDnKnCNH0zCibGvpdbuq" };

			output = AES.encrypt(plainText[0]); // encrypt
			System.out.println("ftp.host= " + output);

			output = AES.encrypt(plainText[1]); // encrypt
			System.out.println("ftp.username= " + output);

			output = AES.encrypt(plainText[2]); // encrypt
			System.out.println("ftp.password= " + output);

			List<Class<?>> classes = ClassFinder.find("co.siten.myvtg.controller");

			for (Class<?> cl : classes) {
				//
				System.out.println("============" + cl.getSimpleName() + "================");

				Object controller = cl.newInstance();
				Method[] methods = controller.getClass().getMethods();
				for (Method method : methods) {
					if (method.getAnnotation(RequestMapping.class) != null) {
						String value = method.getAnnotation(RequestMapping.class).value()[0];
						value = value.substring(1, value.length());
						String methodStr = ((RequestMethod) method.getAnnotation(RequestMapping.class).method()[0])
								.name();
						output = AES.encrypt(value); // encrypt
						System.out.println(value + " " + output);

					}
				}
			}

		} catch (Exception e) {
			
		}
	}
}
