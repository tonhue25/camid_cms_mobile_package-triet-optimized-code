package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SUB_SERVICE_BK_20221608")
@NamedQuery(name = "SubService.findAll", query = "SELECT s FROM SubService s")
public class SubService extends AbstractEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "SUB_SERVICE_BK_20221608_SEQ", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Column(name = "NAME",nullable = false, length = 200)
    private String name;

    @Column(name = "NAME_KH",nullable = false, length = 200)
    private String nameKh;

    @Column(name = "SERVICE_ID")
    private Long serviceId;

    @Column(name = "SHORT_DES",length = 800)
    private String shortDes;

    @Column(name = "FULL_DES",length = 4000)
    private String fullDes;

    @Column(name = "SHORT_DES_KH",length = 800)
    private String shortDesKh;

    @Column(name = "FULL_DES_KH",length = 4000)
    private String fullDesKh;

    @Column(name = "ICON_URL",length = 400)
    private String iconUrl;

    @Column(name = "TYPE",length = 20)
    private String type;

    @Column(name = "VOLUME")
    private Integer volume;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "CHANNEL_TYPE")
    private Integer channelType;

    @Column(name = "LANGUAGE",length = 20)
    private String language;

    @Column(name = "UNIT",length = 20)
    private String unit;

    @Column(name = "SUB_SERVICE_ORDER")
    private Integer subServiceOrder;

    @Column(name = "VALIDITY",length = 100)
    private String vaLiDiTy;

    @Column(name = "FORMAT_CODE",length = 4000)
    private String formatCode;

    @Column(name = "AUTO_RENEW", columnDefinition = "integer default 0")
    private Integer autoRenew;

    @Column(name = "RECOMMEND")
    private Integer recommend;

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

    public String getNameKh() {
        return nameKh;
    }

    public void setNameKh(String nameKh) {
        this.nameKh = nameKh;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getFullDes() {
        return fullDes;
    }

    public void setFullDes(String fullDes) {
        this.fullDes = fullDes;
    }

    public String getShortDesKh() {
        return shortDesKh;
    }

    public void setShortDesKh(String shortDesKh) {
        this.shortDesKh = shortDesKh;
    }

    public String getFullDesKh() {
        return fullDesKh;
    }

    public void setFullDesKh(String fullDesKh) {
        this.fullDesKh = fullDesKh;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getSubServiceOrder() {
        return subServiceOrder;
    }

    public void setSubServiceOrder(Integer subServiceOrder) {
        this.subServiceOrder = subServiceOrder;
    }

    public String getVaLiDiTy() {
        return vaLiDiTy;
    }

    public void setVaLiDiTy(String vaLiDiTy) {
        this.vaLiDiTy = vaLiDiTy;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public Integer getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Integer autoRenew) {
        this.autoRenew = autoRenew;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }
}
