package co.siten.myvtg.service;

import co.siten.myvtg.dto.BccsGwResponse;
import co.siten.myvtg.dto.ResultResponse;
import co.siten.myvtg.util.Common;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@PropertySource(value = {"classpath:config.properties"})
public class ApiGwServiceGlobal {
    @Autowired
    private Environment environment;
    private static final Logger logger = Logger.getLogger(ApiGwServiceGlobal.class);

    public ApiGwServiceGlobal() {
    }


    public BccsGwResponse postRequest(String username) {
        logger.info("#ApiGwServiceGlobal - Start");
        BccsGwResponse baseResponse = new BccsGwResponse();
        try {
            String urlString = String.format("%s", environment.getProperty(Common.URL_API_GET_TOKEN));
            OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
                    .readTimeout(60000, TimeUnit.MILLISECONDS)
                    .build();
            Gson gson = new Gson();
            //create request
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("appCode", environment.getProperty(Common.APPCODE_GET_TOKEN));
            requestBody.put("device", environment.getProperty(Common.DEVICE_GET_TOKEN));
            requestBody.put("prefix", environment.getProperty(Common.PREFIX_GET_TOKEN));
            requestBody.put("username", username);
            String reqContent = gson.toJson(requestBody);

            logger.info("ApiGwServiceGlobal.request: " + reqContent);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, reqContent);
            Request result = new Request.Builder()
                    .url(urlString)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(result).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.code() == 200) {
                JsonNode jsonNode = objectMapper.readTree(response.body().string());
                logger.info("ApiGwServiceGlobal.response code: " + gson.toJson(response.body()));
                String sessionId = objectMapper.treeToValue(jsonNode.findPath("sessionId"), String.class);
                String token = objectMapper.treeToValue(jsonNode.findPath("token"), String.class);
                baseResponse.setSessionId(sessionId);
                baseResponse.setToken(token);
            }
        } catch (Exception e){
            logger.error("##ApiGwServiceGlobal.exception:", e);
        }
        return baseResponse;
    }

}
