/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import co.siten.myvtg.dao.SubDao;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * BCCSWebserviceUtils
 *
 * @author partner7
 */
public class BCCSWebserviceUtils {

    public BCCSWebserviceUtils() {
    }

    public String callBCCSWebservice(String url, JSONObject json, String wsCode, String... list) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(90000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(url);
        try {
            String xml = prepareXmlForRequest(XML.toString(json), wsCode, list);
            //If convert to xml with < or > => replaced by this symbol
            if (xml.contains("&lt;") && xml.contains("&gt;")) {
                xml = xml.replace("&lt;", "<");
                xml = xml.replace("&gt;", ">");
            }
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                return "";
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            return contentReturn;
        } catch (IOException | UnsupportedCharsetException | ParseException | JSONException e) {
            return "";
        }
    }

    public String buildImageList(String imageData, String imageName) {
        String request = "<lstImage><data>" + imageData + "</data><name>" + imageName + "</name></lstImage>";
        return request;
    }

    public String formatDateFromPlashToDot(String plashSring) throws java.text.ParseException {
        if (StringUtils.isEmpty(plashSring)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse(plashSring);
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        String dateDot = sdf.format(date);
        return dateDot;
    }

    private String prepareXmlForRequest(String paramRequests, String wsCode, String... list) {
        StringBuilder request = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.bccs.viettel.com/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<" + wsCode + ">"
                + paramRequests);
        if (list.length > 0) {
            for (String ele : list) {
                request.append(ele);
            }
        }
        request.append("</").append(wsCode).append("></soapenv:Body></soapenv:Envelope>");
        return request.toString();
    }

        public static void main(String[] agres){
        String a = new SubDao().refactorTimeRedundant("");
        System.out.print(a);
    }
}
