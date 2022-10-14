/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;



/**
 *
 * @author daibq
 */
public class EmoneyResponse {
    private int httpStatus;
    
    private String jsonResponse;
    
//    public <T> T getResponseObject(Class<T> clazz) {
//        if (jsonResponse == null || "".equals(jsonResponse.trim())) {
//            return null;
//        }
//        return new Gson().fromJson(jsonResponse, clazz);
//    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
    
    
}
