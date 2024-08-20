package ${(appObjectPackage)!};

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import cn.zhxu.bs.bean.DbIgnore;
import cn.zhxu.bs.bean.SearchBean;
import ${(entityPackage)!};
/**
* @author yishu
*/
@Data
@TableName("${(tableName)!}")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@SearchBean(tables = "${(tableName)!}")
public class ${(entityName)!}AO extends ${(entityName)!} {

}
