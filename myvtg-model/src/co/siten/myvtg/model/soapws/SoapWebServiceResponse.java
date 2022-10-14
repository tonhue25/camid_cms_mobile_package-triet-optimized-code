/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.soapws;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import co.siten.myvtg.dto.*;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * SoapWebServiceResponse
 *
 * @author phuonghc
 */
public class SoapWebServiceResponse {

    private String response;
    private Document document;
    private String errCode;
    private static final String EMPTY_STRING = "";

    public SoapWebServiceResponse(String response) throws ParserConfigurationException, SAXException, IOException {
        this.response = response;
        if (StringUtils.isNotEmpty(response)) {
            parserResponse(response);
        }
    }

    private void parserResponse(String response) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(response));
        this.document = builder.parse(is);
    }

    public String getTextContent(String xpath) throws XPathExpressionException {
        if (this.document == null) {
            return EMPTY_STRING;
        }

        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(xpath).evaluate(this.document, XPathConstants.NODESET);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return EMPTY_STRING;
    }

    public RegOnlineFtthInfoDTO getRegOnlineFtthInfo() throws XPathExpressionException {
        if (this.document == null) {
            return null;
        }
        RegOnlineFtthInfoDTO regOnlineFtthInfo = new RegOnlineFtthInfoDTO();
        NodeList nodeList = this.document.getElementsByTagName("regOnlineFtthInfo");
        if (nodeList != null && nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            if(element.getElementsByTagName("address").item(0) != null){
                regOnlineFtthInfo.setAddress(element.getElementsByTagName("address").item(0).getTextContent());
            }
            if(element.getElementsByTagName("customerMail").item(0) != null){
                regOnlineFtthInfo.setCustomerMail(element.getElementsByTagName("customerMail").item(0).getTextContent());
            }
            if(element.getElementsByTagName("customerPhone").item(0) != null){
                regOnlineFtthInfo.setCustomerPhone(element.getElementsByTagName("customerPhone").item(0).getTextContent());
            }
            if(element.getElementsByTagName("customerName").item(0) != null){
                regOnlineFtthInfo.setCustomerName(element.getElementsByTagName("customerName").item(0).getTextContent());
            }
            if(element.getElementsByTagName("id").item(0) != null){
                regOnlineFtthInfo.setId(element.getElementsByTagName("id").item(0).getTextContent());
            }
            if(element.getElementsByTagName("packageDetail").item(0) != null){
                getPackageDetail(element.getElementsByTagName("packageDetail").item(0).getTextContent(), regOnlineFtthInfo);
            }
            if(element.getElementsByTagName("province").item(0) != null){
                regOnlineFtthInfo.setProvince(element.getElementsByTagName("province").item(0).getTextContent());
            }
            if(element.getElementsByTagName("speed").item(0) != null){
                regOnlineFtthInfo.setSpeed(element.getElementsByTagName("speed").item(0).getTextContent());
            }
            if(element.getElementsByTagName("status").item(0) != null){
                regOnlineFtthInfo.setStatus(element.getElementsByTagName("status").item(0).getTextContent());
            }
            if(element.getElementsByTagName("color").item(0) != null){
                regOnlineFtthInfo.setColor(element.getElementsByTagName("color").item(0).getTextContent());
            }
            if(element.getElementsByTagName("createDateStr").item(0) != null){
                regOnlineFtthInfo.setCreateDateStr(element.getElementsByTagName("createDateStr").item(0).getTextContent().substring(0, 10));
            }
            if(element.getElementsByTagName("statusDes").item(0) != null){
                regOnlineFtthInfo.setStatusDes(element.getElementsByTagName("statusDes").item(0).getTextContent());
            }
            if(element.getElementsByTagName("reason").item(0) != null){
                regOnlineFtthInfo.setReason(element.getElementsByTagName("reason").item(0).getTextContent());
            }
            if(element.getElementsByTagName("assignShop").item(0) != null){
                regOnlineFtthInfo.setAssignShop(element.getElementsByTagName("assignShop").item(0).getTextContent());
            }
            if(element.getElementsByTagName("assignStaff").item(0) != null){
                regOnlineFtthInfo.setAssignStaff(element.getElementsByTagName("assignStaff").item(0).getTextContent());
            }
            if(element.getElementsByTagName("code").item(0) != null){
                regOnlineFtthInfo.setCode(element.getElementsByTagName("code").item(0).getTextContent());
            }
            if(element.getElementsByTagName("overtime").item(0) != null){
                regOnlineFtthInfo.setOvertime(element.getElementsByTagName("overtime").item(0).getTextContent());
            }
            if(element.getElementsByTagName("account").item(0) != null){
                regOnlineFtthInfo.setAccount(element.getElementsByTagName("account").item(0).getTextContent());
            }
            return regOnlineFtthInfo;
        }
        return null;
    }

    public List<RegOnlineHisDTO> getRegOnlineHis(){
        if (this.document == null) {
            return null;
        }
        List<RegOnlineHisDTO> lst = new ArrayList<>();
        NodeList nodeList = this.document.getElementsByTagName("regOnlHis");
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                RegOnlineHisDTO regOnlineHis = new RegOnlineHisDTO();
                Element element = (Element) nodeList.item(i);
                NodeList nodeCol = element.getElementsByTagName("column");
                if(nodeCol.item(0) != null) {
                    String col = nodeCol.item(0).getTextContent();
                    if ("STATUS".equalsIgnoreCase(col)) {
                        NodeList nodeNewValue = element.getElementsByTagName("newValue");
                        NodeList nodeOldValue = element.getElementsByTagName("oldValue");
                        NodeList nodeDate = element.getElementsByTagName("date");
                        NodeList nodeUser = element.getElementsByTagName("user");
                        regOnlineHis.setColumn(col);
                        if (nodeNewValue.item(0) != null) {
                            regOnlineHis.setNewValue(nodeNewValue.item(0).getTextContent());
                        }
                        if (nodeOldValue.item(0) != null) {
                            regOnlineHis.setOldValue(nodeOldValue.item(0).getTextContent());
                        }
                        if(nodeDate.item(0) != null) {
                            regOnlineHis.setUpdateDate(nodeDate.item(0).getTextContent());
                        }
                        if(nodeUser.item(0) != null) {
                            regOnlineHis.setUpdateUser(nodeUser.item(0).getTextContent());
                        }
                        lst.add(regOnlineHis);
                    } else {
                        continue;
                    }
                }
            }
            return lst;
        }
        return null;
    }

    // convert string to object
    private void getPackageDetail(String packageDetail,RegOnlineFtthInfoDTO regOnlineFtthInfo ){
        List<String> lst = Arrays.asList(packageDetail.split("newLine"));
        if(lst.size() == 6){
            regOnlineFtthInfo.setMonthFee(getValue(lst.get(1)));
            regOnlineFtthInfo.setPayAdvance(getValue(lst.get(2)));
            regOnlineFtthInfo.setInstalation(getValue(lst.get(3)));
            regOnlineFtthInfo.setDeposit(getValue(lst.get(4)));
            regOnlineFtthInfo.setModemWifi(getValue(lst.get(5)));
        }
    }

    private String getValue(String srt){
        String[]  arr = srt.split(":");
        return arr[1];
    }
    public List<ObjectDTO> getContentObject(String object, String objectCode, String objectName  ) {
        if (this.document == null) {
            return null;
        }
        List<ObjectDTO> objects = new ArrayList<>();
        NodeList nodes = this.document.getElementsByTagName(object);
        for (int i = 0; i < nodes.getLength(); i++) {
            ObjectDTO objectDTO = new ObjectDTO();
            Element element = (Element) nodes.item(i);
            NodeList nodeCode  = element.getElementsByTagName(objectCode);
            String code = nodeCode.item(0).getTextContent();
            NodeList nodeName  = element.getElementsByTagName(objectName);
            String name = nodeName.item(0).getTextContent();
            objectDTO.setName(name);
            objectDTO.setCode(code);
            objects.add(objectDTO);
        }
        return objects;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
