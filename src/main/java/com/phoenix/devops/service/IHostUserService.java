package com.phoenix.devops.service;

import com.mybatisflex.core.service.IService;
import com.phoenix.devops.entity.HostUser;
import com.phoenix.devops.lang.IPage;
import com.phoenix.devops.model.vo.HostUserVO;

import java.util.List;
import java.util.Set;

/**
 * 主机账号账号 服务层。
 *
 * @author wjj-phoenix
 * @since 2024-05-14
 */
public interface IHostUserService extends IService<HostUser> {
    /**
     * 根据条件查询主机账号
     *
     * @param page      当前页码
     * @param limit     每页数据量
     * @param condition 条件
     * @return 主机账号列表
     */
    IPage<HostUser> fetchHostUsersByCondition(Integer page, Integer limit, String condition);

    /**
     * 根据主键ID查询主机账号详细信息
     *
     * @param userId 主键ID
     * @return 主机账号信息
     */
    HostUser fetchHostUserById(long userId);

    /**
     * 查询所有主机账号
     *
     * @return 主机账号列表
     */
    List<HostUser> fetchAllHostUsers();

    /**
     * 根据主机ID查询主机用户
     *
     * @param machineId 主机ID
     * @return 主机用户列表
     */
    List<HostUser> fetchAllHostUsersByMachineId(Long machineId);

    /**
     * 添加主机账号
     *
     * @param hostUser 机器信息
     * @return 主键ID
     */
    Long addHostUser(HostUserVO hostUser);

    /**
     * 更新主机账号信息
     *
     * @param id       主键ID
     * @param hostUser 机器信息
     * @return true|false
     */
    Boolean updateHostUserById(Long id, HostUserVO hostUser);

    /**
     * 多选删除主机账号
     *
     * @param ids 主机账号ID集合
     */
    Boolean delHostUser(Set<Long> ids);
}
