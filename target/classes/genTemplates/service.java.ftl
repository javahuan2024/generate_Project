package ${(servicePackage)!};

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yh.common.ServiceResult;
import com.yh.common.utils.ResultMap;
import org.springframework.stereotype.Service;
import ${(aoPackage)!};
import ${(mapperPackage)!};
import ${(baseServicePackage)!};
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* ${table.comment!}service
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${(serviceObjName)!} extends BaseService<Entity${(objName)!}GeneratedMapper, Entity${(objName)!}AO> {

    /**
    * 新增数据
    *
    * @param entity${(objName)!}AO 新增数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<Entity${(objName)!}AO> add(Entity${(objName)!}AO entity${(objName)!}AO) {
    ServiceResult<Entity${(objName)!}AO> retCheck = this.validateBase(false, entity${(objName)!}AO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retAdd = this.save(retCheck.getData());
        if(!retAdd){
            return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entity${(objName)!}AO);
    }

    /**
    * 更新数据
    *
    * @param entity${(objName)!}AO 更新数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<Entity${(objName)!}AO> update(Entity${(objName)!}AO entity${(objName)!}AO) {
        ServiceResult<Entity${(objName)!}AO> retCheck = this.validateBase(true, entity${(objName)!}AO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retUpdate = this.updateById(retCheck.getData());
        if(!retUpdate){
           return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entity${(objName)!}AO);
    }

    /**
    * 删除数据
    *
    * @param updater 删除用户id
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<Boolean> delete(String id, String updater) {
        if(StrUtil.isBlank(id)){
            return ResultMap.failResult("删除用户信息不存在！");
        }
        Entity${(objName)!}AO existAO = this.getById(id);
        if (existAO == null) {
            return ResultMap.failResult("删除数据不存在！");
        }
        if(!existAO.getIsValid()){
            return ResultMap.successResult(true);
        }

        existAO.setIsValid(false);

        //TODO 设置更新时间和更新人

        boolean retDel = this.updateById(existAO);
        if(!retDel){
            return ResultMap.failResult("删除数据失败！");
        }
        return ResultMap.successResult(retDel);
    }



    /**
    * 列表数据填充
    *
    * @param dataList 列表数据
    */
    public void fillList(List<Entity${(objName)!}AO> dataList){
        if(CollUtil.isEmpty(dataList)){
            return;
        }

        //TODO 数据填充逻辑
    }

    /**
    * 数据校验默认数据填充
    *
    * @param isModify 是否是更新数据标志
    * @param entity${(objName)!}AO 校验数据
    * @return ServiceResult
    */
    private ServiceResult<Entity${(objName)!}AO> validateBase(boolean isModify, Entity${(objName)!}AO entity${(objName)!}AO) {
        if (isModify) {
            if (StrUtil.isEmpty(entity${(objName)!}AO.getId())) {
                return ResultMap.failResult("主键id不能为空！");
            }
            Entity${(objName)!}AO existAO = this.getById(entity${(objName)!}AO.getId());
            if (existAO == null) {
                return ResultMap.failResult("更新数据不存在！");
            }
        }else {
            //必填字段校验和默认数据填充
    <#list table.fields as field>
        <#if !field.metaInfo.nullable && !field.keyFlag>
            <#if field.propertyType == "String">
            if(StrUtil.isBlank(entity${(objName)!}AO.get${field.propertyName?cap_first}())){
                return ResultMap.failResult("${field.comment}不能为空");
            }
            <#else >
            if(null == entity${(objName)!}AO.get${field.propertyName?cap_first}()){
                return ResultMap.failResult("${field.comment}不能为空");
            }
            </#if>
        </#if>
    </#list>
        }
        return ResultMap.successResult(entity${(objName)!}AO);
    }
}


