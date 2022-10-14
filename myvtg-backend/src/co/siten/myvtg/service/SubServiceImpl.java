/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.*;
import co.siten.myvtg.exception.MyMetfoneException;
import co.siten.myvtg.model.myvtg.Hobby;
import co.siten.myvtg.model.myvtg.Job;
import co.siten.myvtg.model.myvtg.Sub;
import co.siten.myvtg.model.subInforWS.*;
import co.siten.myvtg.util.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author duyth
 */
@Service("SubService")
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
@PropertySource(value = {"classpath:config.properties"})
public class SubServiceImpl implements SubService {

    private static final Logger logger = Logger.getLogger(SubServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";

    private static final String DES_FAILED = "ex.des.fai.";
    private static final String DES_SUC = "su.des.suc.";

    @Autowired
    private SubDao subDao;

    @Autowired
    private SubServiceDao subServiceDao;

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private HobbyDao hobbyDao;

    @Autowired
    private FTPUploader fileUploader;

    @Autowired
    private Environment environment;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    ImDao imdao;

    @Autowired
    ConfigUtils configUtils;

    @Autowired
    CmpreDao cmpreDao;

    @Override
    public String getAvatar(String isdn) throws Exception {
//        Sub sub = subDao.findByIsdn(isdn);
//        String avatarUrl = sub.getAvatarUrl();
//        if (DataUtil.isNullOrEmpty(avatarUrl)) {
//            logger.info("avatar url is null or empty");
//            return avatarUrl;
//        }
//        fileUploader.connect();
//        return fileUploader.getRemoteFile(avatarUrl);
        Sub sub = subDao.findByIsdn(isdn);
        //daibq fix loi null
        String avatarUrl = "";
        if (!DataUtil.isNullObject(sub)) {
            avatarUrl = sub.getAvatarUrl();
            if (DataUtil.isNullOrEmpty(avatarUrl)) {
                logger.info("avatar url is null or empty");
                return avatarUrl;
            }
            FTPClient ftp = new FTPClient();
            fileUploader.connect(ftp);
            return fileUploader.getRemoteFile(avatarUrl, ftp);
        }
        return avatarUrl;
    }

    @Override
    public void updateSub(Sub sub) throws Exception {
        Sub subFound = null;
        if (!DataUtil.isNullOrEmpty(sub.getIsdn())) {
            subFound = subDao.findByIsdn(sub.getIsdn());
        } else if (!DataUtil.isNullOrEmpty(sub.getId())) {
            subFound = subDao.findById(sub.getId());
        }

        if (subFound == null) {
            throw new MyMetfoneException(Constants.NOT_FOUND_DATA, "myMetfone.subscriber.not.found.", "myMetfone.subscriber.not.found");
        }

        if (sub.getJobId() != null) {
            Job job = jobDao.findById(sub.getJobId());
            if (job == null) {
                throw new MyMetfoneException(Constants.NOT_FOUND_DATA, "myMetfone.job.not.found.", "myMetfone.job.not.found.");
            }
        }

        if (sub.getHobbyId() != null) {
            Hobby hobby = hobbyDao.findById(sub.getHobbyId());
            if (hobby == null) {
                throw new MyMetfoneException(Constants.NOT_FOUND_DATA, "myMetfone.hobby.not.found.", "myMetfone.hobby.not.found.");
            }
        }

        if (sub.getAvatar() != null) {
            // save avatar image to disk with FTP method
            String remoteFileName = "avatar_" + subFound.getIsdn() + ".png";
            FTPClient ftp = new FTPClient();
            fileUploader.connect(ftp);
            fileUploader.uploadFile(
                    new ByteArrayInputStream(Base64.decode(sub.getAvatar())),
                    remoteFileName, ftp
            );

            subFound.setAvatarUrl(remoteFileName);
            sub.setAvatar(null);
        }

        subFound.copy(sub);
        subDao.update(subFound);
    }

    @Override
    public Map<String, Object> getUserInfo(String encrypt) {
        String privateKey = environment.getProperty("PRIVATE_KEY");

        String dataEncrypt = AppCryptUtils.getDataEncrypt(encrypt);
        try {
            String decryptedIsdn = AppCryptUtils.decrypt(dataEncrypt, privateKey);
            Sub sub = subDao.findByIsdn(decryptedIsdn);
            Map<String, Object> expectedInfo = new HashMap<String, Object>();
            if (sub != null) {
                expectedInfo.put("fullName", sub.getName());
                expectedInfo.put("email", sub.getEmail());
                expectedInfo.put("dateOfBirth", sub.getBirthDate());
                expectedInfo.put("gender", sub.getGender() == 1 ? "male" : "female");
            } else {
                logger.info("### Cannot find isdn");
            }
            return expectedInfo;
        } catch (Exception e) {
            logger.error("### Cannot find isdn" + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String isdn = "974247778";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoVkSXM5GoRR7TOKX+lgjlfpUVEB/DI4ftLm3jHtLRVGRCHrnULiLt83npVI0HhbWkDt8ui3oJo6cPSlUY5W63aUjHLc1a5Jsrqt+LodqkGrfrsdCvPHDfyYQNoExPz/4JiyrcNMju2bC/T6mfWj4pmf4bcHl2AHvpW9yRwdIRnNF8FDuSR+LaJm4aFo52wk7uqD/z76hXnWSvI80W9bDj8zbN2+ir3+VZo3Pppnv1w5nidAqiDgLA972znekm0FleKdwe91O4hRGMfhwMoUl1ZgNcwrJJ6rSjRysg8KpnbF1K0hVGSt31uzECoERK/+QTrD8tuT/fmISKPeLcCXMSQIDAQAB";
        String enpypt = AppCryptUtils.encrypt(isdn, pubKey);
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChWRJczkahFHtM4pf6WCOV+lRUQH8Mjh+0ubeMe0tFUZEIeudQuIu3zeelUjQeFtaQO3y6Legmjpw9KVRjlbrdpSMctzVrkmyuq34uh2qQat+ux0K88cN/JhA2gTE/P/gmLKtw0yO7ZsL9PqZ9aPimZ/htweXYAe+lb3JHB0hGc0XwUO5JH4tombhoWjnbCTu6oP/PvqFedZK8jzRb1sOPzNs3b6Kvf5Vmjc+mme/XDmeJ0CqIOAsD3vbOd6SbQWV4p3B73U7iFEYx+HAyhSXVmA1zCsknqtKNHKyDwqmdsXUrSFUZK3fW7MQKgREr/5BOsPy25P9+YhIo94twJcxJAgMBAAECggEAYll1A/UIwG9HqVoF4aeQKX9pn+Ggj/qiAPujkpIlE5/glBa2oVGcePGd3fv5oM2zBD/+i04oUDhlrDJhKkK+oRRkDsDuYx/h96a5R4trvZQnvNXhLGMCE6MUBuVBK5jGnh5kcmWBRl4hRVTHD3LI/kYp/F1QyRaFCYahVgJuX+Iy+qJkqVz5kvrcrWYli6Fz5zxmKUP6qa9Z7mgf0t0MWFvB57NBrhSvrXKLgmtUb3KA3j+WuqaKqyAkxVx0w+yFDmXSqmbmnMaF5WYiUQvaQXwmuTBQrTs6FoPh/Tpbqmk+7c+t6uy2fc5ak2MwzafAIF6aXR0GOOPa7j3R0cEeMQKBgQDh8RKJqik31xVRcpXUqvai+bK9kFUIRiuyK7TmrXyxeF86QzvIHuD9GjW4YLmFN3L5Cs6eswymiu+fgfVOfRXiNG/B9xGxPJBP4DY1SD2/uvd+QpbXWy6LSxsBL0eR/eFGWPe9N6qm8dSTgQ6JBv1m0JxqkhJ5AEcg89fC++agcwKBgQC20B7yCqMnMor9/WnW6VTCYLOGHTQ0S0KmrK1nuyMkvloOVbRLI03Jk9I6QZpx4rJE1k9p+3uxdtcx8QQUIp83HecijzFFZKFYs1g2aLBE7QkkV0smY3DTGiJSqD+Q4/30lrNcNyckPQLpV426QJYbMDbm6kEPfY26uSgWfBJdUwKBgQCTmmff43g18J8OrGrRmjax1ao9SNMkKqNVLFRr9p5lWhvr9gxR+kpFTyAVKatJKiZEKBk/d/0lt9I/RCk7c2AefN8dugim3sfwRqtjN6SPYtdYdqwQWJMZkKE9ruN0roTC7lA6HumvUoDrM/6I6jYVOudUf8Tto47UKjikOug5nQKBgDz42PldNuFB6D2iIYS+ObjCy911LA4s88PnX8ZzEduVaS/Swdk1oyISHVG0w7AsLczrtXzhlXWw70EG45dbnMKjRI2wOHyufWm6Fze+Xtv+OshWHQhmriCaXMw4tUq7392NzbB57KIXfzsYKnruQ6I5yHXnt19VPsoCKy6knvNlAoGAQilSqXVghZW/KaSautv/QqdxFgjZ5jCTtofCzeTwQpQhgmWmpTVTJ6k5dSQjnW/F/Y399tqzILjgAoMvsHSRLCVnyWgJKTyAYYuk7/74HBoc6XCpbhybD7V+ydT8ZZOhdDHE2lxvG8AD94He2LSmgg4DRVLRlu8yPXtOENbBpY4=";
        String isdn2 = AppCryptUtils.decrypt(enpypt, privateKey);
        System.out.print(enpypt);
    }

    @Override
    public BaseResponseBean getSubOTP(RequestBean request, String language) {
        logger.info("### Start business getSubOTP of SubServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String isdn = request.getWsRequest().get("isdn").toString().trim();
            SubInfoOTPWSResponse result = subDao.getSubOTP(isdn);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while getSubOTP: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    //check ISDN allow update information
    @Override
    public BaseResponseBean checkSubscriberIsdn(RequestBean request, String language) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseResponseBean getSubscriberInforCusByIsdn(RequestBean request, String language) {
        logger.info("### Start business getSubscriberInforCusByIsdn of SubServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("code")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("code").toString())) {
            logger.info("Error request : code is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String isdn = request.getWsRequest().get("isdn").toString().trim();
            String code = request.getWsRequest().get("code").toString().trim();
            SubInfoCusWsResponse result = subDao.getSubscriberInforCusByIsdn(isdn, "c1u1o1n1g143045ef95bb959ab2448f9072c086c90d01a4", code);

            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while getSubscriberInforCusByIsdn: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateSubscriberCustomerInfo(RequestBean request, String language) {
        logger.info("### Start business updateSubscriberCustomerInfo of SubServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("lang")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("lang").toString())) {
            logger.info("Error request : lang is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.lang.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("isScan")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isScan").toString())) {
            logger.info("Error request : isScan is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.isScan.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("idType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("idType").toString())) {
            logger.info("Error request : idType is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.idType.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("fullName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fullName").toString())) {
            logger.info("Error request : fullName is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.fullName.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("idNumber")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("idNumber").toString())) {
            logger.info("Error request : idNumber is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.idNumber.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("dob")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("dob").toString())) {
            logger.info("Error request : dob is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.dob.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("gender")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("gender").toString())) {
            logger.info("Error request : gender is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.gender.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("issueDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("issueDate").toString())) {
            logger.info("Error request : issueDate is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.issueDate.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("province")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("province").toString())) {
            logger.info("Error request : province is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.province.empty.", language);
        }
        if (null == request.getWsRequest().get("district")) {
            logger.info("Error request : district is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.district.empty.", language);
        }
        if (null == request.getWsRequest().get("commune")) {
            logger.info("Error request : commune is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.commune.empty.", language);
        }
        if (null == request.getWsRequest().get("street")) {
            logger.info("Error request : street is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.street.empty.", language);
        }
        if (null == request.getWsRequest().get("homeNo")) {
            logger.info("Error request : homeNo is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.homeno.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("fullAddress")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fullAddress").toString())) {
            logger.info("Error request : fullAddress is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.fulladdress.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("nationality")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("nationality").toString())) {
            logger.info("Error request : nationality is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.nationality.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("contact")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("contact").toString())) {
            logger.info("Error request : contact is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.contact.empty.", language);
        }
        if (null == request.getWsRequest().get("expireDate")) {
            logger.info("Error request : expireDate is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.expiredate.empty.", language);
        }
        if (null == request.getWsRequest().get("visaDate")) {
            logger.info("Error request : visaDate is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.visadate.empty.", language);
        }
        if (request.getWsRequest().get("subName") == null) {
            logger.info("Error request : subName is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.subname.empty.", language);
        }
        if (request.getWsRequest().get("subGender") == null) {
            logger.info("Error request : subGender is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.subgender.empty.", language);
        }
        if (request.getWsRequest().get("subDateBirth") == null) {
            logger.info("Error request : subDateBirth is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.subdatebirth.empty.", language);
        }
        if (request.getWsRequest().get("relationship") == null) {
            logger.info("Error request : relationship is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.relationship.empty", language);
        }
        //List image
        if (DataUtil.isNullObject(request.getWsRequest().get("image1Name")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("image1Name").toString())) {
            logger.info("Error request : image1Name is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.imagename.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("image1Data")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("image1Data").toString())) {
            logger.info("Error request : image1Data is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.imagedata.empty.", language);
        }
        //Image 2
        if (DataUtil.isNullObject(request.getWsRequest().get("image2Name")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("image2Name").toString())) {
            logger.info("Error request : image2Name is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.imagename.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("image2Data")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("image2Data").toString())) {
            logger.info("Error request : image2Data is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.imagedata.empty.", language);
        }
        //Image 3
        if (DataUtil.isNullObject(request.getWsRequest().get("image3Name")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("image3Name").toString())) {
            logger.info("Error request : image3Name is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "c", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("image3Data")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("image3Data").toString())) {
            logger.info("Error request : image3Data is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.customerInfo.imagedata.empty.", language);
        }

        BaseResponseBean bean = new BaseResponseBean();
        try {
            CusInforUpdate cusInforUpdate = new CusInforUpdate();
            ImageCus imageCus1 = new ImageCus(request.getWsRequest().get("image1Name").toString().trim(), request.getWsRequest().get("image1Data").toString().trim());
            ImageCus imageCus2 = new ImageCus(request.getWsRequest().get("image2Name").toString().trim(), request.getWsRequest().get("image2Data").toString().trim());
            ImageCus imageCus3 = new ImageCus(request.getWsRequest().get("image3Name").toString().trim(), request.getWsRequest().get("image3Data").toString().trim());
            cusInforUpdate.setImageList(Arrays.asList(imageCus1, imageCus2, imageCus3));
            cusInforUpdate.setIsdn(request.getWsRequest().get("isdn").toString().trim());
            cusInforUpdate.setLang(language == "kh" ? "km" : language);
            cusInforUpdate.setIsScan(request.getWsRequest().get("isScan").toString().trim());
            cusInforUpdate.setIdType(request.getWsRequest().get("idType").toString().trim());
            cusInforUpdate.setFullName(request.getWsRequest().get("fullName").toString().trim());
            cusInforUpdate.setIdNumber(request.getWsRequest().get("idNumber").toString().trim());
            cusInforUpdate.setDob(request.getWsRequest().get("dob").toString().trim());
            cusInforUpdate.setGender(request.getWsRequest().get("gender").toString().trim());
            cusInforUpdate.setIssueDate(request.getWsRequest().get("issueDate").toString().trim());
            cusInforUpdate.setProvince(request.getWsRequest().get("province").toString().trim());
            cusInforUpdate.setDistrict(request.getWsRequest().get("district").toString().trim());
            cusInforUpdate.setCommune(request.getWsRequest().get("commune").toString().trim());
            cusInforUpdate.setStreet(request.getWsRequest().get("street").toString().trim());
            cusInforUpdate.setHomeNo(request.getWsRequest().get("homeNo").toString().trim());
            cusInforUpdate.setFullAddress(request.getWsRequest().get("fullAddress").toString().trim());
            cusInforUpdate.setNationality(request.getWsRequest().get("nationality").toString().trim());
            cusInforUpdate.setContact(request.getWsRequest().get("contact").toString().trim());
            cusInforUpdate.setExpireDate(request.getWsRequest().get("expireDate").toString().trim());
            cusInforUpdate.setVisaDate(request.getWsRequest().get("visaDate").toString().trim());
            cusInforUpdate.setSubName(request.getWsRequest().get("subName").toString().trim());
            cusInforUpdate.setSubGender(request.getWsRequest().get("subGender").toString().trim());
            cusInforUpdate.setSubDateBirth(request.getWsRequest().get("subDateBirth").toString().trim());
            cusInforUpdate.setRelationship(request.getWsRequest().get("relationship").toString().trim());

            SubInforUpdatedWsResponse result = subDao.updateSubscriberInforCusByIsdn(cusInforUpdate);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while updateSubscriberCustomerInfo: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean detectOCRFromImage(RequestBean request, String language) {
        logger.info("### Start business detectOCRFromImage of SubServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("image")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("image").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("type")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("type").toString())) {
            logger.info("Error request : code is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String type = request.getWsRequest().get("type").toString().trim();
            String image = request.getWsRequest().get("image").toString().trim();
            //support only type = passport or non - passport
            String wsCode = "ws:idCardReader";
            if ("3".equals(type)) {
                wsCode = "ws:passportReader";
                //for test 
                ImageDetected imageDetected = subDao.detectInforFromImage(image, "818f47f73f024b26abb11b78e05e1349", wsCode);

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(imageDetected);
                bean.setUserMsg("Success");
                return bean;
            }

            ImageDetected imageDetected = subDao.detectInforFromImage(image, "c1u1o1n1g143045ef95bb959ab2448f9072c086c90d01a4", wsCode);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(imageDetected);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while getSubscriberInforCusByIsdn: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateResponseCusInfor(RequestBean request, String language) {
        logger.info("### Start business updateSubscriberCustomerInfo of SubServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("requestId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("requestId").toString())) {
            logger.info("Error request : requestId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.update.infor.requestId.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("msisdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("msisdn").toString())) {
            logger.info("Error request : msisdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.channel.msisdn.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("tid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("tid").toString())) {
            logger.info("Error request : tid is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.update.infor.tid.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("processTime")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("processTime").toString())) {
            logger.info("Error request : processTime is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.update.infor.processtime.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("response")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("response").toString())) {
            logger.info("Error request : response is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.update.infor.response.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
            logger.info("Error request : status is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.update.infor.status.empty.", language);
        }

        String requestId = request.getWsRequest().get("requestId").toString();
        String msisdn = request.getWsRequest().get("msisdn").toString();
        String tid = request.getWsRequest().get("tid").toString();
        String time = request.getWsRequest().get("processTime").toString();
        String response = request.getWsRequest().get("response").toString();
        String status = request.getWsRequest().get("status").toString();
        boolean isRequestAvailable = imdao.isRequestAvailable(requestId, msisdn);
        if (!isRequestAvailable) {
            logger.info("Error request : cannot find request with msisdn=" + msisdn);
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.update.infor.notmatch.empty.", language);
        }

        //Save log
        Long id = imdao.getSequence("REQ_UPDATE_CUSINFOR_LOG_SEQ");
        boolean isSaveLogSuccess = imdao.saveLogEmoneyResponse(id, msisdn, time, tid, response, status, requestId);
        if (!isSaveLogSuccess) {
            logger.info("Error save log : cannot save log with msisdn=" + msisdn);
        }

        boolean isUpdateSuccess = imdao.updateResponseFromEmoney(requestId, msisdn, time, tid, response, status);
        if (!isUpdateSuccess) {
            logger.info("Error request : cannot update request with msisdn=" + msisdn);
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.update.infor.update.fail.", language);
        }

        BaseResponseBean bean = new BaseResponseBean();
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setUserMsg("Success");
        String contentSms = configUtils.getMessage("SMS_4");
        contentSms = contentSms.replace("{1}", "1");
        contentSms = contentSms.replace("{2}", DataUtil.formatIsdn0(msisdn));
        try {
            cmpreDao.insertMT(msisdn, contentSms, "Metfone");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SubServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }

    /*---------------------------------------------------------------------------*/
    @Override
    public BaseResponseBean getSubServices(RequestBean request, String language) {
        try {
            Map<String, Object> wsRequest = request.getWsRequest();
            String search = DataUtil.nullOrValueS(wsRequest.get("search"));
            String sortBy = DataUtil.nullOrValueS(wsRequest.get("sortBy"));
            String sortType = DataUtil.nullOrValueS(wsRequest.get("sortType"));
            Integer page = DataUtil.nullOrValueI(wsRequest.get("page"));
            Integer size = DataUtil.nullOrValueI(wsRequest.get("size"));
            Long serviceId = DataUtil.nullOrValueL(wsRequest.get("serviceId"));
            if (DataUtil.isNullOrEmpty(page) || DataUtil.isNullOrEmpty(size)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            page = page - 1;
            if (page < 0) page = Constants.CUSTOMER_PAGE_START;
            if (size <= 0) size = Constants.CUSTOMER_RECORD_PER_PAGE;
            Map<String, Object> subServiceList = subServiceDao.getListSubService(search, size, page, sortBy, sortType,serviceId);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, subServiceList);
        } catch (Exception ex) {
            logger.error("An error occurred while getSubServices ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean addOrUpdateSubService(RequestBean request, String language, String username) {
        try {
            Date date = subServiceDao.getTime();
            Map<String, Object> wsRequest = request.getWsRequest();
            String code =DataUtil.nullOrValueS(wsRequest.get("code"));
            String name =DataUtil.nullOrValueS(wsRequest.get("name"));
            String nameKh =DataUtil.nullOrValueS(wsRequest.get("nameKh"));

            String shortDes = DataUtil.nullOrValueS(wsRequest.get("shortDes"));
            String fullDes = DataUtil.nullOrValueS(wsRequest.get("fullDes"));
            String shortDesKh = DataUtil.nullOrValueS(wsRequest.get("shortDesKh"));
            String fullDesKh = DataUtil.nullOrValueS(wsRequest.get("fullDesKh"));
            String iconUrl = DataUtil.nullOrValueS(wsRequest.get("iconUrl"));
            String type = DataUtil.nullOrValueS(wsRequest.get("type"));
            String languageDb = DataUtil.nullOrValueS(wsRequest.get("languageDb"));
            String unit = DataUtil.nullOrValueS(wsRequest.get("unit"));
            String validity = DataUtil.nullOrValueS(wsRequest.get("validity"));
            String formatCode = DataUtil.nullOrValueS(wsRequest.get("formatCode"));
            Integer subServiceOrder = DataUtil.nullOrValueI(wsRequest.get("subServiceOrder"));
            Integer autoRenew = DataUtil.nullOrValueI(wsRequest.get("autoRenew"));
            Integer recommend = DataUtil.nullOrValueI(wsRequest.get("recommend"));
            Integer volume = DataUtil.nullOrValueI(wsRequest.get("volume"));
            Integer price = DataUtil.nullOrValueI(wsRequest.get("price"));
            Integer channelType = DataUtil.nullOrValueI(wsRequest.get("channelType"));
            Long serviceId = DataUtil.nullOrValueL(wsRequest.get("serviceId"));
            Long id = DataUtil.nullOrValueL(wsRequest.get("id"));

            if (DataUtil.isNullOrEmpty(code) || DataUtil.isNullOrEmpty(name) || DataUtil.isNullOrEmpty(nameKh)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            if (subServiceDao.isExistedSubService((code))) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.resource.already.exist.", language);
            }
            co.siten.myvtg.model.myvtg.SubService subService = new co.siten.myvtg.model.myvtg.SubService();

            if (id != null) {
                subService = DataUtil.isNullObject(subServiceDao.findById(id))
                        ? new co.siten.myvtg.model.myvtg.SubService() : subServiceDao.findById(id);
            }
            int status = DataUtil.isNullObject(subService.getStatus()) ? 1 : subService.getStatus();
            subService.setCode(code);
            subService.setName(name);
            subService.setNameKh(nameKh);
            subService.setServiceId(serviceId);
            subService.setShortDes(shortDes);
            subService.setFullDes(fullDes);
            subService.setShortDesKh(shortDesKh);
            subService.setFullDesKh(fullDesKh);
            subService.setIconUrl(iconUrl);
            subService.setType(type);
            subService.setVolume(volume);
            subService.setPrice(price);
            subService.setChannelType(channelType);
            subService.setLanguage(languageDb);
            subService.setUnit(unit);
            subService.setSubServiceOrder(subServiceOrder);
            subService.setVaLiDiTy(validity);
            subService.setFormatCode(formatCode);
            subService.setAutoRenew(autoRenew);
            subService.setRecommend(recommend);
            subService.setStatus(status);
            subService.setCreatedBy(username);
            subService.setCreatedTime(date);

            subServiceDao.insert(subService);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.create.update.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while addSubService ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean changeActive(Long id, String language, String username) {
        try {
            Date date = subServiceDao.getTime();
            if (DataUtil.isNullOrEmpty(id)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            co.siten.myvtg.model.myvtg.SubService service = subServiceDao.findById(id);
            if (service == null) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            if (service.getStatus() == Constants.ACTIVE) {
                service.setStatus(Constants.INACTIVE);
            } else {
                service.setStatus(Constants.ACTIVE);
            }
            service.setLastUpdatedBy(username);
            service.setLastUpdatedTime(date);

            subServiceDao.insert(service);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.change.active.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while changeActiveSubService ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean delete(Long id, String language, String username) {
        try {
            Date date = subServiceDao.getTime();
            if (DataUtil.isNullOrEmpty(id)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            co.siten.myvtg.model.myvtg.SubService service = subServiceDao.findById(id);
            if (service == null) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            service.setStatus(Constants.DELETED);
            service.setLastUpdatedBy(username);
            service.setLastUpdatedTime(date);

            subServiceDao.insert(service);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.delete.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteService ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }
}

/*---------------------------------*/



