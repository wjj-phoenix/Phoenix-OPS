package com.phoenix.devops.model.vo;

import com.phoenix.devops.model.Save;
import com.phoenix.devops.model.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceAuthVO {
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {Save.class, Update.class})
    private String name;

    /**
     * 用户名
     */
    @NotBlank(message = "主机账号不能为空", groups = {Save.class, Update.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "主机账号密码不能为空", groups = {Save.class, Update.class})
    private String password;

    /**
     * 是否为特权账号
     */
    @NotNull(message = "需要指定账号是否为特权账号", groups = {Save.class, Update.class})
    private Boolean isPrivileged;

    /**
     * 是否可用
     */
    @NotNull(message = "需要指定账号是否可用", groups = {Save.class, Update.class})
    private Boolean isEnabled;

    /**
     * 所属机器ID
     */
    @NotBlank(message = "需要指定账号所属主机", groups = {Save.class, Update.class})
    private Long machineId;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 有效结束时间
     */
    @NotBlank(message = "需要指定主机有效时间", groups = {Update.class})
    private LocalDateTime effectiveEndTime;
}
