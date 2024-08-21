package com.yh.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yh.common.ServiceResult;
import com.yh.common.utils.ResultMap;
import org.springframework.stereotype.Service;
import com.yh.appobject.EntityAddressAO;
import com.yh.dao.gen.EntityAddressGeneratedMapper;
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
public class AddressService extends BaseService<EntityAddressGeneratedMapper, EntityAddressAO> {

    /**
    * 新增数据
    *
    * @param entityAddressAO 新增数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<EntityAddressAO> add(EntityAddressAO entityAddressAO) {
    ServiceResult<EntityAddressAO> retCheck = this.validateBase(false, entityAddressAO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retAdd = this.save(retCheck.getData());
        if(!retAdd){
            return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entityAddressAO);
    }

    /**
    * 更新数据
    *
    * @param entityAddressAO 更新数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<EntityAddressAO> update(EntityAddressAO entityAddressAO) {
        ServiceResult<EntityAddressAO> retCheck = this.validateBase(true, entityAddressAO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retUpdate = this.updateById(retCheck.getData());
        if(!retUpdate){
           return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entityAddressAO);
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
        EntityAddressAO existAO = this.getById(id);
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
    public void fillList(List<EntityAddressAO> dataList){
        if(CollUtil.isEmpty(dataList)){
            return;
        }

        //TODO 数据填充逻辑
    }

    /**
    * 数据校验默认数据填充
    *
    * @param isModify 是否是更新数据标志
    * @param entityAddressAO 校验数据
    * @return ServiceResult
    */
    private ServiceResult<EntityAddressAO> validateBase(boolean isModify, EntityAddressAO entityAddressAO) {
        if (isModify) {
            if (StrUtil.isEmpty(entityAddressAO.getId())) {
                return ResultMap.failResult("主键id不能为空！");
            }
            EntityAddressAO existAO = this.getById(entityAddressAO.getId());
            if (existAO == null) {
                return ResultMap.failResult("更新数据不存在！");
            }
        }else {
            //必填字段校验和默认数据填充
            if(null == entityAddressAO.getIsValid()){
                return ResultMap.failResult("逻辑删除字段 0：无效 1：有效不能为空");
            }
        }
        return ResultMap.successResult(entityAddressAO);
    }
}


