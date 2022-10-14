package co.siten.myvtg.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.ExchangeChannel;
import co.siten.myvtg.util.ExchangeClientChannel;

@org.springframework.stereotype.Service("ProService")
@PropertySource(value = { "classpath:provisioning.properties" })
public class ProService {
	private static final Logger logger = Logger.getLogger(ProService.class);
	@Autowired
	private Environment environment;

	@Cacheable("prochannel")
	public ExchangeChannel getChannel(String exchangeOrPro) throws Exception {
		ExchangeClientChannel client = new ExchangeClientChannel(environment, exchangeOrPro);
		return client.getChannel();
	}

	@Cacheable("prochannel")
	public ExchangeChannel getChannel() throws Exception {
		ExchangeClientChannel client = new ExchangeClientChannel(environment, Constants.PRO);
		return client.getChannel();
	}
}
