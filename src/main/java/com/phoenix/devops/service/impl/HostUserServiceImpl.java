package com.phoenix.devops.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.phoenix.devops.common.SelectCommon;
import com.phoenix.devops.entity.HostUser;
import com.phoenix.devops.lang.IPage;
import com.phoenix.devops.mapper.HostUserMapper;
import com.phoenix.devops.model.vo.HostUserVO;
import com.phoenix.devops.service.IHostUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.phoenix.devops.entity.table.HostUserTableDef.HOST_USER;

/**
 * 主机账号 服务层实现。
 *
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@Service
public class HostUserServiceImpl extends ServiceImpl<HostUserMapper, HostUser> implements IHostUserService {

    @Override
    public IPage<HostUser> fetchHostUsersByCondition(Integer page, Integer limit, String condition) {
        return new IPage<>(new SelectCommon<HostUser>().findAll(page, limit, condition, this));
    }

    @Override
    public HostUser fetchHostUserById(long userId) {
        return this.getById(userId);
    }

    @Override
    public List<HostUser> fetchAllHostUsers() {
        return this.list(QueryWrapper.create().select(HOST_USER.ALL_COLUMNS).from(HOST_USER));
    }

    @Override
    public List<HostUser> fetchAllHostUsersByMachineId(Long machineId) {
        return this.list(
                QueryWrapper.create()
                        .select(HOST_USER.ALL_COLUMNS).from(HOST_USER)
                        .where(HOST_USER.MACHINE_ID.eq(machineId))
        );
    }

    @Override
    public Long addHostUser(HostUserVO hostUserVO) {
        HostUser hostUser = BeanUtil.toBean(hostUserVO, HostUser.class);
        if (!this.save(hostUser)) {
            throw new IllegalArgumentException("添加主机账号失败");
        }
        return hostUser.getId();
    }

    @Override
    public Boolean updateHostUserById(Long id, HostUserVO hostUserVO) {
        HostUser hostUser = BeanUtil.toBean(hostUserVO, HostUser.class);
        hostUser.setId(id);
        if (!this.updateById(hostUser)) {
            throw new IllegalArgumentException("添加主机账号失败");
        }
        return true;
    }

    @Override
    public Boolean delHostUser(Set<Long> ids) {
        if (!this.removeByIds(ids)) {
            throw new IllegalArgumentException("删除主机账户失败!");
        }
        return true;
    }
}
