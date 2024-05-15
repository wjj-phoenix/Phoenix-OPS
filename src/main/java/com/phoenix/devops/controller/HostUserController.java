package com.phoenix.devops.controller;

import com.phoenix.devops.entity.HostUser;
import com.phoenix.devops.lang.IPage;
import com.phoenix.devops.model.vo.HostUserVO;
import com.phoenix.devops.service.IHostUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 主机账号 控制层。
 *
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@RestController
@RequestMapping("/host-user")
public class HostUserController {

    @Resource
    private IHostUserService service;

    /**
     * 添加主机账号。
     *
     * @param hostUser 主机账号
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping()
    public Long save(@RequestBody HostUserVO hostUser) {
        return service.addHostUser(hostUser);
    }

    /**
     * 根据主键删除主机账号。
     *
     * @param ids 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping()
    public boolean remove(@RequestBody Set<Long> ids) {
        return service.delHostUser(ids);
    }

    /**
     * 根据主键更新主机账号。
     *
     * @param hostUser 主机账号
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody HostUserVO hostUser) {
        return service.updateHostUserById(id, hostUser);
    }

    /**
     * 查询所有主机账号。
     *
     * @return 所有数据
     */
    @GetMapping()
    public List<HostUser> list() {
        return service.fetchAllHostUsers();
    }

    /**
     * 根据主机ID查询所有主机账号。
     *
     * @param machineId 主机ID
     * @return 所有数据
     */
    @GetMapping("/machine/{machineId}")
    public List<HostUser> fetchByMachineId(@PathVariable Long machineId) {
        return service.fetchAllHostUsersByMachineId(machineId);
    }

    /**
     * 根据主机账号主键获取详细信息。
     *
     * @param id 主机账号主键
     * @return 主机账号详情
     */
    @GetMapping("/{id}")
    public HostUser getInfo(@PathVariable Long id) {
        return service.fetchHostUserById(id);
    }

    /**
     * 分页查询主机账号。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public IPage<HostUser> page(@RequestParam(defaultValue = "1", required = false) Integer page,
                                @RequestParam(defaultValue = "20", required = false) Integer limit,
                                @RequestParam(defaultValue = "", required = false) String condition) {
        return service.fetchHostUsersByCondition(page, limit, condition);
    }

}
