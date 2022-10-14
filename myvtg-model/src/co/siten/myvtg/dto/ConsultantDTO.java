package co.siten.myvtg.dto;

/**
 *
 * @author daibq
 *
 */
public class ConsultantDTO {

    private String groupCode;
    private String groupName;
    private String name;
    private String code;
    private String shortDes;
    private String iconUrl;
    private Integer isMultPlan;
    private Integer isRegisterAble;
    private Integer state = 1; // default value: Registered

    public ConsultantDTO(String groupName, String groupCode, String name, String code, String shortDes, String iconUrl,
            Integer isMultPlan) {
        super();
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isMultPlan = isMultPlan;
    }

    public ConsultantDTO(String name, String code, String shortDes, Integer isRegisable, String iconUrl, Integer isMultPlan) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isRegisterAble = isRegisable;
        this.isMultPlan = isMultPlan;
    }

    public ConsultantDTO(String name, String code, String shortDes, Integer isRegisable, String iconUrl, Integer isMultPlan, Integer state) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isRegisterAble = isRegisable;
        this.isMultPlan = isMultPlan;
        this.state = state;
    }

    public ConsultantDTO(String name, String code, String shortDes, String iconUrl) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
    }

    public ConsultantDTO(String name, String code, String shortDes, String iconUrl, Integer state) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.state = state;
    }

    public Integer getIsRegisterAble() {
        return isRegisterAble;
    }

    public void setIsRegisterAble(Integer isRegisterAble) {
        this.isRegisterAble = isRegisterAble;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getIsMultPlan() {
        return isMultPlan;
    }

    public void setIsMultPlan(Integer isMultPlan) {
        this.isMultPlan = isMultPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
