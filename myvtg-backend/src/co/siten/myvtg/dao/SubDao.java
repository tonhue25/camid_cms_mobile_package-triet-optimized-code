package co.siten.myvtg.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.bean.SubAccountInfoBean;
import co.siten.myvtg.model.myvtg.Hobby;
import co.siten.myvtg.model.myvtg.Job;
import co.siten.myvtg.model.myvtg.Sub;
import co.siten.myvtg.model.soapws.SoapWebServiceResponse;
import co.siten.myvtg.model.subInforWS.Cus;
import co.siten.myvtg.model.subInforWS.CusInforUpdate;
import co.siten.myvtg.model.subInforWS.SubInfoCusWsResponse;
import co.siten.myvtg.model.subInforWS.SubInfoOTPWSResponse;
import co.siten.myvtg.model.subInforWS.SubInforUpdatedWsResponse;
import co.siten.myvtg.model.subInforWS.ImageDetected;
import co.siten.myvtg.util.BCCSWebserviceUtils;
import co.siten.myvtg.util.Common;
import co.siten.myvtg.util.DataUtil;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.xml.sax.SAXException;

/**
 *
 * @author thomc
 *
 */
@Repository("SubDao")
@Qualifier("myvtgtx")
@PropertySource(value = {"classpath:config.properties"})
public class SubDao extends AbstractMyvtgDao<Sub> {

    private static final Logger logger = Logger.getLogger(SubDao.class.getName());
    @Autowired
    private Environment environment;
    private static final String urlDefault = "http://10.79.120.2:8089/registerInfoSub";
    private static final String urlDefaultPP = "http://10.79.120.2:8093/registerInfoSub";
    private static final String WS_PASSPORT = "ws:passportReader";

    public Sub findById(String id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (Sub) criteria.uniqueResult();
    }

    public Sub findByIsdn(String isdn) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("isdn", isdn));
            criteria.addOrder(Order.desc("registerTime"));
            //criteria.setProjection(Projections.max("registerTime"));
            // TODO: check "duplicate sub" error

            //return (Sub) criteria.uniqueResult();
            List<Sub> results = criteria.list();
            if (results.size() > 0) {
                return results.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public int updateSubNameAndSyncDate(String isdn, String name, Date now) {
        Query query = getSession().createQuery("update Sub set name = :name, lastSyncTime= :now where isdn = :isdn");
        query.setString("isdn", isdn);
        query.setString("name", name);
        query.setDate("now", now);
        return query.executeUpdate();
    }

    public Long getSubIdByIsdn(String isdn) {
        StringBuilder sb = new StringBuilder("SELECT s.subId FROM Sub s WHERE isdn = :isdn");
        Query query = getSession().createQuery(sb.toString());
        query.setString("isdn", isdn);
        return (Long) query.uniqueResult();
    }

    public String getIsdnBySubId(Long subId) {
        try {
            StringBuilder sb = new StringBuilder("SELECT s.isdn FROM Sub s WHERE subId = :subId");
            Query query = getSession().createQuery(sb.toString());
            query.setLong("subId", subId);
            return (String) query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    public SubAccountInfoBean getSubByIsdn(String isdn) {
        StringBuilder sb = new StringBuilder("SELECT s FROM Sub s WHERE isdn = :isdn AND status = 1");
        Query query = getSession().createQuery(sb.toString());
        query.setString("isdn", isdn);

        //daibq fix exception query did not return a unique result: 2
        //		Sub sub = (Sub) query.uniqueResult();
        List<Sub> listSub = query.list();
        Sub sub = null;
        if (!DataUtil.isNullOrEmpty(listSub)) {
            sub = listSub.get(0);
        }

        String jobName = "";
        String hobbyName = "";
        if (sub != null) {
            if (sub.getHobbyId() != null) {
                sb = new StringBuilder("SELECT s FROM Hobby s WHERE id = :id");
                query = getSession().createQuery(sb.toString());
                query.setLong("id", sub.getHobbyId());

                Hobby h = (Hobby) query.uniqueResult();
                if (h != null) {
                    hobbyName = h.getName();
                }

            }
            if (sub.getJobId() != null) {
                sb = new StringBuilder("SELECT s FROM Job s WHERE id = :id");
                query = getSession().createQuery(sb.toString());
                query.setLong("id", sub.getJobId());

                Job h = (Job) query.uniqueResult();
                if (h != null) {
                    jobName = h.getName();
                }
            }
            SubAccountInfoBean bean = new SubAccountInfoBean(sub.getName(), sub.getBirthDate(), jobName, hobbyName,
                    sub.getEmail(), sub.getLastSyncTime());
            return bean;
        }

        return null;
    }

    /**
     * Author:
     *
     * @param isdn: phone number of subscriber
     * @return a subscriber object
     */
    public Sub getSubscriberByIsdn(String isdn) {
        Session session = getSession();
        Sub sub = (Sub) session.load(Sub.class, isdn);
        logger.info("Sub loaded succesfully, Sub detail is " + sub);
        return sub;
    }

    //partner 7
    public SubInfoOTPWSResponse getSubOTP(String isdn) throws Exception {
        JSONObject json = new JSONObject();
        json.put("isdn", isdn);
        String url = environment.getProperty(Common.URL_BCCSGW, urlDefault);
        String response = new BCCSWebserviceUtils().callBCCSWebservice(url, json, "ws:subscriberGetOTP");
        SubInfoOTPWSResponse result = getSubInforOTPResponseWS(response);
        return result;
    }

    //
    public Object checkSubscriberIsdn(String isdn, String token, String language) {
        JSONObject json = new JSONObject();
        json.put("username", "addba2e908c412ca");
        json.put("password", "523cc9765677493c4a2fe6ef8b80d222");
        json.put("wscode", "ws:subscriberCheckIsdn");
        json.put("isdn", isdn);
        json.put("token", token);
        json.put("lang", language);
        String url = environment.getProperty(Common.URL_BCCSGW, urlDefault);
        String response = new BCCSWebserviceUtils().callBCCSWebservice(url, json, "");
        SubInfoCusWsResponse subInfoCusWsResponse = null;
        return subInfoCusWsResponse;
    }

     public SubInfoCusWsResponse getSubscriberInforCusByIsdn(String isdn, String token, String code) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("isdn", isdn);
        json.put("token", token);
        String url = environment.getProperty(Common.URL_BCCSGW, urlDefault);
        String response = new BCCSWebserviceUtils().callBCCSWebservice(url, json, "ws:subscriberGetInforCusByIsdn");
        SubInfoCusWsResponse subInfoCusWsResponse = getSubInforCusByIsdnResponseWS(response);
        return subInfoCusWsResponse;
    }

    public SubInforUpdatedWsResponse updateSubscriberInforCusByIsdn(CusInforUpdate cusInforUpdate) throws Exception {
        JSONObject json = new JSONObject();
        json.put("isdn", cusInforUpdate.getIsdn());
        json.put("lang", cusInforUpdate.getLang());
        json.put("isScan", cusInforUpdate.getIsScan());
        json.put("idType", cusInforUpdate.getIdType());
        json.put("fullName", cusInforUpdate.getFullName());
        json.put("idNumber", cusInforUpdate.getIdNumber());
        json.put("dob", cusInforUpdate.getDob());
        json.put("gender", cusInforUpdate.getGender());
        json.put("issueDate", cusInforUpdate.getIssueDate());
        json.put("province", cusInforUpdate.getProvince());
        json.put("district", cusInforUpdate.getDistrict());
        json.put("commune", cusInforUpdate.getCommune());
        json.put("street", cusInforUpdate.getStreet());
        json.put("homeNo", cusInforUpdate.getHomeNo());
        json.put("fullAddress", cusInforUpdate.getFullAddress());
        json.put("nationality", cusInforUpdate.getNationality());
        json.put("contact", cusInforUpdate.getContact());
        json.put("expireDate", cusInforUpdate.getExpireDate());
        json.put("visaDate", cusInforUpdate.getVisaDate());
        json.put("subName", cusInforUpdate.getSubName());
        json.put("subGender", cusInforUpdate.getSubGender());
        json.put("subDateBirth", cusInforUpdate.getSubDateBirth());
        json.put("relationship", cusInforUpdate.getRelationship());
        //Image list
        String image1 = new BCCSWebserviceUtils().buildImageList(cusInforUpdate.getImageList().get(0).getData(), cusInforUpdate.getImageList().get(0).getName());
        String image2 = new BCCSWebserviceUtils().buildImageList(cusInforUpdate.getImageList().get(1).getData(), cusInforUpdate.getImageList().get(1).getName());
        String image3 = new BCCSWebserviceUtils().buildImageList(cusInforUpdate.getImageList().get(2).getData(), cusInforUpdate.getImageList().get(2).getName());
        json.put("token", "c1u1o1n1g143045ef95bb959ab2448f9072c086c90d01a4");
        String url = environment.getProperty(Common.URL_BCCSGW, urlDefault);
        //String response = new BCCSWebserviceUtils().callBCCSWebservice(url, json, "ws:subscriberUpdateCustomerInfo", image1, image2, image3);
        String response = new BCCSWebserviceUtils().callBCCSWebservice(url, json, "ws:subscriberUpdateCustomerInfoCamID", image1, image2, image3);
        SubInforUpdatedWsResponse subInfoCusWsResponse = updateSubInforCusByIsdnResponseWS(response);
        return subInfoCusWsResponse;
    }

    public ImageDetected detectInforFromImage(String image, String token, String wsCode) throws Exception {
        JSONObject json = new JSONObject();
        json.put("token", token);
        json.put("image", image);
        String url = environment.getProperty(Common.URL_BCCSGW, urlDefault);
        //mDealer not merge code => call to 2 service different. We remove later
        if (WS_PASSPORT.equals(wsCode)) {
            url = environment.getProperty("URL_BCCSGW_PP", urlDefaultPP);;
        }
        String response = new BCCSWebserviceUtils().callBCCSWebservice(url, json, wsCode);
        ImageDetected imageDetectedResponse;
        if (WS_PASSPORT.equals(wsCode)) {
            imageDetectedResponse = getImageInforDetectResponseWSPP(response);
            //refactor format
            String dobEn = new BCCSWebserviceUtils().formatDateFromPlashToDot(imageDetectedResponse.getDobEn());
            String expiredDate = new BCCSWebserviceUtils().formatDateFromPlashToDot(imageDetectedResponse.getExpiryDate());

            imageDetectedResponse.setDobEn(dobEn);
            imageDetectedResponse.setExpiryDate(expiredDate);

        } else {
            imageDetectedResponse = getImageInforDetectResponseWSIdCart(response);
        }
        return imageDetectedResponse;
    }

    private SubInforUpdatedWsResponse updateSubInforCusByIsdnResponseWS(String contentReturn) throws Exception {
        SubInforUpdatedWsResponse subInfoUpdatedWsResponse = new SubInforUpdatedWsResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_RESPONSE, "/Envelope/Body/subscriberUpdateCustomerInfoCamIDResponse/return/errorCode");
        String xPathErrorDes = environment.getProperty(Common.ERROR_DESCRIPTION_RESPONSE, "/Envelope/Body/subscriberUpdateCustomerInfoCamIDResponse/return/errorDecription");
        String xPathMessageCode = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/subscriberUpdateCustomerInfoCamIDResponse/return/messageCode");
        String xPathRequireOtp = environment.getProperty(Common.REQUIRED_OTP_RESPONSE, "/Envelope/Body/subscriberUpdateCustomerInfoCamIDResponse/return/requireOtp");
        if (StringUtils.isEmpty(xPathErrorCode) || StringUtils.isEmpty(xPathErrorDes) || StringUtils.isEmpty(xPathMessageCode) || StringUtils.isEmpty(xPathRequireOtp)) {
            return subInfoUpdatedWsResponse;
        }
        String errorCode = soapWebServiceResponse.getTextContent(xPathErrorCode);
        String errorDes = soapWebServiceResponse.getTextContent(xPathErrorDes);
        String messageCode = soapWebServiceResponse.getTextContent(xPathMessageCode);
        String requireOtp = soapWebServiceResponse.getTextContent(xPathRequireOtp);
        subInfoUpdatedWsResponse.setErrorCode(errorCode);
        subInfoUpdatedWsResponse.setErrorDescription(errorDes);
        subInfoUpdatedWsResponse.setMessageCode(messageCode);
        subInfoUpdatedWsResponse.setRequireOtp(requireOtp);
        return subInfoUpdatedWsResponse;
    }

    private SubInfoCusWsResponse getSubInforCusByIsdnResponseWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SubInfoCusWsResponse subInfoCusWSResponse = new SubInfoCusWsResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/errorCode");
        String xPathErrorDes = environment.getProperty(Common.ERROR_DESCRIPTION_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/errorDecription");
        String xPathMessageCode = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/messageCode");
        //Cus
        Cus cus = new Cus();
        String xPathAddedDate = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/addedDate");
        String xPathAddedUser = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/addedUser");
        String xPathAddress = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/address");
        String xPathBirthDate = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/birthDate");
        String xPathBusType = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/busType");
        String xPathCorrectCus = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/correctCus");
        String xPathCustId = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/custId");
        String xPathDistrict = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/district");
        String xPathExpireVisa = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/expireVisa");
        String xPathHome = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/home");
        String xPathIdExpireDate = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/idExpireDate");
        String xPathIdIssueDate = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/idIssueDate");
        String xPathIdNo = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/idNo");
        String xPathIdType = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/idType");
        String xPathIsUpdate = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/isUpdate");
        String xPathName = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/name");
        String xPathNationality = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/nationality");
        String xPathNotes = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/notes");
        String xPathPrecinct = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/precinct");
        String xPathProvince = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/province");
        String xPathRelationship = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/relationship");
        String xPathSex = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/sex");
        String xPathStatus = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/status");
        String xPathStreetName = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/streetName");
        String xPathSubDateBirth = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/subDateBirth");
        String xPathSubGender = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/subGender");
        String xPathTelFax = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/telFax");
        String xPathSubName = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/subName");
        String xPathVip = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/vip");
        String xPathEmail = environment.getProperty(Common.ADDED_DATE_RESPONSE, "/Envelope/Body/subscriberGetInforCusByIsdnResponse/return/cus/email");
        if (StringUtils.isEmpty(xPathErrorCode) || StringUtils.isEmpty(xPathErrorDes) || StringUtils.isEmpty(xPathMessageCode)) {
            return subInfoCusWSResponse;
        }
        String errorCode = soapWebServiceResponse.getTextContent(xPathErrorCode);
        String errorDes = soapWebServiceResponse.getTextContent(xPathErrorDes);
        String messageCode = soapWebServiceResponse.getTextContent(xPathMessageCode);
        subInfoCusWSResponse.setErrorCode(errorCode);
        subInfoCusWSResponse.setErrorDescription(errorDes);
        subInfoCusWSResponse.setMessageCode(messageCode);
        subInfoCusWSResponse.setCus(cus);
        //
        cus.setAddedDate(soapWebServiceResponse.getTextContent(xPathAddedDate));
        cus.setAddedUser(soapWebServiceResponse.getTextContent(xPathAddedUser));
        cus.setAddress(soapWebServiceResponse.getTextContent(xPathAddress));
        cus.setBirthDate(soapWebServiceResponse.getTextContent(xPathBirthDate));
        cus.setBusType(soapWebServiceResponse.getTextContent(xPathBusType));
        cus.setCorrectCus(soapWebServiceResponse.getTextContent(xPathCorrectCus));
        cus.setCustId(soapWebServiceResponse.getTextContent(xPathCustId));
        cus.setDistrict(soapWebServiceResponse.getTextContent(xPathDistrict));
        cus.setExpireVisa(soapWebServiceResponse.getTextContent(xPathExpireVisa));
        cus.setHome(soapWebServiceResponse.getTextContent(xPathHome));
        cus.setIdExpireDate(soapWebServiceResponse.getTextContent(xPathIdExpireDate));
        cus.setIdIssueDate(soapWebServiceResponse.getTextContent(xPathIdIssueDate));
        cus.setIdNo(soapWebServiceResponse.getTextContent(xPathIdNo));
        cus.setIdType(soapWebServiceResponse.getTextContent(xPathIdType));
        cus.setIsUpdate(soapWebServiceResponse.getTextContent(xPathIsUpdate));
        cus.setName(soapWebServiceResponse.getTextContent(xPathName));
        cus.setNationality(soapWebServiceResponse.getTextContent(xPathNationality));
        cus.setNotes(soapWebServiceResponse.getTextContent(xPathNotes));
        cus.setPrecinct(soapWebServiceResponse.getTextContent(xPathPrecinct));
        cus.setProvince(soapWebServiceResponse.getTextContent(xPathProvince));
        cus.setRelationship(soapWebServiceResponse.getTextContent(xPathRelationship));
        cus.setSex(soapWebServiceResponse.getTextContent(xPathSex));
        cus.setStatus(soapWebServiceResponse.getTextContent(xPathStatus));
        cus.setStreetName(soapWebServiceResponse.getTextContent(xPathStreetName));
        cus.setSubDateBirth(soapWebServiceResponse.getTextContent(xPathSubDateBirth));
        cus.setSubGender(soapWebServiceResponse.getTextContent(xPathSubGender));
        cus.setTelFax(soapWebServiceResponse.getTextContent(xPathTelFax));
        cus.setSubName(soapWebServiceResponse.getTextContent(xPathSubName));
        cus.setVip(soapWebServiceResponse.getTextContent(xPathVip));
        cus.setEmail(soapWebServiceResponse.getTextContent(xPathEmail));

        return subInfoCusWSResponse;
    }

    private SubInfoOTPWSResponse getSubInforOTPResponseWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SubInfoOTPWSResponse subInfoOTPWSResponse = new SubInfoOTPWSResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_RESPONSE, "/Envelope/Body/subscriberGetOTPResponse/return/errorCode");
        String xPathErrorDes = environment.getProperty(Common.ERROR_DESCRIPTION_RESPONSE, "/Envelope/Body/subscriberGetOTPResponse/return/errorDecription");
        String xPathMessageCode = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/subscriberGetOTPResponse/return/messageCode");
        String xPathRequireOtp = environment.getProperty(Common.REQUIRED_OTP_RESPONSE, "/Envelope/Body/subscriberGetOTPResponse/return/requireOtp");
        if (StringUtils.isEmpty(xPathErrorCode) || StringUtils.isEmpty(xPathErrorDes) || StringUtils.isEmpty(xPathMessageCode) || StringUtils.isEmpty(xPathRequireOtp)) {
            return subInfoOTPWSResponse;
        }
        String errorCode = soapWebServiceResponse.getTextContent(xPathErrorCode);
        String errorDes = soapWebServiceResponse.getTextContent(xPathErrorDes);
        String messageCode = soapWebServiceResponse.getTextContent(xPathMessageCode);
        String requireOtp = soapWebServiceResponse.getTextContent(xPathRequireOtp);
        //
        subInfoOTPWSResponse.setErrorCode(errorCode);
        subInfoOTPWSResponse.setErrorDescription(errorDes);
        subInfoOTPWSResponse.setMessageCode(messageCode);
        subInfoOTPWSResponse.setRequireOtp(requireOtp);
        return subInfoOTPWSResponse;
    }

    private ImageDetected getImageInforDetectResponseWSIdCart(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        ImageDetected imageDetected = new ImageDetected();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_RESPONSE, "/Envelope/Body/idCardReaderResponse/return/errorCode");
        String xPathMessageCode = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/idCardReaderResponse/return/messageCode");

        String xPathDobEn = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/idCardReaderResponse/return/idCardInfo/enDob");
        String xPathProvinceEn = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/idCardReaderResponse/return/idCardInfo/enProvince");
        String xPathSexEn = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/idCardReaderResponse/return/idCardInfo/enSex");
        String xPathIdNumber = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/idCardReaderResponse/return/idCardInfo/idNumber");
        String xPathName = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/idCardReaderResponse/return/idCardInfo/name");

        imageDetected.setErrorCode(soapWebServiceResponse.getTextContent(xPathErrorCode));
        //modify response code similar like errorCode of camid
        if ("000".equals(soapWebServiceResponse.getTextContent(xPathErrorCode))) {
            imageDetected.setErrorCode("0");
        }
        imageDetected.setMessageCode(soapWebServiceResponse.getTextContent(xPathMessageCode));
        imageDetected.setIdNumber(soapWebServiceResponse.getTextContent(xPathIdNumber));
        imageDetected.setName(soapWebServiceResponse.getTextContent(xPathName));
        imageDetected.setDobEn(soapWebServiceResponse.getTextContent(xPathDobEn));
        imageDetected.setProvinceEn(soapWebServiceResponse.getTextContent(xPathProvinceEn));
        imageDetected.setSexEn(soapWebServiceResponse.getTextContent(xPathSexEn));
        return imageDetected;
    }

    private ImageDetected getImageInforDetectResponseWSPP(String contentReturn) throws Exception {
        ImageDetected imageDetected = new ImageDetected();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);

        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/errorCode");
        String xPathMessageCode = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/messageCode");

        String xPathDobEn = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/passportInfo/birthday");
        String xPathSexEn = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/passportInfo/sex");
        String xPathSurName = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/passportInfo/surname");
        String xPathGivenName = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/passportInfo/givenName");
        String xPathDocumentNum = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/passportInfo/documentNum");
        String xPathExpiredDate = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/passportInfo/expiryDate");
        String xPathNationality = environment.getProperty(Common.MESSAGE_CODE_RESPONSE, "/Envelope/Body/passportReaderResponse/return/passportInfo/country");

        imageDetected.setErrorCode(soapWebServiceResponse.getTextContent(xPathErrorCode));
        //modify response code similar like errorCode of camid
        if ("000".equals(soapWebServiceResponse.getTextContent(xPathErrorCode))) {
            imageDetected.setErrorCode("0");
        }
        String name = soapWebServiceResponse.getTextContent(xPathSurName) + " " + soapWebServiceResponse.getTextContent(xPathGivenName);
        imageDetected.setName(name);
        imageDetected.setMessageCode(soapWebServiceResponse.getTextContent(xPathMessageCode));

        imageDetected.setIdNumber(soapWebServiceResponse.getTextContent(xPathDocumentNum));
        imageDetected.setDobEn(soapWebServiceResponse.getTextContent(xPathDobEn));
        imageDetected.setSexEn(soapWebServiceResponse.getTextContent(xPathSexEn));
        imageDetected.setNationality(soapWebServiceResponse.getTextContent(xPathNationality));
        imageDetected.setExpiryDate(soapWebServiceResponse.getTextContent(xPathExpiredDate));
        return imageDetected;
    }

    public String refactorTimeRedundant(String timeRedun) {
        String result = "2010-10-01T00:00:00+07:00";
        int indexT = result.indexOf("T");
        return result.substring(0, indexT);
    }
    
}
