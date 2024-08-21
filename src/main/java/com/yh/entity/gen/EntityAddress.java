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
@TableName("smbms_address")
public class EntityAddress extends Model<EntityAddress> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 联系人姓名
     */
    @TableField("contact")
    private String contact;

    /**
     * 收货地址明细
     */
    @TableField("addressDesc")
    private String addressDesc;

    /**
     * 邮编
     */
    @TableField("postCode")
    private String postCode;

    /**
     * 联系人电话
     */
    @TableField("tel")
    private String tel;

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
     * 用户ID
     */
    @TableField("userId")
    private Long userId;

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
