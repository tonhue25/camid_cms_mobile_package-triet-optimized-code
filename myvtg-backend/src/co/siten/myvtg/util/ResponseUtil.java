/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.dto.ResponseDto;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author buiquangdai
 */
@Component
@PropertySource(value = {"classpath:language.properties"})
public class ResponseUtil {

    @Autowired
    private Environment environment;

    /**
     * responseBean
     *
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @return BaseResponseBean
     */
    public BaseResponseBean responseBean(String errorCode, String description, String content, String language) {
        return new BaseResponseBean(errorCode, environment.getProperty(description + language), environment.getProperty(content + language));
    }


    public BaseResponseBean responseBean(String errorCode, String description, String content, String language, Object wsResponse) {
        return new BaseResponseBean(errorCode, environment.getProperty(description + language), environment.getProperty(content + language), wsResponse);
    }

    /**
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @param obj
     * @param obj1
     * @param obj2
     * @param obj3
     * @return
     */
    public BaseResponseBean responseBean(String errorCode, String description, String content, String language, String obj, String obj1, String obj2, String obj3) {
        return new BaseResponseBean(errorCode, environment.getProperty(description + language), getMessage(content + language, obj, obj1, obj2, obj3));
    }


    public BaseResponseBean responseBean(String errorCode, String description, String content) {
        return new BaseResponseBean(errorCode, description, content);
    }

    /**
     * @param errorCode
     * @param description
     * @param content
     * @param lang
     * @param obj
     * @param obj1
     * @param obj2
     * @param obj3
     * @param check
     * @return
     */
    public BaseResponseBean responseBean(String errorCode, String description, String content, String lang, String obj, String obj1, String obj2, String obj3, String check) {
        return new BaseResponseBean(errorCode, description, getMessage(content + lang, obj, obj1, obj2, obj3));
    }

    public BaseResponseBean responseBeanCompact(String errorCode, String message, String language) {
        return new BaseResponseBean(errorCode, environment.getProperty(message + language));
    }

    /**
     * response
     *
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @return ResponseDto
     */
    public ResponseDto response(String errorCode, String description, String content, String language) {
        return new ResponseDto(errorCode, environment.getProperty(description + language), environment.getProperty(content + language));
    }

    /**
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @param obj
     * @param obj1
     * @param obj2
     * @param obj3
     * @return ResponseDto
     */
    public ResponseDto response(String errorCode, String description, String content, String language, String obj, String obj1, String obj2, String obj3) {
        return new ResponseDto(errorCode, environment.getProperty(description + language), getMessage(content + language, obj, obj1, obj2, obj3));
    }

    /**
     * response
     *
     * @param errorCode
     * @param description
     * @param content
     * @return ResponseDto
     */
    public ResponseDto response(String errorCode, String description, String content) {
        return new ResponseDto(errorCode, description, content);
    }

    /**
     * @param key
     * @param obj
     * @param obj1
     * @param obj2
     * @param obj3
     * @return
     * @author daibq
     */
    public String getMessage(String key, String obj, String obj1, String obj2, String obj3) {
        String msg = "";
        if (!DataUtil.isNullOrEmpty(obj)
                && !DataUtil.isNullOrEmpty(obj1)
                && !DataUtil.isNullOrEmpty(obj2)
                && !DataUtil.isNullOrEmpty(obj3)) {
            msg = String.format(Locale.getDefault(), environment.getProperty(key), obj, obj1, obj2, obj3);
        } else if (!DataUtil.isNullOrEmpty(obj)
                && !DataUtil.isNullOrEmpty(obj1)
                && !DataUtil.isNullOrEmpty(obj2)) {
            msg = String.format(Locale.getDefault(), environment.getProperty(key), obj, obj1, obj2);
        } else if (!DataUtil.isNullOrEmpty(obj)
                && !DataUtil.isNullOrEmpty(obj1)) {
            msg = String.format(Locale.getDefault(), environment.getProperty(key), obj, obj1);
        } else if (!DataUtil.isNullOrEmpty(obj)) {
            msg = String.format(Locale.getDefault(), environment.getProperty(key), obj);
        } else {
            msg = environment.getProperty(key);
        }
        return msg;
    }

    /**
     * getMessage
     *
     * @param key
     * @return
     */
    public String getMessage(String key) {
        return environment.getProperty(key);
    }

    /**
     * getMessage
     *
     * @param key
     * @param defaultStr
     * @return
     */
    public String getMessage(String key, String defaultStr) {
        return environment.getProperty(key, defaultStr);
    }

}
