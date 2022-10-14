package co.siten.myvtg.util;

import co.siten.myvtg.util.*;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author DAIBQ
 *
 */
@Component
@PropertySource(value = {"classpath:config.properties"})
public class ConfigUtils {

    @Autowired
    private Environment environment;

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
     * @param defaulValue
     * @return
     */
    public String getMessage(String key, String defaulValue) {
        return environment.getProperty(key, defaulValue);
    }

    /**
     * @author daibq
     * @param key
     * @param obj
     * @param obj1
     * @param obj2
     * @param obj3
     * @return
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
    
}
