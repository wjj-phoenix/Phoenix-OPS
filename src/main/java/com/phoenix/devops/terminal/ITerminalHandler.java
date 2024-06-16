package com.phoenix.devops.terminal;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.phoenix.devops.exception.BizAssert;
import com.phoenix.devops.exception.terminal.TerminalException;
import com.phoenix.devops.utils.IOUtil;
import com.phoenix.devops.utils.terminal.TerminalUtil;

import java.util.function.Function;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
public interface ITerminalHandler {
    /**
     * 执行shell目录下的shell脚本
     *
     * @param machineId     机器id
     * @param username      主机用户名
     * @param shellFileName shell文件名
     * @return 执行结果
     */
    String runShell(Long machineId, String username, String shellFileName, Object... param);

    /**
     * 执行指定机器的命令
     *
     * @param machineId 机器id
     * @param username  主机用户名
     * @param cmd       命令
     */
    default String exec(Long machineId, String username, String cmd) {
        try {
            return TerminalUtil.exec(getSession(machineId, username), cmd);
        } catch (TerminalException e) {
            throw BizAssert.newException(e.getMessage());
        }
    }

    /**
     * 执行指定机器的命令
     *
     * @param machineId     机器id
     * @param username      主机用户名
     * @param cmd           命令
     * @param lineProcessor 行处理器
     */
    default void exec(Long machineId, String username, String cmd, IOUtil.LineProcessor lineProcessor) {
        try {
            TerminalUtil.exec(getSession(machineId, username), cmd, lineProcessor);
        } catch (TerminalException e) {
            throw BizAssert.newException(e.getMessage());
        }
    }

    /**
     * 获取sftp 并对channel执行操作
     *
     * @param machineId 机器id
     */
    default <T> T sftpOperate(Long machineId, String username, Function<ChannelSftp, T> function) {
        try {
            return TerminalUtil.sftpOperate(getSession(machineId, username), function);
        } catch (TerminalException e) {
            throw BizAssert.newException(e.getMessage());
        }
    }

    /**
     * 获取指定机器的session
     *
     * @param machineId 机器id
     * @param username  主机用户名
     * @return session
     */
    Session getSession(Long machineId, String username);
}

