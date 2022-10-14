package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UD_CHK_TONFILE database table.
 * 
 */
@Entity
@Table(name="UD_CHK_TONFILE")
@NamedQuery(name="UdChkTonfile.findAll", query="SELECT u FROM UdChkTonfile u")
public class UdChkTonfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="THREAD_NAME")
	private String threadName;

	@Column(name="THREAD_TYPE")
	private String threadType;

	public UdChkTonfile() {
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getThreadName() {
		return this.threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadType() {
		return this.threadType;
	}

	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}

}