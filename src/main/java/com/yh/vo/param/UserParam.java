package com.yh.vo.param;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.yh.common.baseParam.BaseParam;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* <p>
* 
* </p>
*
* @author yh
* @since Aug 21, 2024
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserParam extends BaseParam {


    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空", groups = {detail.class, edit.class, delete.class})
    private String id;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 性别（1:女、 2:男）
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 手机
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户角色（取自角色表-角色id）
     */
    private Long userRole;

    /**
     * 创建者（userId）
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者（userId）
     */
    private String modifyUserId;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 逻辑删除字段 0：无效 1：有效
     */
    @NotNull(message = "逻辑删除字段 0：无效 1：有效不能为空", groups = {add.class})
    private Boolean isValid;

}
