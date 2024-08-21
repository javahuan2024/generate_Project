package com.yh.appobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import cn.zhxu.bs.bean.DbIgnore;
import cn.zhxu.bs.bean.SearchBean;
import com.yh.entity.gen.EntityAddress;
/**
* @author yishu
*/
@Data
@TableName("smbms_address")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@SearchBean(tables = "smbms_address")
public class EntityAddressAO extends EntityAddress {

}
