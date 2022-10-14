package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PROCESS_MANAGER database table.
 * 
 */
@Entity
@Table(name="PROCESS_MANAGER")
@NamedQuery(name="ProcessManager.findAll", query="SELECT p FROM ProcessManager p")
public class ProcessManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PROCESS_MANAGER_ID")
	private long processManagerId;

	@Column(name="DELAY_TIME")
	private BigDecimal delayTime;

	@Column(name="IS_DELETE")
	private BigDecimal isDelete;

	@Column(name="MIN_USABLE_SPACE")
	private BigDecimal minUsableSpace;

	@Column(name="PROCESS_MANAGER_CODE")
	private String processManagerCode;

	@Column(name="PROCESS_MANAGER_NAME")
	private String processManagerName;

	public ProcessManager() {
	}

	public long getProcessManagerId() {
		return this.processManagerId;
	}

	public void setProcessManagerId(long processManagerId) {
		this.processManagerId = processManagerId;
	}

	public BigDecimal getDelayTime() {
		return this.delayTime;
	}

	public void setDelayTime(BigDecimal delayTime) {
		this.delayTime = delayTime;
	}

	public BigDecimal getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(BigDecimal isDelete) {
		this.isDelete = isDelete;
	}

	public BigDecimal getMinUsableSpace() {
		return this.minUsableSpace;
	}

	public void setMinUsableSpace(BigDecimal minUsableSpace) {
		this.minUsableSpace = minUsableSpace;
	}

	public String getProcessManagerCode() {
		return this.processManagerCode;
	}

	public void setProcessManagerCode(String processManagerCode) {
		this.processManagerCode = processManagerCode;
	}

	public String getProcessManagerName() {
		return this.processManagerName;
	}

	public void setProcessManagerName(String processManagerName) {
		this.processManagerName = processManagerName;
	}

}