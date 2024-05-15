package com.phoenix.devops.service;

import com.mybatisflex.core.service.IService;
import com.phoenix.devops.entity.Machine;
import com.phoenix.devops.lang.IPage;
import com.phoenix.devops.model.vo.MachineVO;

import java.util.List;
import java.util.Set;

/**
 * 主机表 服务层。
 *
 * @author wjj-phoenix
 * @since 2024-05-14
 */
public interface IMachineService extends IService<Machine> {
    /**
     * 根据条件查询主机
     * @param page       当前页码
     * @param limit      每页数据量
     * @param condition 条件
     * @return 主机列表
     */
    IPage<Machine> fetchMachinesByCondition(Integer page, Integer limit, String condition);

    /**
     * 根据主键ID查询主机详细信息
     * @param machineId 主键ID
     * @return 主机信息
     */
    Machine fetchMachineById(Long machineId);

    /**
     * 查询所有主机
     * @return 主机列表
     */
    List<Machine> fetchAllMachines();

    /**
     * 添加主机
     * @param machine 机器信息
     * @return 主键ID
     */
    Long addMachine(MachineVO machine);

    /**
     * 更新主机信息
     * @param id 主键ID
     * @param machine 机器信息
     * @return true|false
     */
    Boolean updateMachineById(Long id,MachineVO machine);

    /**
     * 多选删除主机
     * @param ids 主机ID集合
     */
    Boolean delMachine(Set<Long> ids);
}
