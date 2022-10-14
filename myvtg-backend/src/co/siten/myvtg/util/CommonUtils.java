/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author daibq
 */
public class CommonUtils {

    /**
     * Get ISDN from number without prefix "0".
     *
     * @param number
     * @return
     */
    public static String getIsdn(String number) {
        number = StringUtils.trimToEmpty(number);
        if (number.isEmpty()) {
            return number;
        }
        while (number.indexOf("0") == 0) {
            number = number.substring(1);
        }
        if (number.length() > 3 && number.indexOf("855") == 0) {
            return number.substring(3);
        }
        return number;
    }

    /**
     * Get msisdn
     *
     * @param number
     * @return
     */
    public static String getMsisdn(String number) {
        number = getIsdn(number);
        if (!number.isEmpty()) {
            return "855" + number;
        }
        return number;
    }

    /**
     * Check a state is from step by step or not. If have more than one "*" in
     * params ,one at first and has last "#", this is not step by step.
     *
     * @param params
     * @return
     */
    public static boolean isStepByStep(String params) {
        int firstIndex = params.indexOf("*");
        int lastIndex = params.lastIndexOf("*");
        if (firstIndex == 0 && lastIndex > firstIndex && (params.indexOf("#") == params.length() - 1)) {
            return false;
        }
        return true;
    }

    public static Map<String, Object> buildRequestForGetHttp(Object... parameters) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("clientType", "ios");
        paramsMap.put("channelId", parameters[0]);
        paramsMap.put("channelDesc", parameters[1]);
        paramsMap.put("createdDate", "");
        paramsMap.put("about", "");
        paramsMap.put("msisdn", parameters[2]);
        paramsMap.put("channelName", parameters[3]);
        paramsMap.put("userId", parameters[4]);
        paramsMap.put("bannerChannel", parameters[5]);
        paramsMap.put("aboutTitle", parameters[6]);
        paramsMap.put("avatarChannel", parameters[7]);
        paramsMap.put("aboutImg", parameters[8]);
        return paramsMap;
    }
}
