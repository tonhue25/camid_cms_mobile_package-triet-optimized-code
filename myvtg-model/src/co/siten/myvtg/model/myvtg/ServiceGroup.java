package co.siten.myvtg.model.myvtg;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SERVICE_GROUP_BK_20221608")
@NamedQuery(name = "ServiceGroup.findAll", query = "SELECT s FROM ServiceGroup s")
public class ServiceGroup extends AbstractEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "SERVICE_GROUP_BK_20221608_SEQ", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "SHORT_DES", length = 200)
    private String shortDes;

    @Column(name = "NAME_KH",nullable = false, length = 100)
    private String nameKh;

    @Column(name = "SHORT_DES_KH", length = 200)
    private String shortDesKh;

    @Column(name = "SERVICE_GROUP_TYPE")
    private Integer serviceGroupType;

    @Column(name = "SERVICE_GROUP_ORDER")
    private Integer serviceGroupOrder;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "COLOR")
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

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
}
