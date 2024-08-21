//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yh.utils;

import java.io.Serializable;
import java.util.Set;

public class SecurityUser implements Serializable {
    private String securityId;
    private String securityUserName;
    private boolean isSuperAdmin = false;
    private Set<String> permissionList;

    public SecurityUser() {
    }

    public String getSecurityId() {
        return this.securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getSecurityUserName() {
        return this.securityUserName;
    }

    public void setSecurityUserName(String securityUserName) {
        this.securityUserName = securityUserName;
    }

    public Set<String> getPermissionList() {
        return this.permissionList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }

    public boolean isSuperAdmin() {
        return this.isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.isSuperAdmin = superAdmin;
    }
}
