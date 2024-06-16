package com.phoenix.devops.handler;

import com.phoenix.devops.pool.GlobalThreadPool;
import com.phoenix.devops.terminal.WebTerminalHandler;
import com.phoenix.devops.utils.SpringUtil;
import com.phoenix.devops.utils.WebSocketUtil;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@Log4j2
@Component
public class IWebTerminalHandler extends TextWebSocketHandler {
    private WebTerminalHandler terminalHandler;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("连接已建立，会话ID：{}，客户端地址：{}", session.getId(), session.getRemoteAddress());
        if (this.terminalHandler == null) {
            this.terminalHandler = SpringUtil.getBean(WebTerminalHandler.class);
        }
        Long machineId = (Long) session.getAttributes().get("machineId");
        String username = (String) session.getAttributes().get("username");

        GlobalThreadPool.execute(() -> {
            try {
                // 连接终端服务器
                terminalHandler.connectShell(machineId, username, session);
            } catch (Exception e) {
                WebSocketUtil.sendText(session, String.format("连接失败：%s", e.getMessage()));
                terminalHandler.close(session);
            }
        });
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) {
        log.info("接受到消息：{}", message.getPayload());
        String payload = message.getPayload();
        terminalHandler.recvMsg(payload, session);
    }

    @Override
    public void handleTransportError(@NonNull WebSocketSession session, Throwable exception) {
        log.error("发生错误, Session ID： {}", session.getId());
        exception.printStackTrace(System.err);
    }

    /**
     * 连接关闭移除会话
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {
        log.info("连接被关闭，会话ID：{}，客户端地址：{}", session.getId(), session.getRemoteAddress());
        terminalHandler.close(session);
    }
}
