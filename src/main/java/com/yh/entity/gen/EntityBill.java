package com.yh.entity.gen;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("smbms_bill")
public class EntityBill extends Model<EntityBill> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 账单编码
     */
    @TableField("billCode")
    private String billCode;

    /**
     * 商品名称
     */
    @TableField("productName")
    private String productName;

    /**
     * 商品描述
     */
    @TableField("productDesc")
    private String productDesc;

    /**
     * 商品单位
     */
    @TableField("productUnit")
    private String productUnit;

    /**
     * 商品数量
     */
    @TableField("productCount")
    private BigDecimal productCount;

    /**
     * 商品总额
     */
    @TableField("totalPrice")
    private BigDecimal totalPrice;

    /**
     * 是否支付（1：未支付 2：已支付）
     */
    @TableField("isPayment")
    private Integer isPayment;

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
     * 供应商ID
     */
    @TableField("providerId")
    private Long providerId;

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
