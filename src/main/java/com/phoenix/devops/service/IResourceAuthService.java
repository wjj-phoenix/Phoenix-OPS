package com.phoenix.devops.service;

import java.util.List;
import java.util.Set;

import com.mybatisflex.core.service.IService;
import com.phoenix.devops.entity.ResourceAuth;
import com.phoenix.devops.lang.IPage;
import com.phoenix.devops.model.vo.ResourceAuthVO;

/**
 * 主机账号 服务层。
 *
 * @author wjj-phoenix
 * @since 2024-06-13T20:35:20.646330300
 */
public interface IResourceAuthService extends IService<ResourceAuth> {
/**
     * 根据条件查询主机账号
     *
     * @param page      当前页码
     * @param limit     每页数据量
     * @param condition 条件
     * @return 主机账号列表
     */
    IPage<ResourceAuth> fetchResourceAuthsByCondition(Integer page, Integer limit, String condition);

    /**
     * 根据主键ID查询主机账号详细信息
     *
     * @param userId 主键ID
     * @return 主机账号信息
     */
    ResourceAuth fetchResourceAuthById(long userId);

    /**
     * 查询所有主机账号
     *
     * @return 主机账号列表
     */
    List<ResourceAuth> fetchAllResourceAuths();

    /**
     * 根据主机ID查询主机用户
     *
     * @param machineId 主机ID
     * @return 主机用户列表
     */
    List<ResourceAuth> fetchAllResourceAuthsByMachineId(Long machineId);

    /**
     * 添加主机账号
     *
     * @param ResourceAuth 机器信息
     * @return 主键ID
     */
    Long addResourceAuth(ResourceAuthVO ResourceAuth);

    /**
     * 更新主机账号信息
     *
     * @param id       主键ID
     * @param ResourceAuth 机器信息
     * @return true|false
     */
    Boolean updateResourceAuthById(Long id, ResourceAuthVO ResourceAuth);

    /**
     * 多选删除主机账号
     *
     * @param ids 主机账号ID集合
     */
    Boolean delResourceAuth(Set<Long> ids);
}
