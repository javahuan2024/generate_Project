package com.yh.service.share;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.common.ServiceResult;
import com.yh.common.baseParam.BaseParam;
import com.yh.common.utils.ResultMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用服务
 *
 * @param <M> BaseMapper
 * @param <T> ao实体
 * @author tim
 */
@Service
public abstract class BaseService<M extends BaseMapper<T>, T extends Model> extends ServiceImpl<M, T> {

    @Resource
    private BeanSearcher beanSearcher;


    /**
     * 获取有效数据
     */
    public ServiceResult<T> getValidById(String id) {

        if (StrUtil.isEmpty(id)) {
            return ResultMap.failResult("Id不能为空");
        }

        QueryWrapper<T> queryWrapper = this.buildQueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("is_valid", Boolean.TRUE);

        T data = this.getOne(queryWrapper);

        if (data == null) {
            return ResultMap.failResult("数据查询异常");
        }

        return ResultMap.successResult(data);

    }

    /**
     * 获取列表数据
     */
    public ServiceResult<List<T>> list(BaseParam param) {
        Map<String, Object> params = BeanUtil.beanToMap(param, false, true);
        return this.list(params);
    }

    /**
     * 获取列表数据
     */
    public ServiceResult<List<T>> list(Map<String, Object> params) {
        params.put("pageNo", 1);
        params.put("pageSize", 99);
        ServiceResult<Page<T>> pageRes = this.page(params);
        if (!pageRes.isSucceed()) {
            return ResultMap.failResult(pageRes.getMsg());
        }
        Page<T> page = pageRes.getData();
        return ResultMap.successResult(page.getRecords());
    }

    /**
     * 分页获取数据
     * @param param 查询参数
     */
    public ServiceResult<Page<T>> page(BaseParam param) {

        Map<String, Object> params = BeanUtil.beanToMap(param, false, true);

        return this.page(params);

    }

    /**
     * 分页获取数据
     */
    public ServiceResult<Page<T>> page(Map<String, Object> params) {

        if (CollUtil.isEmpty(params)) {
            params = new HashMap<>();
            params.put("pageNo", 1);
            params.put("pageSize", 20);
        }

        // 排序
        if (params.get("sortField") != null) {
            if (params.get("sortOrder") != null) {
                params.put("sort", params.get("sortField"));
                params.put("order", params.get("sortOrder"));
            } else {
                params.put("sort", params.get("sortField"));
                params.put("order", "desc");
            }
        } else {
            params.put("sort", "createTime");
            params.put("order", "desc");
        }

        // 分页
        int page = params.get("pageNo") != null ? (Integer) params.get("pageNo") : 0;
        int size = params.get("pageSize") != null ? (Integer) params.get("pageSize") : 20;
        params.put("isValid", Boolean.TRUE);

        // 获取数据
        Class<T> clazz = (Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Number count = beanSearcher.searchCount(clazz, params);
        List<T> dataList = beanSearcher.searchList(clazz, params);

        Page<T> pageData = new Page<>();
        pageData.setTotal(count.longValue());
        pageData.setCurrent(page);
        pageData.setSize(size);
        pageData.setRecords(dataList);

        return ResultMap.successResult(pageData);
    }

    /**
     * 获取查询构造器
     */
    public QueryWrapper<T> buildQueryWrapper() {

        return new QueryWrapper<>();

    }

    /**
     * 处理供应商查询参数
     *
     * @param params
     */
    public void handleSupplierParam(Map<String, Object> params) {
        if (params.get("searchSupplier") != null) {
            String searchSupplier = (String) params.get("searchSupplier");
            Map<String, Object> currParam = MapUtils.builder()
                    .field("supplierId")
                    .sql("$1 in (select id from supplier where supplier_code like concat('%',?,'%') or supplier_name like concat('%',?,'%'))", searchSupplier, searchSupplier)
                    .build();

            params.putAll(currParam);
        }
    }

}
