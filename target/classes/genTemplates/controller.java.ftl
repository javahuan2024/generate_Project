package ${(controllerPackage)!};

import ${paramPackage};
import ${aoPackage};
import ${servicePackage};
import ${packageFix}.utils.UserHolder;
import ${packageFix}.annotation.OperateLog;
import ${packageFix}.enums.OperateTypeEnum;
import ${packageFix}.appobject.EntityUserAO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yh.security.annotation.Permission;
import com.yh.common.annotation.RepeatSubmit;
import com.yh.common.ServiceResult;
import com.yh.common.utils.ResultMap;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* ${table.comment!}controller
*
* @author ${author}
* @since ${date}
*/
@RestController
@RequestMapping(value = "/pc/manage/${path}")
public class ${(controllerObjName)!} {

    /**
    * 服务对象
    */
    @Resource
    private ${(objName)!}Service serv;

    /**
    * userHolder
    */
    @Resource
    private UserHolder userHolder;


    /**
    * 新增${table.comment}
    * @param param 参数
    * @return 是否成功
    */
    @PostMapping(value = "add")
    public ServiceResult<Entity${(objName)!}AO> add(@RequestBody @Validated(${(objName)!}Param.add.class) ${(objName)!}Param param){
        Entity${(objName)!}AO ao = new Entity${(objName)!}AO();
        BeanUtil.copyProperties(param,ao);

        //用户信息获取 设置创建人
        EntityUserAO user = userHolder.getManageUser();
        ao.setCreateUserId(user.getId());
        return serv.add(ao);
    }

    /**
    * 更新${table.comment}
    * @param param 参数
    * @return 是否成功
    */
    @PostMapping(value = "updated")
    public ServiceResult<Entity${(objName)!}AO> updated(@RequestBody @Validated(${(objName)!}Param.edit.class) ${(objName)!}Param param){
        Entity${(objName)!}AO ao = new Entity${(objName)!}AO();
        BeanUtil.copyProperties(param,ao);

        //用户信息获取 设置更新人
        EntityUserAO user = userHolder.getManageUser();
        ao.setModifyUserId(user.getId());
        return serv.update(ao);
    }

    /**
    * 删除
    * @param param 参数
    * @return 是否成功
    */
    @PostMapping(value = "deleted")
    public ServiceResult<Boolean> deleted(@RequestBody @Validated(${(objName)!}Param.delete.class) ${(objName)!}Param param){
        //用户信息获取 设置更新人
        EntityUserAO user = userHolder.getManageUser();
        return serv.delete(param.getId(), user.getId());
    }

    /**
    * 获取详情
    * @param param 参数
    * @return 详情
    */
    @PostMapping(value = "detail")
    public ServiceResult<Entity${(objName)!}AO> detail(@RequestBody @Validated(${(objName)!}Param.detail.class) ${(objName)!}Param param){
        return serv.getValidById(param.getId());
    }


    /**
    * 不分页获取数据
    * @param params 参数
    * @return 是否成功
    */
    @PostMapping("/listAll")
    public ServiceResult<List<Entity${(objName)!}AO>> listAll(@RequestBody Map<String, Object> params) {
       return serv.list(params);
    }

    /**
    * 分页获取数据
    * @param params 参数
    * @return 是否成功
    */
    @PostMapping("/list")
    public ServiceResult<Page<Entity${(objName)!}AO>> list(@RequestBody Map<String, Object> params) {
        return serv.page(params);
    }
}
