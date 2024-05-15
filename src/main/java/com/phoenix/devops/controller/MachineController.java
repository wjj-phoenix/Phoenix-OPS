package com.phoenix.devops.controller;

import com.phoenix.devops.entity.Machine;
import com.phoenix.devops.lang.IPage;
import com.phoenix.devops.model.vo.MachineVO;
import com.phoenix.devops.service.IMachineService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 主机表 控制层。
 *
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@RestController
@RequestMapping("/machine")
public class MachineController {

    @Resource
    private IMachineService service;

    /**
     * 添加主机表。
     *
     * @param machine 主机表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping()
    public Long save(@RequestBody MachineVO machine) {
        return service.addMachine(machine);
    }

    /**
     * 根据主键删除主机表。
     *
     * @param ids 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping()
    public boolean remove(@RequestBody Set<Long> ids) {
        return service.delMachine(ids);
    }

    /**
     * 根据主键更新主机表。
     *
     * @param machine 主机表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody MachineVO machine) {
        return service.updateMachineById(id, machine);
    }

    /**
     * 查询所有主机表。
     *
     * @return 所有数据
     */
    @GetMapping()
    public List<Machine> list() {
        return service.fetchAllMachines();
    }

    /**
     * 根据主机表主键获取详细信息。
     *
     * @param id 主机表主键
     * @return 主机表详情
     */
    @GetMapping("/{id}")
    public Machine getInfo(@PathVariable Long id) {
        return service.fetchMachineById(id);
    }

    /**
     * 分页查询主机表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/pages")
    public IPage<Machine> page(@RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer limit,
            @RequestParam(defaultValue = "", required = false) String condition) {
        return service.fetchMachinesByCondition(page, limit, condition);
    }

}
