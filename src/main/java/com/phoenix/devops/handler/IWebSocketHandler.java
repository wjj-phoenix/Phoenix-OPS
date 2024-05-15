package com.phoenix.devops.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@Log4j2
@Component
public class IWebSocketHandler extends TextWebSocketHandler {
    /**
     * 保存连接的会话
     */
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("连接已建立，会话ID：{}，客户端地址：{}", session.getId(), session.getRemoteAddress());
        sessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("接受到消息：{}", message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("消息传输出错！");
        exception.printStackTrace();
    }

    /**
     * 连接关闭移除会话
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("连接被关闭，会话ID：{}，客户端地址：{}", session.getId(), session.getRemoteAddress());
        sessions.remove(session.getId());
    }

    /**
     * 向客户端推送消息
     *
     * @param msg 文本消息
     */
    public void pushMsg(String msg) {
        final Collection<WebSocketSession> webSocketSessions = sessions.values();
        final TextMessage textMessage = new TextMessage(msg);
        webSocketSessions.forEach(s -> {
            try {
                s.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
