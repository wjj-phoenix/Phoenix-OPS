package com.phoenix.devops.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.phoenix.devops.common.SelectCommon;
import com.phoenix.devops.entity.Machine;
import com.phoenix.devops.lang.IPage;
import com.phoenix.devops.mapper.MachineMapper;
import com.phoenix.devops.model.vo.MachineVO;
import com.phoenix.devops.service.IMachineService;
import com.phoenix.devops.service.IResourceAuthService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.phoenix.devops.entity.table.ResourceAuthTableDef.RESOURCE_AUTH;
import static com.phoenix.devops.entity.table.MachineTableDef.MACHINE;

/**
 * 主机表 服务层实现。
 *
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@Log4j2
@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine> implements IMachineService {
    @Resource
    private IResourceAuthService resourceAuthService;

    @Override
    public IPage<Machine> fetchMachinesByCondition(Integer page, Integer limit, String condition) {
        QueryWrapper wrapper = QueryWrapper.create().select(MACHINE.ALL_COLUMNS, RESOURCE_AUTH.ALL_COLUMNS).from(MACHINE).leftJoin(RESOURCE_AUTH).on(RESOURCE_AUTH.MACHINE_ID.eq(MACHINE.ID));
        return new IPage<>(new SelectCommon<Machine>().findAll(mapper, page, limit, condition, wrapper));
    }

    @Override
    public List<Machine> fetchAllMachines() {
        return mapper.selectListWithRelationsByQuery(
                QueryWrapper.create().select(MACHINE.ALL_COLUMNS, RESOURCE_AUTH.ALL_COLUMNS).from(MACHINE).leftJoin(RESOURCE_AUTH).on(RESOURCE_AUTH.MACHINE_ID.eq(MACHINE.ID)));
    }

    @Override
    public Machine fetchMachineById(Long machineId) {
        Machine machine = this.getById(machineId);
        machine.setResourceAuths(resourceAuthService.fetchAllResourceAuthsByMachineId(machineId));
        return machine;
    }

    @Override
    public Long addMachine(MachineVO machineVO) {
        Machine machine = BeanUtil.toBean(machineVO, Machine.class);
        machine.setCreatedUser(1L);
        if (!this.save(machine)) {
            throw new IllegalArgumentException("添加主机信息失败");
        }
        return machine.getId();
    }

    @Override
    public Boolean updateMachineById(Long id, MachineVO machineVO) {
        Machine machine = BeanUtil.toBean(machineVO, Machine.class);
        machine.setId(id);
        if (!this.updateById(machine)) {
            throw new IllegalArgumentException("更新主机信息失败");
        }
        return true;
    }

    @Override
    public Boolean delMachine(Set<Long> ids) {
        List<Machine> machines = this.list(QueryWrapper.create().select(MACHINE.ALL_COLUMNS).from(MACHINE)
                .leftJoin(RESOURCE_AUTH).on(RESOURCE_AUTH.MACHINE_ID.eq(MACHINE.ID)).where(MACHINE.ID.in(ids)));
        if (CollUtil.isNotEmpty(machines)) {
            List<String> machinesList = machines.stream().map(Machine::getName).distinct().toList();
            throw new IllegalArgumentException(String.format("主机【%s】还存在关联账户，不允许删除", StrUtil.join(",", machinesList)));
        }

        if (!this.removeByIds(ids)) {
            throw new IllegalArgumentException("删除主机信息失败");
        }
        return true;
    }

}
