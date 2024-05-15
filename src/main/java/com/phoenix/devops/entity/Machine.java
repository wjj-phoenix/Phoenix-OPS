package com.phoenix.devops.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 主机表 实体类。
 *
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Machine implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 机器名
     */
    private String name;

    /**
     * 机器IP
     */
    private String ip;

    /**
     * 用于SSH链接的端口
     */
    private Integer port;

    /**
     * 主机操作系统
     */
    private String operatingSystem;

    /**
     * 是否为虚拟机（0：不是；1：是）
     */
    private Boolean isVirtual;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建用户
     */
    private Long createdUser;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime createdTime;

    @Column(ignore = true)
    private List<HostUser> hostUsers;

}
