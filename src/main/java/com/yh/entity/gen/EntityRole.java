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
@TableName("smbms_role")
public class EntityRole extends Model<EntityRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 角色编码
     */
    @TableField("roleCode")
    private String roleCode;

    /**
     * 角色名称
     */
    @TableField("roleName")
    private String roleName;

    /**
     * 创建者
     */
    @TableField("create_user_id")
    private String createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改者
     */
    @TableField("modify_user_id")
    private String modifyUserId;

    /**
     * 修改时间
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
