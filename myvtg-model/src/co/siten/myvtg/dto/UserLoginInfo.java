package co.siten.myvtg.dto;

import java.util.List;

/**
 * @author DanTran
 * @return
 * @see UserLoginInfo
 */

public class UserLoginInfo {
    private UserInfo userInfo;
    private List<Roles> roles;
    private List<Menus> listMenuParent;
    private List<String> listMenuAccess;
    private List<Menus> listPermissionComponent;

    public UserLoginInfo(){}
    public UserLoginInfo(UserInfo userInfo, List<Roles> roles, List<Menus> listMenuParent, List<String> listMenuAccess, List<Menus> listPermissionComponent) {
        this.userInfo = userInfo;
        this.roles = roles;
        this.listMenuParent = listMenuParent;
        this.listMenuAccess = listMenuAccess;
        this.listPermissionComponent = listPermissionComponent;
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "userInfo=" + userInfo +
                ", roles=" + roles +
                ", listMenuParent=" + listMenuParent +
                ", listMenuAccess=" + listMenuAccess +
                ", listPermissionComponent=" + listPermissionComponent +
                '}';
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Menus> getListMenuParent() {
        return listMenuParent;
    }

    public void setListMenuParent(List<Menus> listMenuParent) {
        this.listMenuParent = listMenuParent;
    }

    public List<String> getListMenuAccess() {
        return listMenuAccess;
    }

    public void setListMenuAccess(List<String> listMenuAccess) {
        this.listMenuAccess = listMenuAccess;
    }

    public List<Menus> getListPermissionComponent() {
        return listPermissionComponent;
    }

    public void setListPermissionComponent(List<Menus> listPermissionComponent) {
        this.listPermissionComponent = listPermissionComponent;
    }
}
