package co.siten.myvtg.model.myvtg;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "SERVICE_GROUP")
@NamedQuery(name = "ServiceGroupA.findAll", query = "SELECT s FROM ServiceGroupA s")
public class ServiceGroupA implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	private String code;

	@Column(name = "CREATED_BY")
	private String createdBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "\"LANGUAGE\"")
	private String language;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	private String name;

	@Column(name = "SHORT_DES")
	private String shortDes;

        @Column(name = "SERVICE_GROUP_TYPE")
	private Integer serviceGroupType;
	
	@Column(name = "SERVICE_GROUP_ORDER")
	private Integer serviceGroupOrder;

	@Column(name = "NAME_KH",nullable = false, length = 100)
	private String nameKh;
	@Column(name = "SHORT_DES_KH", length = 200)
	private String shortDesKh;
	@Column(name = "ICON")
	private String icon;

	public String getNameKh() {
		return nameKh;
	}

	public void setNameKh(String nameKh) {
		this.nameKh = nameKh;
	}

	public String getShortDesKh() {
		return shortDesKh;
	}

	public void setShortDesKh(String shortDesKh) {
		this.shortDesKh = shortDesKh;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "COLOR")
	private String color;
        
        
	private Integer status;

	public ServiceGroupA() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getLanguage() {
		return language;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public String getName() {
		return name;
	}

	public String getShortDes() {
		return shortDes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

        public Integer getServiceGroupType() {
            return serviceGroupType;
        }

        public void setServiceGroupType(Integer serviceGroupType) {
            this.serviceGroupType = serviceGroupType;
        }

        public Integer getServiceGroupOrder() {
            return serviceGroupOrder;
        }

        public void setServiceGroupOrder(Integer serviceGroupOrder) {
            this.serviceGroupOrder = serviceGroupOrder;
        }
        
        

}