package com.yh.controller;

import com.yh.appobject.EntityUserAO;
import com.yh.vo.param.BillParam;
import com.yh.appobject.EntityBillAO;
import com.yh.service.BillService;
import com.yh.utils.UserHolder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
* controller
*
* @author yh
* @since Aug 21, 2024
*/
@RestController
@RequestMapping(value = "/pc/manage/bill")
public class BillController {

    /**
    * 服务对象
    */
    @Resource
    private BillService serv;

    /**
    * userHolder
    */
    @Resource
    private UserHolder userHolder;


    /**
    * 新增
    * @param param 参数
    * @return 是否成功
    */
    @PostMapping(value = "add")
    public ServiceResult<EntityBillAO> add(@RequestBody @Validated(BillParam.add.class) BillParam param){
        EntityBillAO ao = new EntityBillAO();
        BeanUtil.copyProperties(param,ao);

        //用户信息获取 设置创建人
        EntityUserAO user = userHolder.getManageUser();
        ao.setCreateUserId(user.getId());
        return serv.add(ao);
    }

    /**
    * 更新
    * @param param 参数
    * @return 是否成功
    */
    @PostMapping(value = "updated")
    public ServiceResult<EntityBillAO> updated(@RequestBody @Validated(BillParam.edit.class) BillParam param){
        EntityBillAO ao = new EntityBillAO();
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
    public ServiceResult<Boolean> deleted(@RequestBody @Validated(BillParam.delete.class) BillParam param){
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
    public ServiceResult<EntityBillAO> detail(@RequestBody @Validated(BillParam.detail.class) BillParam param){
        return serv.getValidById(param.getId());
    }


    /**
    * 不分页获取数据
    * @param params 参数
    * @return 是否成功
    */
    @PostMapping("/listAll")
    public ServiceResult<List<EntityBillAO>> listAll(@RequestBody Map<String, Object> params) {
       return serv.list(params);
    }

    /**
    * 分页获取数据
    * @param params 参数
    * @return 是否成功
    */
    @PostMapping("/list")
    public ServiceResult<Page<EntityBillAO>> list(@RequestBody Map<String, Object> params) {
        return serv.page(params);
    }
}
