package co.siten.myvtg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtil {
	private static final Logger logger = LoggerFactory.getLogger(AppUtil.class);

	public static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat("mm/dd/yyyy hh:mm:ss a", Locale.ENGLISH);
	}

	public static Date getDateFromString(String date) {
		try {
			return AppUtil.getDateFormat().parse(date);
		} catch (Exception e) {
			logger.error("", e);
		}

		return null;
	}
	//
	// public static void main(String[] args) {
	// try {
	// String output = "";
	//// String[] plainText = { "10.30.164.140", "vtz_cm_app",
	// "VtgPtudVTZ2015"};
	// String[] plainText = { "10.120.47.17", "api_app", "api#2017"};
	// // //16 bytes = 128 bit
	// for (String plain : plainText) {
	// output = AES.encrypt(plain); // encrypt
	// System.out.println("encrypted text=" + output);
	// output = AES.decrypt(output); // decrypt
	// System.out.println("decrypted text=" + output);
	// System.out.println("===========================");
	// }
	//
	// List<Class<?>> classes = ClassFinder.find("co.siten.myvtg.controller");
	//
	// for (Class<?> cl : classes) {
	// //
	// System.out.println("============"+cl.getSimpleName()+"================");
	//
	// Object controller = cl.newInstance();
	// Method[] methods = controller.getClass().getMethods();
	// for (Method method : methods) {
	// if (method.getAnnotation(RequestMapping.class) != null) {
	// String value = method.getAnnotation(RequestMapping.class).value()[0];
	// value= value.substring(1, value.length());
	// String methodStr = ((RequestMethod)
	// method.getAnnotation(RequestMapping.class).method()[0])
	// .name();
	// output = AES.encrypt(value + "." + methodStr); // encrypt
	// System.out.println(value + "." + methodStr + " " + value + " " + output);
	//
	// }
	// }
	// }
	//
	// } catch (Exception e) {
	// logger.error("", e);
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
