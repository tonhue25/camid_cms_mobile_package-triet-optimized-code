/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author LuatNC
 */
public class SoapWSResponse {
	private String response;
	private Document doc;
	private String errCode;

	public SoapWSResponse(String response) throws Exception {
		this.response = response;
		if (response != null && !response.isEmpty()) {
			parserResponse(response);
		}
	}

	private void parserResponse(String response) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(response));
		doc = builder.parse(is);
	}

	public String getTextContent(String xpath) throws XPathExpressionException {
		if (doc == null)
			return null;

		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.compile(xpath).evaluate(doc, XPathConstants.NODESET);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		return null;
	}

	public String getWSResponse() {
		return response;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        
}
