/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import co.siten.myvtg.service.AccountServiceImpl;
import com.viettel.common.ViettelService;
import com.vtc.provisioning.client.Service;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author minhnhutnt
 */
public class ServiceClient {

    private static final Logger logger = Logger.getLogger(ServiceClient.class);

    public static ViettelService getInforSub(String msisdn) throws Exception{
        try {
            Service service = new Service();
            HashMap<String, ViettelService> lstRerult = service.getInfoSubOCS(msisdn);
            ViettelService request = lstRerult.get(com.vtc.provisioning.common.Constant.REQUEST);
            logger.info("Request New "+request.toString());
            ViettelService response = lstRerult.get(com.vtc.provisioning.common.Constant.RESPONSE);
            logger.info("Response New "+response.toString());
            return response;
        } catch (Exception ex) {
            logger.info("Error "+ex.getMessage(), ex);
            throw ex;
        }

    }
}
