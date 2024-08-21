package com.yh.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yh.common.ServiceResult;
import com.yh.common.utils.ResultMap;
import org.springframework.stereotype.Service;
import com.yh.appobject.EntityUserAO;
import com.yh.dao.gen.EntityUserGeneratedMapper;
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
public class UserService extends BaseService<EntityUserGeneratedMapper, EntityUserAO> {

    /**
    * 新增数据
    *
    * @param entityUserAO 新增数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<EntityUserAO> add(EntityUserAO entityUserAO) {
    ServiceResult<EntityUserAO> retCheck = this.validateBase(false, entityUserAO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retAdd = this.save(retCheck.getData());
        if(!retAdd){
            return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entityUserAO);
    }

    /**
    * 更新数据
    *
    * @param entityUserAO 更新数据信息
    * @return ServiceResult
    */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ServiceResult<EntityUserAO> update(EntityUserAO entityUserAO) {
        ServiceResult<EntityUserAO> retCheck = this.validateBase(true, entityUserAO);
        if(!retCheck.isSucceed()){
            return retCheck;
        }
        boolean retUpdate = this.updateById(retCheck.getData());
        if(!retUpdate){
           return ResultMap.failResult("创建数据失败！");
        }
        return ResultMap.successResult(entityUserAO);
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
        EntityUserAO existAO = this.getById(id);
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
    public void fillList(List<EntityUserAO> dataList){
        if(CollUtil.isEmpty(dataList)){
            return;
        }

        //TODO 数据填充逻辑
    }

    /**
    * 数据校验默认数据填充
    *
    * @param isModify 是否是更新数据标志
    * @param entityUserAO 校验数据
    * @return ServiceResult
    */
    private ServiceResult<EntityUserAO> validateBase(boolean isModify, EntityUserAO entityUserAO) {
        if (isModify) {
            if (StrUtil.isEmpty(entityUserAO.getId())) {
                return ResultMap.failResult("主键id不能为空！");
            }
            EntityUserAO existAO = this.getById(entityUserAO.getId());
            if (existAO == null) {
                return ResultMap.failResult("更新数据不存在！");
            }
        }else {
            //必填字段校验和默认数据填充
            if(null == entityUserAO.getIsValid()){
                return ResultMap.failResult("逻辑删除字段 0：无效 1：有效不能为空");
            }
        }
        return ResultMap.successResult(entityUserAO);
    }
}


