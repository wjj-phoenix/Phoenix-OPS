package com.phoenix.devops.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MachineVO {
    /**
     * 机器名
     */
    @NotBlank(message = "机器名不能为空")
    private String name;

    /**
     * 机器IP
     */
    @NotBlank(message = "机器IP不能为空")
    private String ip;

    /**
     * 用于SSH链接的端口
     */
    @NotNull(message = "需要指定SSH连接端口")
    private Integer port;

    /**
     * 主机操作系统
     */
    @NotBlank(message = "需要指定主机操作系统")
    private String operatingSystem;

    /**
     * 是否为虚拟机（0：不是；1：是）
     */
    @NotNull(message = "需要设置虚拟机是否为虚拟机")
    private Boolean isVirtual;

    /**
     * 备注信息
     */
    private String remark;
}
