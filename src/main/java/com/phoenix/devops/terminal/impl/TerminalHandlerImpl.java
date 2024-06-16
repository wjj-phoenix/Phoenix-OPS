package com.phoenix.devops.terminal.impl;

import com.jcraft.jsch.Session;
import com.phoenix.devops.entity.Machine;
import com.phoenix.devops.exception.BizAssert;
import com.phoenix.devops.service.IMachineService;
import com.phoenix.devops.terminal.ITerminalHandler;
import com.phoenix.devops.utils.IOUtil;
import com.phoenix.devops.utils.ResourceUtil;
import com.phoenix.devops.utils.terminal.SessionInfo;
import com.phoenix.devops.utils.terminal.TerminalUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
@Service
public class TerminalHandlerImpl implements ITerminalHandler {
    private static final Logger log = LoggerFactory.getLogger(TerminalHandlerImpl.class);
    @Resource
    private IMachineService machineService;

    private static final Map<String, String> SHELL_CACHE = new ConcurrentHashMap<>();

    @Override
    public String runShell(Long machineId, String username, String shellFileName, Object... param) {
        String shellContent = getShellContent(shellFileName);
        if (!ArrayUtils.isEmpty(param)) {
            shellContent = String.format(shellContent, param);
        }
        BizAssert.notEmpty(shellContent, "不存在该shell脚本");
        return exec(machineId, username, shellContent);
    }
    @Override
    public Session getSession(Long machineId, String username) {
        Machine machine = machineService.fetchMachineAndResourceAuthByIdAndUsername(machineId, username);
        BizAssert.notNull(machine, "机器不存在");
        return TerminalUtil.getSession(Long.toString(machineId), () -> SessionInfo.builder(machine.getIp()).port(machine.getPort()).password(machine.getResourceAuths().getFirst().getPassword()).username(machine.getResourceAuths().getFirst().getUsername()).build());
    }

    /**
     * 获取类路径下的shell脚本内容
     *
     * @param shellFileName shell文件名
     * @return 文件内容
     */
    private String getShellContent(String shellFileName) {
        return SHELL_CACHE.computeIfAbsent(shellFileName, key -> {
            List<URL> resources = ResourceUtil.getResources("shell/" + key + ".sh");
            for (URL url : resources) {
                try {
                    return IOUtil.read(url.openStream());
                } catch (Exception e) {
                    throw BizAssert.newException("读取shell文件失败");
                }
            }
            return null;
        });
    }
}
