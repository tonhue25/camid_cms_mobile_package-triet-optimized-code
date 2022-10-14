package co.siten.myvtg.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DanTran
 * @return
 * @see Menus
 */


public class Menus implements Comparable<Menus> {
    private Long objectId;
    private int appId;
    private int status;
    private String objectName;
    private int objectTypeId;
    private String objectCode;
    private String objectLevel;
    private String createDate;
    private long objectType;
    private long parentId;
    private Long ord;
    private String nameUS;
    private String nameVN;
    private String icons;
    private String objectUrl;
    private List<Menus> listMenuChild = new ArrayList();
    private List<Menus> listMenuComponent = new ArrayList();

    @Override
    public String toString() {
        return "Menus{" +
                "objectId=" + objectId +
                ", appId=" + appId +
                ", status=" + status +
                ", objectName='" + objectName + '\'' +
                ", objectTypeId=" + objectTypeId +
                ", objectCode='" + objectCode + '\'' +
                ", objectLevel='" + objectLevel + '\'' +
                ", createDate='" + createDate + '\'' +
                ", objectType=" + objectType +
                ", parentId=" + parentId +
                ", ord=" + ord +
                ", nameUS='" + nameUS + '\'' +
                ", nameVN='" + nameVN + '\'' +
                ", icons='" + icons + '\'' +
                ", objectUrl='" + objectUrl + '\'' +
                ", listMenuChild=" + listMenuChild +
                ", listMenuComponent=" + listMenuComponent +
                '}';
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(int objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectLevel() {
        return objectLevel;
    }

    public void setObjectLevel(String objectLevel) {
        this.objectLevel = objectLevel;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getObjectType() {
        return objectType;
    }

    public void setObjectType(long objectType) {
        this.objectType = objectType;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public String getNameUS() {
        return nameUS;
    }

    public void setNameUS(String nameUS) {
        this.nameUS = nameUS;
    }

    public String getNameVN() {
        return nameVN;
    }

    public void setNameVN(String nameVN) {
        this.nameVN = nameVN;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getObjectUrl() {
        return objectUrl;
    }

    public void setObjectUrl(String objectUrl) {
        this.objectUrl = objectUrl;
    }

    public List<Menus> getListMenuChild() {
        return listMenuChild;
    }

    public void setListMenuChild(List<Menus> listMenuChild) {
        this.listMenuChild = listMenuChild;
    }

    public List<Menus> getListMenuComponent() {
        return listMenuComponent;
    }

    public void setListMenuComponent(List<Menus> listMenuComponent) {
        this.listMenuComponent = listMenuComponent;
    }

    @Override
    public int compareTo(Menus permission) {
        return this.objectId.compareTo(permission.getObjectId());
    }
}
