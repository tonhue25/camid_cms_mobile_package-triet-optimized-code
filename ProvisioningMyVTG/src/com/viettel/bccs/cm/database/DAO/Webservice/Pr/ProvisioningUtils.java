/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.bccs.cm.database.DAO.Webservice.Pr;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author Ta Manh Tuan
 */
public class ProvisioningUtils {

    public static String DB_OR_FILE_KEY = "provisioning.config.read.file.enable";
    public static String ALLOW_SEND_REQUEST_KEY = "provisioning.allow.send.request";

    /**
     * @author              :           TuanTM11
     * @createDate          :           21 - 06 - 2011
     * @return              :           check viec doc cac tham so ket noi Provisioning tu file hay tu db
     */
    public static boolean isReadConfigFile() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("provisioning");
            if (resource != null && resource.getString(DB_OR_FILE_KEY) != null
                    && resource.getString(DB_OR_FILE_KEY).trim().equalsIgnoreCase("true")) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * @author              :           TuanTM11
     * @createDate          :           21 - 06 - 2011
     * @return              :           check co dc send request toi Provisioning hay khong
     */
    public static boolean allowSendRequest() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("provisioning");
            if (resource != null && resource.getString(ALLOW_SEND_REQUEST_KEY) != null
                    && resource.getString(ALLOW_SEND_REQUEST_KEY).trim().equalsIgnoreCase("false")) {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
