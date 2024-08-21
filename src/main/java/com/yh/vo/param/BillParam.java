package com.yh.vo.param;

import java.math.BigDecimal;
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
public class BillParam extends BaseParam {


    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空", groups = {detail.class, edit.class, delete.class})
    private String id;

    /**
     * 账单编码
     */
    private String billCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述
     */
    private String productDesc;

    /**
     * 商品单位
     */
    private String productUnit;

    /**
     * 商品数量
     */
    private BigDecimal productCount;

    /**
     * 商品总额
     */
    private BigDecimal totalPrice;

    /**
     * 是否支付（1：未支付 2：已支付）
     */
    private Integer isPayment;

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
     * 供应商ID
     */
    private Long providerId;

    /**
     * 逻辑删除字段 0：无效 1：有效
     */
    private Boolean isValid;

}
