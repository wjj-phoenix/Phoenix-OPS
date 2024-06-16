package com.phoenix.devops.terminal;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.phoenix.devops.model.terminal.TerminalReceiveMsg;
import com.phoenix.devops.utils.JsonUtils;
import com.phoenix.devops.utils.WebSocketUtil;
import com.phoenix.devops.utils.terminal.TerminalUtil;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
@Log4j2
@Component
public class WebTerminalHandler {
    @Resource
    private ITerminalHandler handler;

    private static final Map<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();

    public void recvMsg(String buffer, WebSocketSession session) {
        TerminalReceiveMsg msg = JsonUtils.parseObject(buffer, TerminalReceiveMsg.class);
        if (msg.getType().equals(TerminalReceiveMsg.CMD)) {
            Channel channel = CHANNEL_MAP.get(session.getId());
            try {
                trans2Ssh(channel, msg.getMsg());
            } catch (Exception e) {
                log.error("执行命令 :'{}' 失败", msg.getMsg(), e);
                close(session);
            }
            return;
        }

        if (msg.getType().equals(TerminalReceiveMsg.RESIZE)) {
            ChannelShell channel = (ChannelShell) CHANNEL_MAP.get(session.getId());
            if (channel != null) {
                channel.setPtySize(msg.getCols(), msg.getRows(), msg.getCols(), msg.getRows());
            }
        }
    }

    public void connectShell(Long machineId,String username, WebSocketSession webSocketSession) throws Exception {
        ChannelShell channel = TerminalUtil.openChannelShell(handler.getSession(machineId, username));
        // 通道连接 超时时间3s
        channel.connect(5000);
        CHANNEL_MAP.put(webSocketSession.getId(), channel);
        // 读取终端返回的信息流
        try (InputStream inputStream = channel.getInputStream()) {
            // 循环读取
            byte[] buffer = new byte[1024];
            int i;
            // 如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = inputStream.read(buffer)) != -1) {
                sendMessage(webSocketSession, Arrays.copyOfRange(buffer, 0, i));
            }
        } finally {
            // 断开连接后关闭会话
            channel.disconnect();
        }
    }

    /**
     * 发送消息至客户端
     *
     * @param session session
     * @param buffer  内容
     */
    public void sendMessage(WebSocketSession session, byte[] buffer) throws IOException {
        WebSocketUtil.sendText(session, new String(buffer));
    }

    public void close(WebSocketSession session) {
        String id = session.getId();
        Channel channel = CHANNEL_MAP.get(id);
        if (channel != null) {
            channel.disconnect();
        }
        CHANNEL_MAP.remove(id);
    }

    /**
     * 将消息转发到终端
     *
     * @param channel channel
     * @param command cmd
     * @throws IOException exception
     */
    private void trans2Ssh(Channel channel, String command) throws IOException {
        if (channel != null) {
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
        }
    }
}
