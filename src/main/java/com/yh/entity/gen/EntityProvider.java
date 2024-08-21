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
@TableName("smbms_provider")
public class EntityProvider extends Model<EntityProvider> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 供应商编码
     */
    @TableField("proCode")
    private String proCode;

    /**
     * 供应商名称
     */
    @TableField("proName")
    private String proName;

    /**
     * 供应商详细描述
     */
    @TableField("proDesc")
    private String proDesc;

    /**
     * 供应商联系人
     */
    @TableField("proContact")
    private String proContact;

    /**
     * 联系电话
     */
    @TableField("proPhone")
    private String proPhone;

    /**
     * 地址
     */
    @TableField("proAddress")
    private String proAddress;

    /**
     * 传真
     */
    @TableField("proFax")
    private String proFax;

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
     * 更新时间
     */
    @TableField("modify_user_id")
    private String modifyUserId;

    /**
     * 更新者（userId）
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Long modifyTime;

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
