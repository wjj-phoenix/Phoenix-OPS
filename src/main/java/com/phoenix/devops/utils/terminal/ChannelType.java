package com.phoenix.devops.utils.terminal;

import com.phoenix.devops.utils.enums.ValueEnum;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 * Jsch支持的Channel类型
 */
public enum ChannelType implements ValueEnum<String> {
    /**
     * Session
     */
    SESSION("session"),
    /**
     * shell
     */
    SHELL("shell"),
    /**
     * exec
     */
    EXEC("exec"),
    /**
     * x11
     */
    X11("x11"),
    /**
     * agent forwarding
     */
    AGENT_FORWARDING("auth-agent@openssh.com"),
    /**
     * direct tcpip
     */
    DIRECT_TCPIP("direct-tcpip"),
    /**
     * forwarded tcpip
     */
    FORWARDED_TCPIP("forwarded-tcpip"),
    /**
     * sftp
     */
    SFTP("sftp"),
    /**
     * subsystem
     */
    SUBSYSTEM("subsystem");

    /**
     * channel值
     */
    private final String value;

    /**
     * 构造
     *
     * @param value 类型值
     */
    ChannelType(String value) {
        this.value = value;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    @Override
    public String getValue() {
        return this.value;
    }
}
