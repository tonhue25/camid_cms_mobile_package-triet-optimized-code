package co.siten.myvtg.util;

import java.util.Date;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.viettel.common.ExchMsg;
import com.viettel.common.ViettelService;

import co.siten.myvtg.model.myvtg.TransactionLog;

public class CardUtil {
	private static final Logger logger = Logger.getLogger(CardUtil.class);
	private ExchangeChannel channel;

	public CardUtil(ExchangeChannel channel) {
		this.channel = channel;
	}

	public TransactionLog lockCard(String msisdn, String cardPin, Environment environment) {
		TransactionLog log = new TransactionLog();

		ViettelService request = new ViettelService();
		ViettelService response;
		try {
			request.setMessageType("1900");
			request.setProcessCode(environment.getProperty("recharge_lockcard"));
			request.set("MSISDN", msisdn);
			request.set("CARDPIN", cardPin);

			log.setMsisdn(msisdn);
			log.setChannel(channel.getFullInfo());
			log.setCommand(request.getCommand());
			log.setRequest(request.toString());
			log.setRequestTime(CommonUtil.getCurrentTime());
			long timeStart = System.currentTimeMillis();

			// send COMMAND, dung ham sendAll cua lib
			response = (ViettelService) channel.send(request);
			log.setResponseTime(CommonUtil.getCurrentTime());

			logger.info("Time process VC_CARDTOPUP: " + (System.currentTimeMillis() - timeStart) + " ms");

			if (AppUtil.checkResponse(response)) {
				String value = (String) response.get("CARDVALUE");
				if (value != null) {
					log.setResultValue(value);
					log.setMoney(Double.parseDouble(value));
					log.setErrorCode(Constants.ERROR_CODE_0);
				}
                                log.setSeriNumber((String) response.get("SERIAL"));
			} else {
				log.setErrorCode(Constants.ERROR_CODE_1);
			}

			if (response != null)
				log.setResponse(response.get("description").toString());

			logger.info("Thong tin gui sang Provisioning:\n" + request);
			logger.info("Thong tin tra ve tu Provisioning:\n" + response);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return log;
	}

	public TransactionLog chargeMoney(String msisdn, long value, Environment environment) {
		TransactionLog log = new TransactionLog();

		ViettelService request = new ViettelService();
		ViettelService response;
		try {
			request.setMessageType("1900");
			request.setProcessCode(environment.getProperty("recharge_chargemoney"));
			request.set("MSISDN", msisdn);
			request.set("VALUE", value);

			log.setMsisdn(msisdn);
			log.setChannel(channel.getFullInfo());
			log.setCommand(request.getCommand());
			log.setRequest(request.toString());
			log.setRequestTime(new Date());
			long timeStart = System.currentTimeMillis();

			// send COMMAND, dung ham sendAll cua lib
			response = (ViettelService) channel.send(request);

			log.setResponseTime(new Date());

			if (AppUtil.checkResponse(response)) {
				log.setErrorCode(Constants.ERROR_CODE_0);
			} else {
				log.setErrorCode(Constants.ERROR_CODE_1);
			}
			if (response != null) {
				log.setResponse(response.toString());
			}
			logger.info("Time process OCS_PAYMENT: " + (System.currentTimeMillis() - timeStart) + " ms");
		} catch (Exception ex) {
			logger.error("error", ex);
		}

		return log;
	}

	public TransactionLog viewCardExchange(String msisdn, String cardPin, Environment environment) {
		TransactionLog log = new TransactionLog();

		try {
			ExchMsg request = new ExchMsg();
			request.setSynchronous(true);
			request.setCommand(environment.getRequiredProperty("recharge_lockcard"));
			request.set(environment.getRequiredProperty("recharge_cardpin"), cardPin);
                        request.set(environment.getRequiredProperty("recharge_msisdn"), msisdn);
                        
			log.setMsisdn(msisdn);
			log.setChannel(channel.getFullInfo());
			log.setCommand(request.getCommand());
			log.setRequest(request.toString());
			log.setRequestTime(new Date());
			long timeStart = System.currentTimeMillis();
			ExchMsg response = (ExchMsg) channel.send(request);
			log.setResponseTime(new Date());
			logger.info("Time process IN_CARDTOPUP: " + (System.currentTimeMillis() - timeStart) + " ms");

			if (response != null) {
                                logger.info(response);
				log.setResponse(CommonUtil.convertObjectToJsonString(response));
				String err = response.getError();
				if (!CommonUtil.isEmpty(err) && "0".equalsIgnoreCase(err)) {
					log.setErrorCode(Constants.ERROR_CODE_0);
/*
					String delim = "|";
					String regex = "(?<!\\\\)" + Pattern.quote(delim);
					String original = response.getOriginal();

					String value = original.split("RESULT=")[1];

					String flag = value.split(regex)[0];
					String cardValue;
					if ("1".equalsIgnoreCase(flag)) {
						cardValue = value.split(regex)[1];
					} else {
						cardValue = value.split(regex)[4];
                                        
                                        return response.get("CARDVALUE").toString().length()<5 ?response.get("CARDVALUE2").toString():response.get("CARDVALUE").toString();
					}
*/		
                                        String cardValue = response.get(environment.getRequiredProperty("recharge_cardvalue")).toString().length() < 5? 
                                                response.get("CARDVALUE2").toString():response.get(environment.getRequiredProperty("recharge_cardvalue")).toString() ;
                                        String serial = response.get("SERIAL").toString();
                                        log.setSeriNumber(serial);
					log.setResultValue(cardValue);
					log.setMoney(Double.parseDouble(cardValue) );
					return log;
				} else {
					log.setErrorCode(Constants.ERROR_CODE_1);
				}
			} else {
				log.setResponse(null);
				log.setErrorCode(Constants.ERROR_CODE_1);
			}

		} catch (Exception ex) {
			logger.error("error", ex);
		}
		return log;
	}

	public TransactionLog chargeMoneyExchange(String msisdn, Double value, Environment environment) {
		TransactionLog log = new TransactionLog();

		try {
			ExchMsg request = new ExchMsg();
			request.setSynchronous(true);
			request.setCommand(environment.getRequiredProperty("recharge_chargemoney"));
			request.set("ISDN", msisdn);
			request.set(environment.getRequiredProperty("recharge_ammount"), Double.toString(value));

			log.setMsisdn(msisdn);
			log.setChannel(channel.getFullInfo());
			log.setCommand(request.getCommand());
			log.setRequest(request.toString());
			log.setRequestTime(new Date());
			long timeStart = System.currentTimeMillis();
			ExchMsg response = (ExchMsg) channel.send(request);
			log.setResponseTime(new Date());
			logger.info("Time process OCSHW_TOPUP_CARD: " + (System.currentTimeMillis() - timeStart) + " ms");

			if (response != null) {
				log.setResponse(CommonUtil.convertObjectToJsonString(response));
				String err = response.getError();
				if (!CommonUtil.isEmpty(err) && "0".equalsIgnoreCase(err)) {
					log.setErrorCode(Constants.ERROR_CODE_0);
					return log;
				} else {
					log.setErrorCode(Constants.ERROR_CODE_1);
				}
			} else {
				log.setResponse(null);
				log.setErrorCode(Constants.ERROR_CODE_1);
			}

		} catch (Exception ex) {
			logger.error("error", ex);
		}
		return log;
	}
        
        public TransactionLog viewCardExchangeVT(String msisdn, String cardPin, Environment environment) {
		TransactionLog log = new TransactionLog();

		try {
			ExchMsg request = new ExchMsg();
			request.setSynchronous(true);
			request.setCommand(environment.getRequiredProperty("recharge_lockcard_vt"));
			request.set(environment.getRequiredProperty("recharge_msisdn"), msisdn);
			request.set(environment.getRequiredProperty("recharge_cardpin_vt"), cardPin);

			log.setMsisdn(msisdn);
			log.setChannel(channel.getFullInfo());
			log.setCommand(request.getCommand());
			log.setRequest(request.toString());
			log.setRequestTime(new Date());
			long timeStart = System.currentTimeMillis();
			ExchMsg response = (ExchMsg) channel.send(request);
			log.setResponseTime(new Date());
			logger.info("Time process IN_CARDTOPUP: " + (System.currentTimeMillis() - timeStart) + " ms");

			if (response != null) {
				log.setResponse(CommonUtil.convertObjectToJsonString(response));
				String err = response.getError();
				if (!CommonUtil.isEmpty(err) && "0".equalsIgnoreCase(err)) {
					log.setErrorCode(Constants.ERROR_CODE_0);

			//		String delim = "|";
				//	String regex = "(?<!\\\\)" + Pattern.quote(delim);
			//		String original = response.getOriginal();

				//	String value = original.split("RESULT=")[1];

					String cardValue = response.get(environment.getRequiredProperty("recharge_cardvalue")).toString();
				//	String flag = value.split(regex)[0];
				//	String cardValue;
					/*
					if ("1".equalsIgnoreCase(flag)) {
						cardValue = value.split(regex)[1];
					} else {
						cardValue = value.split(regex)[4];
					}*/

					log.setResultValue(cardValue);
                                        log.setMoney(Double.parseDouble(cardValue));
					return log;
				} else {
					log.setErrorCode(Constants.ERROR_CODE_1);
				}
			} else {
				log.setResponse(null);
				log.setErrorCode(Constants.ERROR_CODE_1);
			}

		} catch (Exception ex) {
			logger.error("error", ex);
		}
		return log;
	}

	public TransactionLog chargeMoneyExchangeVT(String msisdn, Double value, Environment environment) {
		TransactionLog log = new TransactionLog();

		try {
			ExchMsg request = new ExchMsg();
			request.setSynchronous(true);
			request.setCommand(environment.getRequiredProperty("recharge_chargemoney_vt"));
			request.set(environment.getRequiredProperty("recharge_partycode"), "METFONE");
			request.set(environment.getRequiredProperty("recharge_payment_vt"), Double.toString(value));
			request.set(environment.getRequiredProperty("recharge_msisdn"), msisdn);

			log.setMsisdn(msisdn);
			log.setChannel(channel.getFullInfo());
			log.setCommand(request.getCommand());
			log.setRequest(request.toString());
			log.setRequestTime(new Date());
			long timeStart = System.currentTimeMillis();
			ExchMsg response = (ExchMsg) channel.send(request);
			log.setResponseTime(new Date());
			logger.info("Time process OCS_TOPUP_CARD: " + (System.currentTimeMillis() - timeStart) + " ms");

			if (response != null) {
				log.setResponse(CommonUtil.convertObjectToJsonString(response));
				String err = response.getError();
				if (!CommonUtil.isEmpty(err) && "0".equalsIgnoreCase(err)) {
					log.setErrorCode(Constants.ERROR_CODE_0);
					return log;
				} else {
					log.setErrorCode(Constants.ERROR_CODE_1);
				}
			} else {
				log.setResponse(null);
				log.setErrorCode(Constants.ERROR_CODE_1);
			}

		} catch (Exception ex) {
			logger.error("error", ex);
		}
		return log;
	}
}
