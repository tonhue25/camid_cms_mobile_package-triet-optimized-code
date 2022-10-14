package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SERVER database table.
 * 
 */
@Entity
@NamedQuery(name="Server.findAll", query="SELECT s FROM Server s")
public class Server implements Serializable {
	private static final long serialVersionUID = 1L;

	private String remote;

	@Column(name="SERVER_CONFIG")
	private String serverConfig;

	@Column(name="SERVER_ID")
	private BigDecimal serverId;

	public Server() {
	}

	public String getRemote() {
		return this.remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public String getServerConfig() {
		return this.serverConfig;
	}

	public void setServerConfig(String serverConfig) {
		this.serverConfig = serverConfig;
	}

	public BigDecimal getServerId() {
		return this.serverId;
	}

	public void setServerId(BigDecimal serverId) {
		this.serverId = serverId;
	}

}