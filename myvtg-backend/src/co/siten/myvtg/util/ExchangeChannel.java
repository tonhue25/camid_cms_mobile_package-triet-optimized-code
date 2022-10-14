
package co.siten.myvtg.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.viettel.common.ObjectClientChannel;
import com.viettel.common.ViettelMsg;

/**
 *
 * @author ThoMC
 * @since
 * @version 1.0
 */
public class ExchangeChannel {

	private String host;
	private int port;
	private String user;
	private String pass;
	private ObjectClientChannel channel;
	private int id;

	public ExchangeChannel(ObjectClientChannel channel, long receiverTimeOut, int id) {
		this.channel = channel;
		this.channel.setReceiverTimeout(receiverTimeOut);
		this.id = id;
		this.host = channel.getIp();
		this.port = channel.getPort();
		this.user = channel.getUsername();
		this.pass = channel.getPassword();
	}

	public String getInfor() {
		return host + ":" + port;
	}

	public String getFullInfo() {
		return host + ":" + port + "/" + user + "@" + pass;
	}

	public ViettelMsg send(ViettelMsg request) throws IOException {
		return channel.send(request);
	}
}
