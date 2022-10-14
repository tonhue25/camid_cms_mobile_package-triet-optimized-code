/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import static co.siten.myvtg.controller.BaseController.DES_FAIL;
import co.siten.myvtg.service.AdvertisementService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdvertisementController
 *
 * @author partner7
 */
@RestController
@RequestMapping("/api/${version}/advertisement")
public class AdvertisementController extends BaseController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    AdvertisementService service;

    private static final Logger logger = Logger.getLogger(AdvertisementController.class);

    @RequestMapping(value = "/wsInforPartner", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsInforPartnerAdvertisement(@RequestBody RequestBean request) {
        logger.info("Start wsInforPartner API of AdvertisementController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }

            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.saveInforPartnerAdvertisement(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }
}
