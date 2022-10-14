/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package co.siten.myvtg.util;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.viettel.common.ObjectClientChannel;
import com.viettel.common.ViettelMsg;

/**
 *
 * @author Thomc
 * @since
 * @version 1.0
 */
public class ExchangeClientChannel {
	private static Logger logger = Logger.getLogger(ExchangeClientChannel.class);
	private static String loggerLabel = "ExchangeClientChannel: ";
	private HashMap listExch;
	private int exchId;
	private int exchCount = 0;

	public ExchangeClientChannel(Environment env, String exchangeOrPro) throws Exception {
		logger.info(loggerLabel + "Init Exchange");

		String host;
		int port;
		String user;
		String pass;
		listExch = new HashMap();

		try {
			host = env.getRequiredProperty(exchangeOrPro + "_ip");
			port = Integer.parseInt(env.getRequiredProperty(exchangeOrPro + "_port"));
			user = env.getRequiredProperty(exchangeOrPro + "_user");
			pass = env.getRequiredProperty(exchangeOrPro + "_pass");
			exchId = 0;
			// init client

			ObjectClientChannel channel = new ObjectClientChannel(host, port, user, pass, true);
			ExchangeChannel exChannel = new ExchangeChannel(channel,
					Long.parseLong(env.getRequiredProperty("RECEIVER_TIME_OUT")), exchId);
			logger.info(loggerLabel + "Init Chanel: " + host + "-" + port + "-" + user + "-" + pass + "-" + " success");

			listExch.put(exchId, exChannel);
			exchId++;

			logger.info(loggerLabel + "Init Exchange: " + exChannel.getInfor() + " success");

			logger.info(loggerLabel + "Init All Exchange success");
		} catch (Exception ex) {
			logger.error("ERROR Init Exchange, please check exchange_client.cfg", ex);
			listExch.clear();
		}
	}

	public ExchangeChannel getChannel() {
		int moduleId = exchCount;

		if (exchCount == listExch.size() - 1) {
			exchCount = 0;
		} else {
			exchCount++;
		}

		return (ExchangeChannel) listExch.get(moduleId);
	}

	public ViettelMsg processRequest(Logger logger, ExchangeChannel channel, ViettelMsg request, Long commandTimeout) {
		ViettelMsg response = null;
		try {
			if (commandTimeout != null) {
				request.set("ClientTimeout", String.valueOf(commandTimeout));
			}
			response = channel.send(request);
		} catch (Exception ex) {
			logger.error(loggerLabel + "ERROR send request " + channel.getInfor(), ex);

		}
		return response;
	}

}
