/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.database.DAO.Webservice.Pr;

import com.viettel.common.ExchMsg;
import com.viettel.common.ObjectClientChannel;
import com.viettel.common.OriginalViettelMsg;
import com.viettel.common.ViettelService;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author Vu Viet Dinh
 */
public class InterfacePr {

    public static ObjectClientChannel getPrConnectionByTelecomService() throws Exception{
        ObjectClientChannel clientChannel = null;
        ProConfig proConfig = null;
        try {
            ResourceBundle resource = ResourceBundle.getBundle("provisioning");
            if (resource != null) {
                proConfig = new ProConfig();
                proConfig.setUsername(resource.getString("pro_user"));
                proConfig.setPassword(resource.getString("pro_pass"));
                proConfig.setIp(resource.getString("pro_ip"));
                proConfig.setPort(resource.getString("pro_port"));
            }
            if (proConfig != null) {
                clientChannel = new ObjectClientChannel(proConfig.getIp(), Integer.parseInt(proConfig.getPort()),
                        proConfig.getUsername(), proConfig.getPassword(), true, true);

                clientChannel.setReceiverTimeout(60000);
                clientChannel.connect();
            }

        } catch (Exception ex) {

            throw ex;
        }
        return clientChannel;
    }

    public ViettelService request(ViettelService requestPr) throws Exception {
        ViettelService responsePr = null;
        try {
            // if test bussiness flow then didn't send request to provisioning
            if (ProvisioningUtils.allowSendRequest()) {
                responsePr = (ViettelService) getPrConnectionByTelecomService().send(requestPr);
            } else {
                responsePr = new ViettelService();
                responsePr.set("responseCode", "0");
                responsePr.set("content", "Test bussiness flow over exchange");
                return responsePr;
            }

            if (responsePr != null) {
                String responseXML = responsePr.toString();
                if (responseXML != null && responseXML.toString().trim().length() >= 4000) {
                    int lengReponse = responseXML.toString().trim().length();
                    responseXML = responseXML.toString().trim().substring(lengReponse - 3000, lengReponse);
                }
            }
        } catch (Exception ex) {
            responsePr = new ViettelService();
            responsePr.set("requestPr", requestPr.toString());
            responsePr.set("responseCode", "-5555");
            responsePr.set("content", ex.toString());
            ex.printStackTrace();
            throw ex;
        }

        if (responsePr != null) {
            responsePr.set("requestPr", requestPr.toString());
        } else {
            responsePr = new ViettelService();
            responsePr.set("requestPr", requestPr.toString());
            responsePr.set("responseCode", "-1");
            responsePr.set("content", "Provisioning Timeout Or getConnection Error");
        }
        return responsePr;
    }
}