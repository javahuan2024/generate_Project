package com.yh.common.define;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * StaticDefine
 */
public class StaticDefine {

    /**
     * PC端管理后台请求头TOKEN名称
     */
    public final static String MANAGE_SESSION_ID = "X-Manage-Session-Id";

    /**
     * PDA端请求头TOKEN名称
     */
    public final static String PDA_SESSION_ID = "X-Pda-Session-Id";
    /**
     * 会话用户
     */
    public final static String SESSION_USER = "sessionUser";

    /**
     * 商户端小程序请求头TOKEN名称
     */
    public final static String MINI_MERCHANT_SESSION_ID = "X-Mini-Merchant-Session-Id";

    /**
     * 商户端小程序会话用户
     */
    public final static String MERCHANT_SESSION_USER = "merchantSessionUser";

    /**
     * 顾客小程序请求头TOKEN名称
     */
    public final static String MINI_CUSTOMER_SESSION_ID = "X-Mini-Customer-Session-Id";

    /**
     * 顾客会话用户
     */
    public final static String CUSTOMER_SESSION_USER = "customerSessionUser";

    /**
     * 运营平台机构ID
     */
    public final static String PLATFORM_INSTITUTION_ID = "0";

    /**
     * 系统管理员角色CODE
     */
    public final static String ADMIN_USER_ROLE_CODE = "99";

    /**
     * 账号注册初始密码
     */
    public final static String INITIAL_PASSWORD = "123456";

    /**
     * 根节点
     */
    public final static String ROOT_NODE = "0";

    /**
     * 根节点location（location使用）
     */
    public static final String ROOT_NODE_LOCATION = "00";

    /**
     * 新节点字符（location使用）
     */
    public static final String NEW_NODE_STR = "$$";

    /**
     * 节点结束字符（location使用）
     */
    public static final String LAST_NODE_STR = "zz";

    /**
     * 文件上传路径
     */
    public final static String FILE_UPLOAD_PATH = "FILE_UPLOAD_PATH";

    /**
     * 水印文件后缀
     */
    public final static String WATERMARK_FILE_SUFFIX = "_watermark";
    /**FILE_UPLOAD_PATH
     * 图片
     */
    public static final String IMG_ATTACH_CODE = "image";
    /**
     * 视频
     */
    public static final String VIDEO_ATTACH_CODE = "video";

    /**
     * 设备类型CODE
     */
    public static final String DEVICE_TYPE_CODE = "DEVICE_TYPE";

    /**
     * 检测设备回调负载KEY
     */
    public static final String DEVICE_REQUEST_PAYLOAD_KEY = "payload";

    /**
     * 设备状态CODE
     */
    public static final String DEVICE_STATUS_CODE = "DEVICE_STATUS";

    /**
     * 菜单开放权限标识
     */
    public static final String MENU_OPEN_AUTHORITY = "open";

    /**
     * 姚安中心标志
     */
    public static final String SPL_CEN_CODE = "YAOAN";

    /**
     * 提花道口编号 桶：1~20
     */
    public static final List<String> EXTRACT_WINDOW_CODE_BUCKET = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");

    /**
     * 提花道口编号 盒：1~5
     */
    public static final List<String> EXTRACT_WINDOW_CODE_BOX = Arrays.asList("1", "2", "3", "4", "5");

    /**
     * 用户类型
     */
    public class UserType {

        /**
         * 运营平台用户
         */
        public final static String MANAGE_USER = "0";

        /**
         * 机构平台用户
         */
        public final static String INSTITUTION_USER = "1";
    }

    /**
     * 用户状态
     */
    @Getter
    public enum UserStatus {

        ACTIVE("00", "正常"),
        LOCKED("99", "锁定"),
        INVALID("10", "无效"),
        STOP("20", "停用");

        private String code;
        private String name;

        UserStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getNameByCode(String code) {
            for (UserStatus enums : UserStatus.values()) {
                if(enums.code.equals(code)){
                    return enums.name;
                }
            }
            return "";
        }
        public static String getCodeByName(String name) {
            for (UserStatus enums : UserStatus.values()) {
                if(enums.name.equals(name)){
                    return enums.code;
                }
            }
            return "";
        }
    }

    /**
     * 附件存储类型
     */
    public class AttachmentStorageType {

        /**
         * 本地磁盘存储
         */
        public static final String LOCAL = "local";

        /**
         * 阿里云OSS
         */
        public static final String OSS = "oss";
    }

    /**
     * 子系统类型
     */
    public class SysType {

        /**
         * 运营管理系统
         */
        public final static String MANAGE = "manage";

        /**
         * 机构端管理系统
         */
        public final static String INSTITUTION = "institution";
    }

    /**
     * 生成编号前缀类型
     */
    public class CodePrefix {

        /**
         * 工单
         */
        public final static String PROJECT = "GD";

    }

    /**
     * 角色机构id
     */
    public class RoleInstitution {
        /**
         * 平台用户
         */
        public final static String MANAGE_ROLE = "0";

        /**
         * 客户外部人员
         */
        public final static String CUSTOMER_ROLE = "1";
    }

    /**
     * 角色code
     */
    public class RoleCode {
        /**
         * 平台管理员
         */
        public final static String MANAGE_ROLE = "99";

        /**
         * 客户外部人员
         */
        public final static String CUSTOMER_ROLE = "1";
    }

    ///**
    // * 字典可配置类型参数
    // */
    //public static class DIC_TYPE_PARAM {
    //    public static List<EntityDicAO> getDicTypeList() {
    //        ArrayList<EntityDicAO> defineDicTypeList = new ArrayList<>();
    //        return defineDicTypeList;
    //    }
    //}

    /**
     * 权限类型
     */
    public class AUTHORITY_TYPE {
        /**
         * 菜单权限
         */
        public final static String MENU = "menu";

        /**
         * 按钮权限
         */
        public final static String BUTTON = "button";
    }

    ///**
    // * 默认权限
    // */
    //public static EntityAuthorityAO getDefaultAuthor() {
    //    EntityAuthorityAO defaultAuthority = new EntityAuthorityAO();
    //    defaultAuthority.setName("公开");
    //    defaultAuthority.setCode(StaticDefine.MENU_OPEN_AUTHORITY);
    //
    //    return defaultAuthority;
    //}


    /**
     * 附件关联类型
     */
    public static class ATTACHMENT_BUSINESS_TYPE {

        /**
         * 用户头像
         */
        public final static String USER_HEAD = "USER_HEAD";

        /**
         * 定级结果
         */
        public final static String LEVEL_RESULT_PIC = "LEVEL_RESULT_PIC";

        /**
         * 容器定级结果
         */
        public final static String LEVEL_CONTAINERS_RESULT_PIC = "LEVEL_CONTAINERS_RESULT_PIC";

        /**
         * 花卉定级图片(自评)
         */
        public final static String SELF_LEVEL_PIC = "SELF_LEVEL_PIC";

        /**
         * 扫描图片
         */
        public final static String SCAN_PIC = "SCAN_PIC";

        /**
         * 快递发货信息图片
         */
        public final static String EXPRESS_DELIVERY_INFORMATION = "EXPRESS_DELIVERY_INFORMATION";
    }
}
