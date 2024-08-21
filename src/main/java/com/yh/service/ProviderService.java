package com.yh.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yh.common.ServiceResult;
import com.yh.common.utils.ResultMap;
import org.springframework.stereotype.Service;
import com.yh.appobject.EntityProviderAO;
import com.yh.dao.gen.EntityProviderGeneratedMapper;
import com.yh.service.share.BaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* service
*
* @author yh
* @since Aug 21, 2024
*/
@Service
public class ProviderService extends BaseService<EntityProviderGeneratedMapper, EntityProviderAO> {

    /**
    * 新增数据
    *
    * @param entityProviderAO 新增数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<EntityProviderAO> add(EntityProviderAO entityProviderAO) {
    ServiceResult<EntityProviderAO> retCheck = this.validateBase(false, entityProviderAO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retAdd = this.save(retCheck.getData());
        if(!retAdd){
            return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entityProviderAO);
    }

    /**
    * 更新数据
    *
    * @param entityProviderAO 更新数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<EntityProviderAO> update(EntityProviderAO entityProviderAO) {
        ServiceResult<EntityProviderAO> retCheck = this.validateBase(true, entityProviderAO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retUpdate = this.updateById(retCheck.getData());
        if(!retUpdate){
           return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entityProviderAO);
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
        EntityProviderAO existAO = this.getById(id);
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
    public void fillList(List<EntityProviderAO> dataList){
        if(CollUtil.isEmpty(dataList)){
            return;
        }

        //TODO 数据填充逻辑
    }

    /**
    * 数据校验默认数据填充
    *
    * @param isModify 是否是更新数据标志
    * @param entityProviderAO 校验数据
    * @return ServiceResult
    */
    private ServiceResult<EntityProviderAO> validateBase(boolean isModify, EntityProviderAO entityProviderAO) {
        if (isModify) {
            if (StrUtil.isEmpty(entityProviderAO.getId())) {
                return ResultMap.failResult("主键id不能为空！");
            }
            EntityProviderAO existAO = this.getById(entityProviderAO.getId());
            if (existAO == null) {
                return ResultMap.failResult("更新数据不存在！");
            }
        }else {
            //必填字段校验和默认数据填充
            if(null == entityProviderAO.getIsValid()){
                return ResultMap.failResult("逻辑删除字段 0：无效 1：有效不能为空");
            }
        }
        return ResultMap.successResult(entityProviderAO);
    }
}


