package com.yh.utils;


import com.yh.appobject.EntityUserAO;
import com.yh.common.define.StaticDefine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 获取回话中的用户
 * @author chris
 */
@Slf4j
@Component
public class UserHolder implements IUserHolder {
    /**
     * 管理员CODE
     */
    private static final String ADMIN_AUTH_CODE = "admin";

    /**
     * 获取PC端管理用户
     * @return 返回管理用户信息
     */
    public EntityUserAO getManageUser() {
        EntityUserAO user = (EntityUserAO) RequestHolder.getRequest().getAttribute(StaticDefine.SESSION_USER);
        if (user == null) {
            throw new RuntimeException("从会话中获取用户信息为空！");
        } else {
            return user;
        }
    }

    /**
     * 获取pc端用户<br>
     * 获取用户失败将返回空
     * @return
     */
    //public EntityUserAO getManageUserNotThrowException() {
    //    EntityUserAO user = null;
    //    try {
    //        user = (EntityUserAO) RequestHolder.getSession().getAttribute(StaticDefine.SESSION_USER);
    //    } catch (Exception e) {
    //        log.error("获取客户信息失败！", e);
    //    }
    //    return user;
    //}

    @Override
    public SecurityUser getSecurityUser() {
        return null;
    }

    /**
     * 用户角色权限信息
     * @return 用户角色权限信息
     */
    //@Override
    //public SecurityUser getSecurityUser() {
    //
    //    // 设置用户权限
    //    SecurityUser securityUser = new SecurityUser();
    //
    //    // 根据路径 获取平台、机构用户
    //    EntityUserAO manageUser = null;
    //    String requestUri = RequestHolder.getRequest().getRequestURI();
    //    if (requestUri.contains("pc/manage") || requestUri.contains("pda/")) {
    //        // 获取登陆用户
    //        manageUser = (EntityUserAO) RequestHolder.getRequest().getAttribute(StaticDefine.SESSION_USER);
    //
    //        securityUser.setSecurityId(manageUser.getId());
    //        securityUser.setSecurityUserName(manageUser.getName());
    //
    //        List<String> userAuthorityList = manageUser.getUserAuthorityList();
    //        Set<String> userAuthoritySet = null;
    //        if (!CollectionUtils.isEmpty(userAuthorityList)) {
    //            userAuthoritySet = new HashSet<>(userAuthorityList);
    //        }
    //        securityUser.setPermissionList(userAuthoritySet);
    //    }
    //    if (requestUri.contains("pc/institution")) {
    //        // TODO 有机构端再增加业务逻辑代码
    //    }
    //
    //    if (requestUri.contains("mini/merchant")) {
    //        // 获取登陆商户
    //        EntitySupplierAO supplier = (EntitySupplierAO) RequestHolder.getRequest().getAttribute(StaticDefine.MERCHANT_SESSION_USER);
    //        securityUser.setSecurityId(supplier.getId());
    //        securityUser.setSecurityUserName(supplier.getSupplierName());
    //        return securityUser;
    //    }
    //
    //    securityUser.setSecurityId(manageUser.getId());
    //    securityUser.setSecurityUserName(manageUser.getName());
    //    List<String> userAuthorityList = manageUser.getUserAuthorityList();
    //    Set<String> userAuthoritySet = new HashSet<>(userAuthorityList);
    //    if (manageUser.getAdmin()) {
    //        userAuthoritySet.add(ADMIN_AUTH_CODE);
    //    }
    //    securityUser.setPermissionList(userAuthoritySet);
    //
    //    return securityUser;
    //}

    ///**
    // * 获取商户端管理用户
    // */
    //public EntitySupplierAO getMerchantUser() {
    //    EntitySupplierAO supplier =  (EntitySupplierAO) RequestHolder.getRequest().getAttribute(StaticDefine.MERCHANT_SESSION_USER);
    //    if (supplier == null) {
    //        throw new UserNotLogedException("从会话中获取商户信息为空！");
    //    } else {
    //        return supplier;
    //    }
    //}
    ///**
    // * 顾客信息
    // */
    //public EntityCustomerAO getCustomer() {
    //    EntityCustomerAO user = (EntityCustomerAO) RequestHolder.getRequest().getAttribute(StaticDefine.CUSTOMER_SESSION_USER);
    //    if (user == null) {
    //        throw new UserNotLogedException("从会话中获取用户信息为空！");
    //    } else {
    //        return user;
    //    }
    //}

}
