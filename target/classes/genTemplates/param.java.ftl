package ${(paramPackage)!};

<#if fieldPackages!?size gt 0 >
<#list fieldPackages as pkg>
import ${pkg};
</#list>
</#if>
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
* ${table.comment!}
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ${(paramObjName)!} extends BaseParam {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    <#if !field.metaInfo.nullable && field.keyFlag>
        <#if field.propertyType == "String">
    @NotBlank(message = "${field.comment}不能为空", groups = {detail.class, edit.class, delete.class})
        <#else >
    @NotNull(message = "${field.comment}不能为空", groups = {detail.class, edit.class, delete.class})
        </#if>
    <#elseif !field.metaInfo.nullable>
        <#if field.propertyType == "String">
    @NotBlank(message = "${field.comment}不能为空", groups = {add.class})
        <#else >
    @NotNull(message = "${field.comment}不能为空", groups = {add.class})
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}
