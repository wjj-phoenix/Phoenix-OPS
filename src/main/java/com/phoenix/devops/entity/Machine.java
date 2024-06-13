package com.phoenix.devops.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.RelationOneToMany;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;

/**
 * 主机表 实体类。
 *
 * @author wjj-phoenix
 * @since 2024-06-13T20:35:20.646330300
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("machine")
public class Machine implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
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
    private Integer isVirtual;

    /**
     * 是否可用（0：不可用；1：可用）
     */
    private Integer enabled;

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

    /**
     * 最近登录时间
     */
    private LocalDateTime latestTime;

    /**
     * 修改时间
     */
    @Column(onUpdateValue = "now()")
    private LocalDateTime updatedTime;

    /**
     * 修改用户
     */
    private Long updatedUser;

    @RelationOneToMany(selfField = "id", targetField = "machineId",targetTable = "resource_auth")
    private List<ResourceAuth> resourceAuths;
}
