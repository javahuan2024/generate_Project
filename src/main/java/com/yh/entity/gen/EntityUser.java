package com.yh.entity.gen;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import cn.zhxu.bs.bean.DbField;

/**
 * <p>
 * 
 * </p>
 *
 * @author yh
 * @since 2024-08-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("smbms_user")
public class EntityUser extends Model<EntityUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户编码
     */
    @TableField("userCode")
    private String userCode;

    /**
     * 用户名称
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("userPassword")
    private String userPassword;

    /**
     * 性别（1:女、 2:男）
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 出生日期
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 手机
     */
    @TableField("phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 用户角色（取自角色表-角色id）
     */
    @TableField("userRole")
    private Long userRole;

    /**
     * 创建者（userId）
     */
    @TableField("create_user_id")
    private String createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者（userId）
     */
    @TableField("modify_user_id")
    private String modifyUserId;

    /**
     * 更新时间
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    /**
     * 逻辑删除字段 0：无效 1：有效
     */
    @TableField("is_valid")
    private Boolean isValid;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
