package co.siten.myvtg.dto;

public class Roles {
    private int status;
    private int roleId;
    private String roleName;
    private String description;
    private String roleCode;
    private String createDate;
    private int creatorId;
    private String creatorName;
    private int ipOfficeWan;

    @Override
    public String toString() {
        return "Roles{" +
                "status=" + status +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", createDate='" + createDate + '\'' +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", ipOfficeWan=" + ipOfficeWan +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public int getIpOfficeWan() {
        return ipOfficeWan;
    }

    public void setIpOfficeWan(int ipOfficeWan) {
        this.ipOfficeWan = ipOfficeWan;
    }
}
