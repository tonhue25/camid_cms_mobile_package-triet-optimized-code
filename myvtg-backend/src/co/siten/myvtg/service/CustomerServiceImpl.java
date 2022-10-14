/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.NiceNumber;
import co.siten.myvtg.bean.NiceNumberBean;
import java.util.LinkedHashMap;
import java.util.List;

import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.model.myvtg.Webservice;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.SoapWSResponse;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.util.StringUtils;
import co.siten.myvtg.util.WebServiceUtil;
import com.viettel.pm.database.BO.ProductOffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.xpath.XPathExpressionException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author namdh1
 */

@org.springframework.stereotype.Service("CustomerService")

@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")

public class CustomerServiceImpl implements CustomerService{
    
    @Autowired
	private Environment environment;
	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);

        
	@Autowired
	MessageUtil messageUtil;
        @Autowired
	MyvtgMasterDataDao myvtgDao;
        
        private static final String WS_RECEIVE_COMPLAIN_CARD_FAIL = "wsReceiveComplainCard.fail.";
        private static final String ISDN = "isdn";
        private static final String PRICE = "price";
        private static final String STATUS = "status";
        private static final String TYPE = "type";
        private static final String TYPE_ISDN = "typeisdn";
        private static final String WS_SEARCH_ISDN_FAIL = "wsSearchNumber.fail.";
        private static final String WS_SEARCH_ISDN_SUCCESS = "wsSearchNumber.success.";
        private static final String WS_DO_LOGIN_BY_GET_CODE_FAIL = "wsDoLoginByGetCode.fail.";
        private static final String WS_DO_LOGIN_BY_GET_CODE_SUCCESS = "wsDoLoginByGetCode.success.";
        private static final String WS_VERIFY_BY_CODE_FAIL = "wsVerifyByCode.fail.";
        private static final String WS_VERIFY_BY_CODE_SUCCESS = "wsVerifyByCode.success.";
        private static final String WS_GET_PROFILE_BY_ISDN_FAIL = "wsGetProfileByIsdn.fail.";
        private static final String WS_GET_PROFILE_BY_ISDN_SUCCESS = "wsGetProfileByIsdn.success.";
        private static final String WS_UPDATE_INFO_BY_USER_FAIL = "wsUpdateInfoByUser.fail.";
        private static final String WS_UPDATE_INFO_BY_USER_SUCCESS = "wsUpdateInfoByUser.success.";
        private static final String WS_RESERVE_NUMBER_FAIL = "wsReserveNumber.fail.";
        private static final String WS_RESERVE_NUMBER_SUCCESS = "wsReserveNumber.success.";
        
        private static final String RETURN_TAG = "return";
        private static final String RESULT_TAG = "lstnicenumber";

             
              
  
    private boolean validateIsdn(String isdn, ResponseSuccessBean bean, String language) {
        if (StringUtils.isEmpty(isdn)) {
            logger.error("isdn invalid");
            bean.setUserMsg(messageUtil.getMessage(WS_RECEIVE_COMPLAIN_CARD_FAIL + language));
            bean.setErrorCode(Constants.ERROR_CODE_1);
            return true;
        }
        return false;
    }

        
   private SoapWSResponse doCallWebserviceBccs(LinkedHashMap<String, Object> params, Webservice wsObjectBean) throws Exception {
		if (wsObjectBean != null) {
			WebServiceUtil wsUtil = new WebServiceUtil(wsObjectBean, environment);
			if (params == null)
				params = new LinkedHashMap<>();

			
			return null;
		} else
			return null;
	}
   
   
    @Override
       public void wsSearchNumberToBuy(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String language) throws Exception{
        
		Webservice wsBeanNiceNumber = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_SEARCH_NUMBER);
		if (wsBeanNiceNumber == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
                        logger.error("The Webserive can't be found for this ");
                        bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
			return;
		}
		//params = new LinkedHashMap<>();
                 WebServiceUtil wsUtil = new WebServiceUtil(wsBeanNiceNumber, environment);
                        
                        
		SoapWSResponse objNiceBumber = wsUtil.callWebService(params, false);                
                
		if (objNiceBumber == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
			bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
		//	return;
		} else {
			try {
                            if(Constants.ERROR_CODE_0.equals(objNiceBumber.getErrCode())){
				NiceNumberBean niceNumberBean = null;
				Object dataNiceNumber = null;
					String xmlStringNiceNumberBean = objNiceBumber.getResponse();
                                        
                                        if(!StringUtils.isEmpty(xmlStringNiceNumberBean)){
					logger.info("wsSearchNumberToBuy xmlString:==================> " + xmlStringNiceNumberBean);
                                        Document doc = Jsoup.parse(xmlStringNiceNumberBean, "", Parser.xmlParser());
                                        String resultNiceNumber = "";
                                        for (Element e : doc.select(RETURN_TAG)) {
                                            resultNiceNumber = resultNiceNumber + e.toString();
                                        }
                                     //   resultProductOffer = resultProductOffer + "</result>";
                                        JSONObject data = XML.toJSONObject(resultNiceNumber);
                                      
                                        dataNiceNumber = data.getJSONObject(RETURN_TAG).get(RESULT_TAG);
                                        logger.info("dataObject" + dataNiceNumber);
                                        
                                        
                                         if (dataNiceNumber instanceof JSONArray) {
                                             
                                        niceNumberBean = new NiceNumberBean();
                                          JSONArray objIsdn = (JSONArray) dataNiceNumber;
                                          List<NiceNumber> lstNiceNumber = new ArrayList<>();
                                          for (Object objNiceNumber :  objIsdn ){
                                             JSONObject product = (JSONObject) objNiceNumber;
                                             NiceNumber isdn= new NiceNumber();
                                             isdn.setIsdn(product.optString(ISDN));
                                             isdn.setPrice(product.optString(PRICE));
                                             isdn.setStatus(product.optString(STATUS));
                                             isdn.setType(product.optInt(TYPE));
                                             isdn.setTypeIsdn(product.getInt(TYPE_ISDN));
                                             lstNiceNumber.add(isdn);
                                          }
                                          niceNumberBean.setLstNiceNumber(lstNiceNumber);
                                         logger.info("getLstNiceNumber Array:" + niceNumberBean.getLstNiceNumber().size());
                                        
                                      }else {
                                          JSONObject objIsdn = (JSONObject) dataNiceNumber;
                                             niceNumberBean = new NiceNumberBean();
                                          
                                           List<NiceNumber> lstNiceNumber = new ArrayList<>();
                                            NiceNumber isdn= new NiceNumber();
                                             isdn.setIsdn(objIsdn.optString(ISDN));
                                             isdn.setPrice(objIsdn.optString(PRICE));
                                             isdn.setStatus(objIsdn.optString(STATUS));
                                             isdn.setType(objIsdn.optInt(TYPE));
                                             isdn.setTypeIsdn(objIsdn.getInt(TYPE_ISDN));
                                             lstNiceNumber.add(isdn);
                                            niceNumberBean.setLstNiceNumber(lstNiceNumber);
                                            logger.info("getLstNiceNumber Object:" + niceNumberBean.getLstNiceNumber().size());
                                      }
					bean.setErrorCode(Constants.ERROR_CODE_0);
					bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_SUCCESS + language));
				} else {
					bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
					bean.setErrorCode(Constants.ERROR_CODE_1);
				}
				bean.setWsResponse(niceNumberBean);
                            }else{
                                bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
					bean.setErrorCode(Constants.ERROR_CODE_1);
                            
                        }
                            
			} catch (JsonSyntaxException  e) {
				logger.error("parse description error: ", e);
				bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
				bean.setErrorCode(Constants.ERROR_CODE_1);
			}
		}           
       }
       
       
        @Override
       public void wsDoLoginByGetCode(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String language) throws Exception{
        
            
		Webservice wsBeanGetCode = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_GET_CODE);
		if (wsBeanGetCode == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
                        logger.error("The Webserive can't be found for this ");
                        bean.setUserMsg(messageUtil.getMessage(WS_DO_LOGIN_BY_GET_CODE_FAIL + language));
			return;
		}                
		//params = new LinkedHashMap<>();
                String isdn = params.get("isdn").toString();
                if (StringUtils.isEmpty(isdn)) {
                    logger.error("isdn invalid");
                    bean.setUserMsg(messageUtil.getMessage(WS_DO_LOGIN_BY_GET_CODE_FAIL + language));
                    bean.setErrorCode(Constants.ERROR_CODE_1);
                    return ;
                }
                WebServiceUtil wsUtil = new WebServiceUtil(wsBeanGetCode, environment);                        
                        
		SoapWSResponse objGetCode = wsUtil.callWebService(params, false);                
                
                
		if (objGetCode == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
			bean.setUserMsg(messageUtil.getMessage(WS_DO_LOGIN_BY_GET_CODE_FAIL + language));
		//	return;
		} else {
			
                            String token = objGetCode.getTextContent(wsBeanGetCode.getXpathExtension02());
                            if (token != null) {
                                bean.setErrorCode(Constants.ERROR_CODE_0);
                                bean.setUserMsg(messageUtil.getMessage(WS_DO_LOGIN_BY_GET_CODE_SUCCESS + language));
                                bean.setMessage(token);
                                bean.setWsResponse(token);
                            } else {
                                bean.setErrorCode(Constants.ERROR_CODE_1);
                                bean.setUserMsg(messageUtil.getMessage(WS_DO_LOGIN_BY_GET_CODE_FAIL + language));
                                bean.setMessage(objGetCode.getTextContent(wsBeanGetCode.getXpathExtension01()));
                            }                                                             			
		}           
       }
       
       @Override
       public void wsVerifyByCode(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String language) throws Exception{
        
		Webservice wsBeanVerify = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_VERIFY_CODE);
		if (wsBeanVerify == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
                        logger.error("The Webserive can't be found for this ");
                        bean.setUserMsg(messageUtil.getMessage(WS_VERIFY_BY_CODE_FAIL + language));
			return;
		}
		    String isdn =  params.get("isdn").toString();
		    String code =  params.get("code").toString();
		    String token =  params.get("token").toString();
                      
                if (StringUtils.isEmpty(isdn) || StringUtils.isEmpty(code) || StringUtils.isEmpty(token)) {
                    logger.error("wsVerifyByCode params invalid");
                    bean.setUserMsg(messageUtil.getMessage(WS_VERIFY_BY_CODE_FAIL + language));
                    bean.setErrorCode(Constants.ERROR_CODE_1);
                    return ;
                }
                
                
                WebServiceUtil wsUtil = new WebServiceUtil(wsBeanVerify, environment);
                        
		SoapWSResponse obVerifyCode = wsUtil.callWebService(params, false);                
                
		if (obVerifyCode == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
			bean.setUserMsg(messageUtil.getMessage(WS_VERIFY_BY_CODE_FAIL + language));
		//	return;
		} else {
			try {
                              if(Constants.ERROR_CODE_0.equals(obVerifyCode.getErrCode())){
                                        bean.setErrorCode(Constants.ERROR_CODE_0);
                                        bean.setUserMsg(messageUtil.getMessage(WS_VERIFY_BY_CODE_SUCCESS + language));
                                       
                                    } else {
                                        bean.setErrorCode(Constants.ERROR_CODE_1);
                                        bean.setUserMsg(messageUtil.getMessage(WS_VERIFY_BY_CODE_FAIL + language));
                                        bean.setMessage(obVerifyCode.getTextContent(wsBeanVerify.getXpathExtension01()));
                              }       
                                
			} catch (JsonSyntaxException  e) {
				logger.error("parse description error: ", e);
				bean.setUserMsg(messageUtil.getMessage(WS_VERIFY_BY_CODE_FAIL + language));
				bean.setErrorCode(Constants.ERROR_CODE_1);
			}		           
       }
       }
       
        @Override
       public void wsGetProfileByIsdn(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String language) throws Exception{
        
		Webservice wsProfileByIsdn = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_GET_PROFILE_BY_ISDN);
		if (wsProfileByIsdn == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
                        logger.error("The Webserive can't be found for this ");
                        bean.setUserMsg(messageUtil.getMessage(WS_GET_PROFILE_BY_ISDN_FAIL + language));
			return;
		}
		//params = new LinkedHashMap<>();
                
                 String isdn =  params.get("isdn").toString();		
		 String token =  params.get("token").toString();
                      
                 if (StringUtils.isEmpty(isdn) || StringUtils.isEmpty(token)) {
                    logger.error("wsGetProfileByIsdn params invalid");
                    bean.setUserMsg(messageUtil.getMessage(WS_GET_PROFILE_BY_ISDN_FAIL + language));
                    bean.setErrorCode(Constants.ERROR_CODE_1);
                    return ;
                }
                 
                WebServiceUtil wsUtil = new WebServiceUtil(wsProfileByIsdn, environment);
                                                
		SoapWSResponse objProfileByIsdn = wsUtil.callWebService(params, false);                
                
		if (objProfileByIsdn == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
			bean.setUserMsg(messageUtil.getMessage(WS_GET_PROFILE_BY_ISDN_FAIL + language));
		//	return;
		} else {
			try {
                        if (Constants.ERROR_CODE_0.equals(objProfileByIsdn.getErrCode())) {

                            String xmlString = objProfileByIsdn.getResponse();
                            if (!StringUtils.isEmpty(xmlString)) {

                                logger.info("wsGetProfileByIsdn xmlString:==================> " + xmlString);
                                Document doc = Jsoup.parse(xmlString, "", Parser.xmlParser());
                                String resultProfileByIsdn = "";
                                for (Element e : doc.select(RETURN_TAG)) {
                                    resultProfileByIsdn = resultProfileByIsdn + e.toString();
                                }
                              
                                JSONObject data = XML.toJSONObject(resultProfileByIsdn);
                                String strData = data.toString().replaceAll("\"","\"");
                                bean.setWsResponse(strData);

                                bean.setErrorCode(Constants.ERROR_CODE_0);
                                bean.setUserMsg(messageUtil.getMessage(WS_GET_PROFILE_BY_ISDN_SUCCESS + language));
                            } else {
                                bean.setUserMsg(messageUtil.getMessage(WS_GET_PROFILE_BY_ISDN_FAIL + language));
                                bean.setErrorCode(Constants.ERROR_CODE_1);
                                bean.setMessage(objProfileByIsdn.getTextContent(wsProfileByIsdn.getXpathExtension01()));
                            }
                        }
                        else {
                                bean.setUserMsg(messageUtil.getMessage(WS_GET_PROFILE_BY_ISDN_FAIL + language));
                                bean.setErrorCode(Constants.ERROR_CODE_1);
                                bean.setMessage(objProfileByIsdn.getTextContent(wsProfileByIsdn.getXpathExtension01()));
                            }
                        //bean.setWsResponse(niceNumberBean);

                    } catch (JsonSyntaxException e) {
                        logger.error("parse description error: ", e);
                        bean.setUserMsg(messageUtil.getMessage(WS_GET_PROFILE_BY_ISDN_FAIL + language));
                        bean.setErrorCode(Constants.ERROR_CODE_1);
                    }
		}           
       }
       
       @Override
       public void wsUpdateInfoByUser(String xmlParam, LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String language) throws Exception {
        
		Webservice wsUpdateCus = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_UPDATE_INFO_BY_USER);
		if (wsUpdateCus == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
                        logger.error("The Webserive can't be found for this ");
                        bean.setUserMsg(messageUtil.getMessage(WS_UPDATE_INFO_BY_USER_FAIL + language));
			return;
		}
		
                WebServiceUtil wsUtil = new WebServiceUtil(wsUpdateCus, environment);
                params = new LinkedHashMap<>();
		params.put("customerInfo", xmlParam); 
                        
		SoapWSResponse objUpdateCusInfo = wsUtil.callWebService(params, false);           
                
		if (objUpdateCusInfo == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
			bean.setUserMsg(messageUtil.getMessage(WS_UPDATE_INFO_BY_USER_FAIL + language));
		//	return;
		} else {
			try {
				 if(Constants.ERROR_CODE_0.equals(objUpdateCusInfo.getErrCode())){
                                      logger.info("Update Customer Sucess!");
                                      bean.setErrorCode(Constants.ERROR_CODE_0);
                                      bean.setUserMsg(messageUtil.getMessage(WS_UPDATE_INFO_BY_USER_SUCCESS + language));
                                       
                                    } else {
                                       logger.info("Update Customer Error!");
                                        bean.setErrorCode(Constants.ERROR_CODE_1);
                                        bean.setUserMsg(messageUtil.getMessage(WS_UPDATE_INFO_BY_USER_FAIL + language));
                                        bean.setMessage(objUpdateCusInfo.getTextContent(wsUpdateCus.getXpathExtension01()));
                                 }

			} catch (JsonSyntaxException  e) {
				logger.error("parse description error: ", e);
				bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
				bean.setErrorCode(Constants.ERROR_CODE_1);
			}
		}           
       }
       
       
    @Override
    public void wsReserveNumber(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String language) throws Exception {

        Webservice wsBeanReserveNumber = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_RESERVE_NUMBER);
        if (wsBeanReserveNumber == null) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            logger.error("The Webserive " + Constants.WEBSERVICE_RESERVE_NUMBER + " can't be found for this ");

            bean.setUserMsg(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
            return;
        }

        WebServiceUtil wsUtil = new WebServiceUtil(wsBeanReserveNumber, environment);
        String isdnFrm = params.get("isdnFrm").toString();
        String customerIsdn = params.get("customerIsdn").toString();
        if (StringUtils.isEmpty(isdnFrm) || StringUtils.isEmpty(customerIsdn)) {
            logger.error("wsReserveNumber params invalid:" + "isdnFrm" + isdnFrm + "customerIsdn" + customerIsdn);

            bean.setUserMsg(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
            bean.setErrorCode(Constants.ERROR_CODE_1);
            return;
        }
        params.put("isdnFrm", isdnFrm);
        params.put("customerIsdn", customerIsdn);
        SoapWSResponse wsResponseRv = wsUtil.callWebService(params, true);

        // parser result
        if (wsResponseRv != null) {
            try {

                String error = wsResponseRv.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = wsResponseRv.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    try {
                        SoapWSResponse response = new SoapWSResponse(respStr);
                        String respCode = response.getTextContent("/Envelope/Body/lockIsdnByCusResponse/return/errorCode");

                        if (respCode != null && Constants.ERROR_CODE_0.equalsIgnoreCase(respCode)) {
                            String pinCode = response.getTextContent("/Envelope/Body/lockIsdnByCusResponse/return/pincode");
                            bean.setMessage(pinCode);
                            bean.setUserMsg(messageUtil.getMessage(WS_RESERVE_NUMBER_SUCCESS + language));
                            bean.setWsResponse(pinCode);
                            bean.setErrorCode(Constants.ERROR_CODE_0);
                        } else {
                            bean.setErrorCode(Constants.ERROR_CODE_1);
                            bean.setMessage(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
                            bean.setUserMsg(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
                        }
                    } catch (Exception e) {
                        bean.setErrorCode(Constants.ERROR_CODE_1);
                        bean.setMessage(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
                        bean.setUserMsg(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
                        logger.error("error", e);
                    }
                } else {
                    bean.setErrorCode(Constants.ERROR_CODE_1);
                    bean.setMessage(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
                    bean.setUserMsg(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
                    return;
                }
            } catch (XPathExpressionException xe) {

                logger.error("XPathExpressionException", xe);
            }
        } else {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
            bean.setUserMsg(messageUtil.getMessage(WS_RESERVE_NUMBER_FAIL + language));
        }
    }
    
    public void wsGetNumberToBuy(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String language) throws Exception{
        
		Webservice wsBeanNiceNumber = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_SEARCH_NUMBER);
		if (wsBeanNiceNumber == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
                        logger.error("The Webserive can't be found for this ");
                        bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
			return;
		}
		//params = new LinkedHashMap<>();
                 WebServiceUtil wsUtil = new WebServiceUtil(wsBeanNiceNumber, environment);
                        
                        
		SoapWSResponse objNiceBumber = wsUtil.callWebService(params, false);                
                
		if (objNiceBumber == null) {
			bean.setErrorCode(Constants.ERROR_CODE_1);
			bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
		//	return;
		} else {
			try {
                            if(Constants.ERROR_CODE_0.equals(objNiceBumber.getErrCode())){
				NiceNumberBean niceNumberBean = null;
				Object dataNiceNumber = null;
					String xmlStringNiceNumberBean = objNiceBumber.getResponse();
                                        
                                        if(!StringUtils.isEmpty(xmlStringNiceNumberBean)){
					logger.info("wsSearchNumberToBuy xmlString:==================> " + xmlStringNiceNumberBean);
                                        Document doc = Jsoup.parse(xmlStringNiceNumberBean, "", Parser.xmlParser());
                                        String resultNiceNumber = "";
                                        for (Element e : doc.select(RETURN_TAG)) {
                                            resultNiceNumber = resultNiceNumber + e.toString();
                                        }
                                     //   resultProductOffer = resultProductOffer + "</result>";
                                        JSONObject data = XML.toJSONObject(resultNiceNumber);
                                      
                                        dataNiceNumber = data.getJSONObject(RETURN_TAG).get(RESULT_TAG);
                                        logger.info("dataObject" + dataNiceNumber);
                                        
                                        
                                         if (dataNiceNumber instanceof JSONArray) {
                                             
                                        niceNumberBean = new NiceNumberBean();
                                          JSONArray objIsdn = (JSONArray) dataNiceNumber;
                                          List<NiceNumber> lstNiceNumber = new ArrayList<>();
                                          for (Object objNiceNumber :  objIsdn ){
                                             JSONObject product = (JSONObject) objNiceNumber;
                                             NiceNumber isdn= new NiceNumber();
                                             isdn.setIsdn(product.optString(ISDN));
                                             isdn.setPrice(product.optString(PRICE));
                                             isdn.setStatus(product.optString(STATUS));
                                             isdn.setType(product.optInt(TYPE));
                                             isdn.setTypeIsdn(product.getInt(TYPE_ISDN));
                                             lstNiceNumber.add(isdn);
                                          }
                                          niceNumberBean.setLstNiceNumber(lstNiceNumber);
                                         logger.info("getLstNiceNumber Array:" + niceNumberBean.getLstNiceNumber().size());
                                        
                                      }else {
                                          JSONObject objIsdn = (JSONObject) dataNiceNumber;
                                             niceNumberBean = new NiceNumberBean();
                                          
                                           List<NiceNumber> lstNiceNumber = new ArrayList<>();
                                            NiceNumber isdn= new NiceNumber();
                                             isdn.setIsdn(objIsdn.optString(ISDN));
                                             isdn.setPrice(objIsdn.optString(PRICE));
                                             isdn.setStatus(objIsdn.optString(STATUS));
                                             isdn.setType(objIsdn.optInt(TYPE));
                                             isdn.setTypeIsdn(objIsdn.getInt(TYPE_ISDN));
                                             lstNiceNumber.add(isdn);
                                            niceNumberBean.setLstNiceNumber(lstNiceNumber);
                                            logger.info("getLstNiceNumber Object:" + niceNumberBean.getLstNiceNumber().size());
                                      }
					bean.setErrorCode(Constants.ERROR_CODE_0);
					bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_SUCCESS + language));
				} else {
					bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
					bean.setErrorCode(Constants.ERROR_CODE_1);
				}
				bean.setWsResponse(niceNumberBean);
                            }else{
                                bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
					bean.setErrorCode(Constants.ERROR_CODE_1);
                            
                        }
                            
			} catch (JsonSyntaxException  e) {
				logger.error("parse description error: ", e);
				bean.setUserMsg(messageUtil.getMessage(WS_SEARCH_ISDN_FAIL + language));
				bean.setErrorCode(Constants.ERROR_CODE_1);
			}
		}           
       }
             
}
