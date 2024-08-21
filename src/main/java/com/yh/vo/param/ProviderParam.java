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
public class ProviderParam extends BaseParam {


    /**
     * 主键ID
     */
    @NotBlank(message = "主键ID不能为空", groups = {detail.class, edit.class, delete.class})
    private String id;

    /**
     * 供应商编码
     */
    private String proCode;

    /**
     * 供应商名称
     */
    private String proName;

    /**
     * 供应商详细描述
     */
    private String proDesc;

    /**
     * 供应商联系人
     */
    private String proContact;

    /**
     * 联系电话
     */
    private String proPhone;

    /**
     * 地址
     */
    private String proAddress;

    /**
     * 传真
     */
    private String proFax;

    /**
     * 创建者（userId）
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private String modifyUserId;

    /**
     * 更新者（userId）
     */
    private Long modifyTime;

    /**
     * 逻辑删除字段 0：无效 1：有效
     */
    @NotNull(message = "逻辑删除字段 0：无效 1：有效不能为空", groups = {add.class})
    private Boolean isValid;

}
