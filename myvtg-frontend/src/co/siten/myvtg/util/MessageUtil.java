package co.siten.myvtg.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 * @author thomc
 *
 */
@Component
@PropertySource(value = { "classpath:language.properties" })
public class MessageUtil {
	private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);
	@Autowired
	private Environment environment;

	public String getMessage(String key) {
		try {
			return environment.getProperty(key, key);
		} catch (Exception e) {
			logger.error("", e);
			return key;
		}

	}

}
