package com.phoenix.devops.common;

import java.util.List;
import java.util.Map;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.phoenix.devops.utils.ParamUtil;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SelectCommon<T> {
    public Page<T> findAll(BaseMapper<T> mapper, Integer page, Integer limit, String condition, QueryWrapper wrapper) {
        if (StrUtil.isNotBlank(condition)) {
            Map<String, Object> map = ParamUtil.split(condition);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                wrapper.and(new QueryColumn(entry.getKey()).like(entry.getValue()));
            }
            log.info("构造的查询条件: {}", wrapper);
        }
        return mapper.paginateWithRelations(new Page<>(page, limit), wrapper(condition));
    }
    /**
     * 分页查询所有信息
     *
     * @param page      页码
     * @param limit      每页大小
     * @param condition 查询条件
     * @param service   查询实体
     * @return 分页对象
     */
    public Page<T> findAll(Integer page, Integer limit, String condition, IService<T> service) {
        return service.page(new Page<>(page, limit), wrapper(condition));
    }

    /**
     * 根据条件查询信息
     * @param condition 条件
     * @param service 查询实体
     * @return
     */
    public List<T> findAll(String condition, IService<T> service) {
        return service.list(wrapper(condition));
    }

    public QueryWrapper wrapper(String condition) {
        QueryWrapper wrapper = QueryWrapper.create().where("1 = 1");
        if (StrUtil.isNotBlank(condition)) {
            Map<String, Object> map = ParamUtil.split(condition);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                wrapper.and(new QueryColumn(entry.getKey()).like(entry.getValue()));
            }
            log.info("构造的查询条件: {}", wrapper);
        }
        return wrapper;
    }
}
