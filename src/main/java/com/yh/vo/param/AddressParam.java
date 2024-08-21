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
public class AddressParam extends BaseParam {


    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空", groups = {detail.class, edit.class, delete.class})
    private String id;

    /**
     * 联系人姓名
     */
    private String contact;

    /**
     * 收货地址明细
     */
    private String addressDesc;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 联系人电话
     */
    private String tel;

    /**
     * 创建者
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改者
     */
    private String modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 逻辑删除字段 0：无效 1：有效
     */
    @NotNull(message = "逻辑删除字段 0：无效 1：有效不能为空", groups = {add.class})
    private Boolean isValid;

}
